package Shop;

import MainServer.ServerInfo;
import comInf.MessageConfig;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class ShopServer {
    
    /**
     * Server Shop port number.
     * @serial portNumb
     */
    private static final int portNumb = 22171;
    
    public static void main(String[] args) throws UnknownHostException {
        // Aqui e onde corre o servidor e lanca threads(ClientProxy) para atender cada cliente.
        
        setShopHostOnMainServer(MessageConfig.SETSHOPHOST);
        setShopPortOnMainServer(MessageConfig.SETSHOPPORT);
        
        String repositoryHost = contactMainServer(MessageConfig.GETREPOSITORYHOST, "");
        int repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        int nCustomers = contactMainServer(MessageConfig.GETNCUSTOMERS, -1);
        int nCraftmans = contactMainServer(MessageConfig.GETNCRAFTMANS, -1);
        int nInitialProductsInShop = contactMainServer(MessageConfig.GETNINITIALPRODUCTSINSHOP, -1);
//        int nInitialPrimeMaterialsInStorage = contactMainServer(MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE, -1);
//        int nPrimeMaterialsInFactory = contactMainServer(MessageConfig.GETNPRIMEMATERIALSINFACTORY, -1);
//        int nPrimeMaterialsByProduct = contactMainServer(MessageConfig.GETNPRIMEMATERIALSBYPRODUCT, -1);
        int totalProducts = contactMainServer(MessageConfig.GETTOTALPRODUCTS, -1);
        
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
        System.out.println("Shop");
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

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
                System.out.println("Repository: Error contacting Main Server..");
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
                System.out.println("Shop: Error contacting Main Server..");
                System.out.println(inMessage.toString());
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
            System.out.println("Shop: Error contacting Main Server..");
            System.out.println(inMessage.toString());
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
            System.out.println("Shop: Error contacting Main Server..");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
