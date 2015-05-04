package Customer;

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
public class CustomerMain {
    public static void main(String[] args){
        //TODO: create and start 3 Customers
        int nCustomers = contactMainServer(MessageConfig.GETNCUSTOMERS, -1);
        // Array de Clientes
        Customer[] customer = new Customer[nCustomers];
        String shopHost = getHost(MessageConfig.GETSHOPHOST, -1);
        int shopPort = contactMainServer(MessageConfig.GETSHOPPORT, -1);
        String repositoryHost = getHost(MessageConfig.GETREPOSITORYHOST, -1);
        int repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        
        // PORTAS: 221GX -> G = grupo 7 -> X 0-9
        //CustomerBroker broker = new CustomerBroker("localhost", 22171, "localhost", 22170);
        CustomerBroker broker = new CustomerBroker(shopHost, shopPort, repositoryHost, repositoryPort);
        
        //Initialization of Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i] = new Customer(broker, i, broker);
        
        // Starting Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i].start();
    }
    
    private static int contactMainServer(int msgType, int value){
        //TODO: ligar ao Main Server e informar o seu ip e porta e pedir valores que necessita.
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
    
    private static String getHost(int msgType, int value){
        //TODO: ligar ao Main Server e informar o seu ip e porta e pedir valores que necessita.
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
        String result = null;
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
}
