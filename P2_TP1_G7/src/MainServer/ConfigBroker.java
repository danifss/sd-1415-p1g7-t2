package MainServer;

import comInf.MessageException;
import comInf.MessageConfig;

public class ConfigBroker {

	/**
	 * Configuration Data
	 * 
	 * @serialField data
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
                if((inMessage.getValue() != -1) || (!inMessage.getStr().equals(""))){
                    throw new MessageException("Invalid message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        int value = 0;
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
        }
        return (outMessage);
    }
}
