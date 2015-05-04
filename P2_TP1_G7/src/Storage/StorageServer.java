package Storage;

import genclass.GenericIO;
import MainServer.ServerInfo;
import comInf.MessageConfig;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 *
 * @author Daniel
 */
public class StorageServer {
    
    /**
     * Server Storage port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22173;
    
    private static int nInitialPrimeMaterialsInStorage = 20; // Initial number of prime materials in the Storage
    private static int nPrimeOwnerCarry = 10; // Number of prime materials that the owner can carry
    
    public static void main(String[] args) throws UnknownHostException {
        
        setStorageHostOnMainServer(MessageConfig.SETSTORAGEHOST);
        setStoragePortOnMainServer(MessageConfig.SETSTORAGEPORT);
        
        nInitialPrimeMaterialsInStorage = contactMainServer(MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE);
        nPrimeOwnerCarry = contactMainServer(MessageConfig.GETNMAXPRODUCTSCOLLECT);
        
        Storage storage;
        StorageBroker storageInterface;
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        storage = new Storage(nInitialPrimeMaterialsInStorage, nPrimeOwnerCarry);
        storageInterface = new StorageBroker(storage);
        GenericIO.writelnString("Storage");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                               // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, storageInterface); // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
    
    private static void setStorageHostOnMainServer(int msgType) throws UnknownHostException {
        //TODO: ligar ao Main Server e informar o seu ip
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
            GenericIO.writelnString("Storage: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static void setStoragePortOnMainServer(int msgType) {
        //TODO: ligar ao Main Server e informar a sua porta.
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
            GenericIO.writelnString("Storage: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
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
                GenericIO.writelnString("Storage: Error contacting Main Server..");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
}
