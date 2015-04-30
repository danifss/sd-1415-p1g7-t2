package Storage;

import comInf.MessageException;
import comInf.MessageStorage;

/**
 *
 * @author Daniel
 */
class StorageBroker {
    
    public MessageStorage processAndReply(MessageStorage inMessage) throws MessageException{
        throw new MessageException("ERRO",new MessageStorage(0));
    }
}
