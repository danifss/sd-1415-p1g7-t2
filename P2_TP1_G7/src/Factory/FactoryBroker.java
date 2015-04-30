package Factory;

import comInf.MessageException;
import comInf.MessageFactory;

/**
 *
 * @author Daniel
 */
public class FactoryBroker {

    /**
     * Repository Monitor
     *
     * @serialField shop
     */
    private final Repository repository;
    
    /**
     * Total number of Customers
     * @serialField nCustomers
     */
    private final int nCustomers;
    
    /**
     * Total number of Craftmans
     * @serialField nCraftmans
     */
    private final int nCraftmans;

    /**
     * Constructor of Repository Broker
     *
     * @param shop Repository Monitor Object
     * @param nCustomers Total Number of Customers
     */
    public RepositoryBroker(Repository repository, int nCustomers, int nCraftmans) {
        this.repository = repository;
        this.nCustomers = nCustomers;
        this.nCraftmans = nCraftmans;
    }

    /**
     * Processing message through respectively task execution. Generation of a response message.
     *
     * @param inMessage message with request
     * @return response message
     * @throws MessageException if invalid message request
     */
    public MessageFactory processAndReply(MessageFactory inMessage) throws MessageException {
        MessageFactory outMessage = null;                           // mensagem de resposta

        // validacao da mensagem recebida
        switch (inMessage.getType()) {
            case MessageFactory.CHECKFORRESTOCK:
            case MessageFactory.CHECKFORMATERIALS:
            case MessageFactory.COLLECTMATERIALS:
            case MessageFactory.GOTOSTORE:
            case MessageFactory.READYFORTRANSFER:
            case MessageFactory.CHECKCONTACTPRODUCT:
            case MessageFactory.PRIMEMATERIALSNEEDED:
            case MessageFactory.FLAGPRIMEACTIVATED:
            case MessageFactory.GETNPRIMEPERPRODUCT:
            case MessageFactory.ENDOFPRIMEMATERIALS:
                // Craftmans Messages
                if ((inMessage.getCraftId() < 0) || (inMessage.getCraftId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageFactory.GOTOWORKSHOP:
            case MessageFactory.REPLENISHSTOCK:
                // Owner Messages
                if ((inMessage.getCustId()!= -1) && (inMessage.getCraftId() != -1)){
                    throw new MessageException("Invalid Owner message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        // seu processamento
        switch (inMessage.getType()) {
            //*************** Craftmans Messages
            case MessageFactory.CHECKFORMATERIALS:
                break;
            case MessageFactory.COLLECTMATERIALS:
                break;
            case MessageFactory.GOTOSTORE:
                break;
            case MessageFactory.READYFORTRANSFER:
                break;
            case MessageFactory.CHECKCONTACTPRODUCT:
                break;
            case MessageFactory.PRIMEMATERIALSNEEDED:
                break;
            case MessageFactory.FLAGPRIMEACTIVATED:
                break;
            case MessageFactory.GETNPRIMEPERPRODUCT:
                break;
            case MessageFactory.ENDOFPRIMEMATERIALS:
                break;
            //*************** Owner Messages
            case MessageFactory.GOTOWORKSHOP:
                break;
            case MessageFactory.REPLENISHSTOCK:
                break;
        }

        return (outMessage);
    }
}
