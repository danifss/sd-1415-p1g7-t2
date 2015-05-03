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
                if((inMessage.getValue() != -1) || (!inMessage.getStr().equals(""))){
                    throw new MessageException("Invalid message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        // seu processamento
        switch (inMessage.getType()) {
            //*************** Owner Messages
            case MessageConfig.GETFNAME:
                String fName = data.getfName();
                outMessage = new MessageConfig(MessageConfig.ACK, fName);
                break;
        }
        return (outMessage);
    }
}
