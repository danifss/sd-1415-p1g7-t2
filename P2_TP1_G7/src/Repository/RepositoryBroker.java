package Repository;

import comInf.MessageException;
import comInf.MessageRepository;

/**
 *
 * @author Daniel
 */
class RepositoryBroker {

    public MessageRepository processAndReply(MessageRepository inMessage) throws MessageException{
        throw new MessageException("ERRO",new MessageRepository(0));
    }
}
