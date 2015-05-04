package Craftman;

import MainServer.ServerInfo;
import comInf.MessageConfig;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CraftmanMain {
    public static void main(String[] args){
        // create and start n Craftmans
        int nCraftmans = contactMainServer(MessageConfig.GETNCRAFTMANS, -1);
        // Array de Craftmans
        Craftman[] craftman = new Craftman[nCraftmans];
        String repositoryHost = contactMainServer(MessageConfig.GETREPOSITORYHOST, "");
        int repositoryPort = contactMainServer(MessageConfig.GETREPOSITORYPORT, -1);
        String shopHost = contactMainServer(MessageConfig.GETSHOPHOST, "");
        int shopPort = contactMainServer(MessageConfig.GETSHOPPORT, -1);
        String factoryHost = contactMainServer(MessageConfig.GETFACTORYHOST, "");
        int factoryPort = contactMainServer(MessageConfig.GETFACTORYPORT, -1);
       
        // PORTAS: 221GX -> G = grupo 7 -> X 0-9
        CraftmanBroker broker = new CraftmanBroker(repositoryHost, repositoryPort, shopHost, shopPort, factoryHost, factoryPort);
        
        //Initialization of Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i] = new Craftman(i, broker, broker, broker);
        
        // Starting Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i].start();
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
                System.out.println("Repository: Error contacting Main Server..");
                System.out.println(inMessage.toString());
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
                System.out.println("Repository: Error contacting Main Server..");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
}
