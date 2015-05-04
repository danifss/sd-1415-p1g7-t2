package Storage;

import MainServer.ServerInfo;
import comInf.MessageConfig;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class StorageServer {
    
    /**
     * Server Storage port number.
     * @serial portNumb
     */
    private static final int portNumb = 22173;

    public static void main(String[] args) throws UnknownHostException {
        
        setStorageHostOnMainServer(MessageConfig.SETSTORAGEHOST);
        setStoragePortOnMainServer(MessageConfig.SETSTORAGEPORT);
        
        int nInitialPrimeMaterialsInStorage = contactMainServer(MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE);
        int nPrimeOwnerCarry = contactMainServer(MessageConfig.GETNMAXPRODUCTSCOLLECT);
        
        Storage storage;
        StorageBroker storageInterface;
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        storage = new Storage(nInitialPrimeMaterialsInStorage, nPrimeOwnerCarry);
        storageInterface = new StorageBroker(storage);
        System.out.println("Storage");
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                               // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, storageInterface); // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
    
    private static void setStorageHostOnMainServer(int msgType) throws UnknownHostException {
        //ligar ao Main Server e informar o seu ip
        ClientCom con = new ClientCom(ServerInfo.getMainServerHostName(), ServerInfo.getMainServerPortNum());
        MessageConfig inMessage, outMessage;
        
        String addr = Inet4Address.getLocalHost().getHostAddress();
        outMessage = new MessageConfig(msgType, addr); // pede a realizacao do servico
        while (!con.open ()){                          // aguarda ligação
            try{
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageConfig) con.readObject();
        if(inMessage.getType() != MessageConfig.ACK){
            System.out.println("Storage: Error contacting Main Server..");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static void setStoragePortOnMainServer(int msgType) {
        //ligar ao Main Server e informar a sua porta.
        ClientCom con = new ClientCom(ServerInfo.getMainServerHostName(), ServerInfo.getMainServerPortNum());
        MessageConfig inMessage, outMessage;
        
        outMessage = new MessageConfig(msgType, portNumb); // pede a realizacao do servico
        while (!con.open ()){                              // aguarda ligação
            try{
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageConfig) con.readObject();
        if(inMessage.getType() != MessageConfig.ACK){
            System.out.println("Storage: Error contacting Main Server..");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static int contactMainServer(int msgType){
        // Ligar ao Main Server pedir valores que necessita.
        ClientCom con = new ClientCom(ServerInfo.getMainServerHostName(), ServerInfo.getMainServerPortNum());
        MessageConfig inMessage, outMessage;
        
        outMessage = new MessageConfig(msgType); // pede a realizacao do servico
        while (!con.open ()){                    // aguarda ligação
            try{
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageConfig) con.readObject();
        int result = -1;
        switch(inMessage.getType()){
            case MessageConfig.ACK:
                result = inMessage.getValue();
                break;
            default:
                System.out.println("Storage: Error contacting Main Server..");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
}
