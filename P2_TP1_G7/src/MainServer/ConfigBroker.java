package MainServer;

import comInf.MessageException;
import comInf.MessageConfig;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */

public class ConfigBroker {

    /**
     * Configuration Data
     * 
     * @serial data
     */
    private ConfigData data;


    /**
     * Configurations Broker Constructor
     * @param data Configurations Data
     */
    public ConfigBroker(ConfigData data){
            this.data = data;
    }

    /**
     * Processing message through respectively task execution. Generation of a response message.
     *
     * @param inMessage message with request
     * @return response message
     * @throws MessageException if invalid message request
     */
    public MessageConfig processAndReply(MessageConfig inMessage) throws MessageException {
        MessageConfig outMessage = null;                           // mensagem de resposta

        // validacao da mensagem recebida
        switch (inMessage.getType()) {
            case MessageConfig.GETFNAME:
            case MessageConfig.GETNCRAFTMANS:
            case MessageConfig.GETNCUSTOMERS:
            case MessageConfig.GETNPRIMEMATERIALSINFACTORY:
            case MessageConfig.GETNINITIALPRODUCTSINSHOP:
            case MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE:
            case MessageConfig.GETNPRIMEMATERIALSBYPRODUCT:
            case MessageConfig.GETNMAXPRODUCTSCOLLECT:
            case MessageConfig.GETNMINPRIMEMATERIALSFORRESTOCK:
            case MessageConfig.GETTOTALPRODUCTS:
            // REPOSITORY
            case MessageConfig.GETREPOSITORYHOST:
            case MessageConfig.GETREPOSITORYPORT:
            // SHOP
            case MessageConfig.GETSHOPHOST:
            case MessageConfig.GETSHOPPORT:
            // STORAGE
            case MessageConfig.GETSTORAGEHOST:
            case MessageConfig.GETSTORAGEPORT:
            // FACTORY
            case MessageConfig.GETFACTORYHOST:
            case MessageConfig.GETFACTORYPORT:
                if((inMessage.getValue() != -1) || (!inMessage.getStr().equals(""))){
                    throw new MessageException("Invalid message!", inMessage);
                }
                break;
            case MessageConfig.SETREPOSITORYHOST:
            case MessageConfig.SETSHOPHOST:
            case MessageConfig.SETSTORAGEHOST:    
            case MessageConfig.SETFACTORYHOST:
                if(inMessage.getStr().equals("")){
                    throw new MessageException("Invalid message!", inMessage);
                }
                break;
            case MessageConfig.SETREPOSITORYPORT:
            case MessageConfig.SETSHOPPORT:
            case MessageConfig.SETSTORAGEPORT:
            case MessageConfig.SETFACTORYPORT:
                if(inMessage.getValue() == -1){
                    throw new MessageException("Invalid message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        int value = 0;
        String str = "";
        // seu processamento
        switch (inMessage.getType()) {
            //*************** Owner Messages
            case MessageConfig.GETFNAME:
                String fName = data.getfName();
                outMessage = new MessageConfig(MessageConfig.ACK, fName);
                break;
            case MessageConfig.GETNCRAFTMANS:
                value = data.getnCraftmans();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNCUSTOMERS:
                value = data.getnCustomers();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNPRIMEMATERIALSINFACTORY:
                value = data.getnPrimeMaterialsInFactory();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNINITIALPRODUCTSINSHOP:
                value = data.getnInitialProductsInShop();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNINITIALPRIMEMATERIALSINSTORAGE:
                value = data.getnInitialPrimeMaterialsInStorage();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNPRIMEMATERIALSBYPRODUCT:
                value = data.getnPrimeMaterialsByProduct();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNMAXPRODUCTSCOLLECT:
                value = data.getnMaxProductsCollect();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETNMINPRIMEMATERIALSFORRESTOCK:
                value = data.getnMinPrimeMaterialsForRestock();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETTOTALPRODUCTS:
                value = data.gettotalProducts();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            //****************************************
            case MessageConfig.GETREPOSITORYHOST:
                str = data.getRepositoryHostName();
                outMessage = new MessageConfig(MessageConfig.ACK, str);
                break;
            case MessageConfig.GETREPOSITORYPORT:
                value = data.getRepositoryPortNum();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETSHOPHOST:
                str = data.getShopHostName();
                outMessage = new MessageConfig(MessageConfig.ACK, str);
                break;
            case MessageConfig.GETSHOPPORT:
                value = data.getShopPortNum();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETSTORAGEHOST:
                str = data.getStorageHostName();
                outMessage = new MessageConfig(MessageConfig.ACK, str);
                break;
            case MessageConfig.GETSTORAGEPORT:
                value = data.getStoragePortNum();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.GETFACTORYHOST:
                str = data.getFactoryHostName();
                outMessage = new MessageConfig(MessageConfig.ACK, str);
                break;
            case MessageConfig.GETFACTORYPORT:
                value = data.getFactoryPortNum();
                outMessage = new MessageConfig(MessageConfig.ACK, value);
                break;
            case MessageConfig.SETREPOSITORYHOST:
                str = inMessage.getStr();
                data.setRepositoryHostName(str);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETSHOPHOST:
                str = inMessage.getStr();
                data.setShopHostName(str);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETSTORAGEHOST:
                str = inMessage.getStr();
                data.setStorageHostName(str);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETFACTORYHOST:
                str = inMessage.getStr();
                data.setFactoryHostName(str);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETREPOSITORYPORT:
                value = inMessage.getValue();
                data.setRepositoryPortNum(value);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETSHOPPORT:
                value = inMessage.getValue();
                data.setShopPortNum(value);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETSTORAGEPORT:
                value = inMessage.getValue();
                data.setStoragePortNum(value);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
            case MessageConfig.SETFACTORYPORT:
                value = inMessage.getValue();
                data.setFactoryPortNum(value);
                outMessage = new MessageConfig(MessageConfig.ACK);
                break;
        }
        return (outMessage);
    }
}
