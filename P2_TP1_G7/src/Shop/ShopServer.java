package Shop;

import MainServer.ServerInfo;
import comInf.MessageConfig;
import genclass.GenericIO;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 *
 * @author Daniel
 */
public class ShopServer {
    
    /**
     * Server Shop port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22171;
    
    private static String repositoryHost = "localhost";
    private static int repositoryPort = 22170;
    private static int nCustomers = 3;
    private static int nCraftmans = 3;
    private static int nInitialProductsInShop = 10; // Initial number of products in the Shop
    private static int nInitialPrimeMaterialsInStorage = 20; // Initial number of prime materials in the Storage
    private static int nPrimeMaterialsInFactory = 10; // Initial number of prime materials in the Factory
    private static int nPrimeMaterialsByProduct = 2; // Prime materials needed per product
    private static int totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage) / nPrimeMaterialsByProduct) + nInitialProductsInShop;
    
    public static void main(String[] args) throws UnknownHostException {
        // Aqui e onde corre o servidor e lanca threads(ClientProxy) para atender cada cliente.
        
        setShopHostOnMainServer(MessageConfig.SETSHOPHOST);
        setShopPortOnMainServer(MessageConfig.SETSHOPPORT);
        
        repositoryHost = contactMainServer(MessageConfig.GETREPOSITORYHOST, "");
        repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        nCustomers = contactMainServer(MessageConfig.GETNCUSTOMERS, -1);
        nCraftmans = contactMainServer(MessageConfig.GETNCRAFTMANS, -1);
        nInitialProductsInShop = contactMainServer(MessageConfig.GETNINITIALPRODUCTSINSHOP, -1);
        nInitialPrimeMaterialsInStorage = contactMainServer(MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE, -1);
        nPrimeMaterialsInFactory = contactMainServer(MessageConfig.GETNPRIMEMATERIALSINFACTORY, -1);
        nPrimeMaterialsByProduct = contactMainServer(MessageConfig.GETNPRIMEMATERIALSBYPRODUCT, -1);
        totalProducts = contactMainServer(MessageConfig.GETTOTALPRODUCTS, -1);
        
        Shop shop;                                              // Shop (servico a ser prestado)
        ShopBroker shopInterface;                               // Interface a Shop
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        ShopBrokerRepository repository;                        // Repository Broker client

        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        repository = new ShopBrokerRepository(repositoryHost, repositoryPort); // ativacao do servico
        shop = new Shop(nInitialProductsInShop, nCustomers, repository, totalProducts); // activacao do servico
        shopInterface = new ShopBroker(shop, nCustomers, nCraftmans); // activacao do interface com o servico
        GenericIO.writelnString("Shop");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                               // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, shopInterface);    // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
    
    private static int contactMainServer(int msgType, int value){
        //Ligar ao Main Server e pedir valores que necessita.
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
                GenericIO.writelnString("Repository: Error contacting Main Server..");
                GenericIO.writelnString(inMessage.toString());
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
                GenericIO.writelnString("Shop: Error contacting Main Server..");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    private static void setShopHostOnMainServer(int msgType) throws UnknownHostException {
        //Ligar ao Main Server e informar o seu ip.
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
            GenericIO.writelnString("Shop: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static void setShopPortOnMainServer(int msgType) {
        //Ligar ao Main Server e informar a sua porta.
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
            GenericIO.writelnString("Shop: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
