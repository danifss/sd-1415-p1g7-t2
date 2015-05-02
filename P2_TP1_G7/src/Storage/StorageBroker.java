package Storage;

import comInf.MessageException;
import comInf.MessageStorage;

/**
 *
 * @author Daniel
 */
class StorageBroker {
    
    /**
     * Storage Monitor
     * 
     * @serialField storage
     */
    private final Storage storage;
    
    /**
     * Constructor of Storage Broker
     * @param storage Storage Monitor
     */
    public StorageBroker(Storage storage) {
        this.storage = storage;
    }
    
    /**
     * Processing message through respectively task execution. Generation of a response message.
     *
     * @param inMessage message with request
     * @return response message
     * @throws MessageException if invalid message request
     */
    public MessageStorage processAndReply(MessageStorage inMessage) throws MessageException {
        MessageStorage outMessage = null;                           // mensagem de resposta

        // validacao da mensagem recebida
        switch (inMessage.getType()) {
            case MessageStorage.PRIMEMATERIALSAVAILABLE:
            case MessageStorage.VISITSUPPLIERS:
            case MessageStorage.GETNPRIMEMATERIALSDELIVERED:
            case MessageStorage.GETMAXPRIMEMATERIALSTODELIVER:
                if ((inMessage.getCustId()!= -1) && (inMessage.getCraftId() != -1)){
                    throw new MessageException("Invalid Owner message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        // seu processamento
        switch (inMessage.getType()) {
            //*************** Owner Messages
            case MessageStorage.PRIMEMATERIALSAVAILABLE:
                boolean result = storage.isPrimeMaterialsAvailabe();
                outMessage = new MessageStorage(MessageStorage.ACK, result);
                break;
            case MessageStorage.VISITSUPPLIERS:
                int primeMaterials = storage.visitSuppliers();
                outMessage = new MessageStorage(MessageStorage.ACK, primeMaterials);
                break;
            //*************** Other Messages
            case MessageStorage.GETNPRIMEMATERIALSDELIVERED:
                int delivered = storage.getnPrimeMaterialsDelivered();
                outMessage = new MessageStorage(MessageStorage.ACK, delivered);
                break;
            case MessageStorage.GETMAXPRIMEMATERIALSTODELIVER:
                int maxPrimeMaterials = storage.getnMaxPrimeMaterialsToDeliver();
                outMessage = new MessageStorage(MessageStorage.ACK, maxPrimeMaterials);
                break;
        }

        return (outMessage);
    }
}
