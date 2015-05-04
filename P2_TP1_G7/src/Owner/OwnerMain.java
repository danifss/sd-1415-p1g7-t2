package Owner;

import genclass.GenericIO;
import MainServer.ServerInfo;
import comInf.MessageConfig;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class OwnerMain {
    public static void main(String[] args){
        Owner owner;
        String shopHost = contactMainServer(MessageConfig.GETSHOPHOST, "");
        int shopPort = contactMainServer(MessageConfig.GETSHOPPORT, -1);
        String repositoryHost = contactMainServer(MessageConfig.GETREPOSITORYHOST, "");
        int repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        String factoryHost = contactMainServer(MessageConfig.GETFACTORYHOST, "");
        int factoryPort = contactMainServer(MessageConfig.GETFACTORYPORT, -1);
        String storageHost = contactMainServer(MessageConfig.GETSTORAGEHOST, "");
        int storagePort = contactMainServer(MessageConfig.GETSTORAGEPORT, -1);
        
        OwnerBroker broker = new OwnerBroker(shopHost, shopPort, repositoryHost, repositoryPort, factoryHost, factoryPort, storageHost, storagePort);
        
        owner = new Owner(broker, broker, broker, broker);
        
        owner.start();
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
    
    private static String contactMainServer(int msgType, String str){
        //Jigar ao Main Server e pedir valores que necessita.
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
}
