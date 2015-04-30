package Factory;

import comInf.MessageException;
import comInf.MessageFactory;

/**
 *
 * @author Daniel
 */
public class FactoryBroker {

    public MessageFactory processAndReply(MessageFactory inMessage) throws MessageException{
        throw new MessageException("ERRO",new MessageFactory(0));
    }

}
