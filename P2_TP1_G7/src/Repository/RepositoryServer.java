package Repository;

import genclass.GenericIO;
import MainServer.ServerInfo;
import comInf.MessageConfig;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class RepositoryServer {

    /**
     * Server Repository port number.
     *
     * @serialField portNumb
     */
    private static final int portNumb = 22170;
    
    public static void main(String[] args) throws UnknownHostException {
        // Aqui e onde corre o servidor e lanca threads(ClientProxy) para atender cada cliente.
        setRepositoryHostOnMainServer(MessageConfig.SETREPOSITORYHOST);
        setRepositoryPortOnMainServer(MessageConfig.SETREPOSITORYPORT);
        
        int nCustomers = contactMainServer(MessageConfig.GETNCUSTOMERS, -1);
        int nCraftmans = contactMainServer(MessageConfig.GETNCRAFTMANS, -1);
        int nPrimeMaterialsInFactory = contactMainServer(MessageConfig.GETNPRIMEMATERIALSINFACTORY, -1);
        String fName = contactMainServer(MessageConfig.GETFNAME, "");
        
        Repository repository;                                  // Repository (servico a ser prestado)
        RepositoryBroker repositoryInterface;                   // Interface a Repository
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico


        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        repository = new Repository(nCraftmans, nCustomers, fName, nPrimeMaterialsInFactory); // activacao do servico
        repositoryInterface = new RepositoryBroker(repository, nCustomers, nCraftmans);       // activacao do interface com o servico
        GenericIO.writelnString("Repository");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true){
            sconi = scon.accept();                              // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, repositoryInterface);    // lancamento do agente prestador do servico
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
                GenericIO.writelnString("Repository: Error contacting Main Server..");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    private static void setRepositoryHostOnMainServer(int msgType) throws UnknownHostException {
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
            GenericIO.writelnString("Repository: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    private static void setRepositoryPortOnMainServer(int msgType) {
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
            GenericIO.writelnString("Repository: Error contacting Main Server..");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
