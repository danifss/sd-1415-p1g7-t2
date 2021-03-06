package Factory;

import MainServer.ServerInfo;
import comInf.MessageConfig;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class FactoryServer {

    /**
     * Server Factory port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22172;
    
    public static void main(String[] args) throws UnknownHostException{
        
        setFactoryHostOnMainServer(MessageConfig.SETFACTORYHOST);
        setFactoryPortOnMainServer(MessageConfig.SETFACTORYPORT);
        
        Factory factory;                                        // Shop (servico a ser prestado)
        FactoryBroker factoryInterface;                         // Interface a Shop
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        FactoryBrokerRepository repository;                     // Repository Broker client
        
        int nCraftmans = contactMainServer(MessageConfig.GETNCRAFTMANS, -1);
        int nPrimeMaterialsInFactory = contactMainServer(MessageConfig.GETNPRIMEMATERIALSINFACTORY, -1);
        int nTotalPrime = contactMainServer(MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE, -1);
        int nPrimePerProduct = contactMainServer(MessageConfig.GETNPRIMEMATERIALSBYPRODUCT, -1);
        int nPrimeRestock = contactMainServer(MessageConfig.GETNMINPRIMEMATERIALSFORRESTOCK, -1);
        int nProductsCollect = contactMainServer(MessageConfig.GETNMAXPRODUCTSCOLLECT, -1);
        String repositoryHost = contactMainServer(MessageConfig.GETREPOSITORYHOST, "");
        int repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        
        repository = new FactoryBrokerRepository(repositoryHost, repositoryPort); // ativacao do servico
        factory = new Factory(repository, nPrimeMaterialsInFactory, nTotalPrime, nPrimePerProduct, nPrimeRestock, nProductsCollect); // activacao do servico
        
        factoryInterface = new FactoryBroker(factory, nCraftmans); // activacao do interface com o servico
        System.out.println("Factory");
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                                  // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, factoryInterface);    // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
    
    private static int contactMainServer(int msgType, int value){
        //ligar ao Main Server e informar o seu ip e porta e pedir valores que necessita.
        ClientCom con = new ClientCom(ServerInfo.getMainServerHostName(), ServerInfo.getMainServerPortNum());
        MessageConfig inMessage, outMessage;
        
        outMessage = new MessageConfig(msgType, value); // pede a realizacao do servico
        while (!con.open ()){                           // aguarda ligação
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
                System.out.println("Factory: Error contacting Main Server..");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    private static String contactMainServer(int msgType, String str){
        //Ligar ao Main Server e pedir valores que necessita.
        ClientCom con = new ClientCom(ServerInfo.getMainServerHostName(), ServerInfo.getMainServerPortNum());
        MessageConfig inMessage, outMessage;
        
        outMessage = new MessageConfig(msgType, str); // pede a realizacao do servico
        while (!con.open ()){                           // aguarda ligação
            try{
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageConfig) con.readObject();
        String result = "";
        switch(inMessage.getType()){
            case MessageConfig.ACK:
                result = inMessage.getStr();
                break;
            default:
                System.out.println("Factory: Error contacting Main Server..");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    private static void setFactoryHostOnMainServer(int msgType) throws UnknownHostException {
        //ligar ao Main Server e informar o seu ip e porta e pedir valores que necessita.
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
            System.out.println("Factory: Error contacting Main Server..");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static void setFactoryPortOnMainServer(int msgType) {
        //ligar ao Main Server e informar o seu ip e porta e pedir valores que necessita.
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
            System.out.println("Factory: Error contacting Main Server..");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
