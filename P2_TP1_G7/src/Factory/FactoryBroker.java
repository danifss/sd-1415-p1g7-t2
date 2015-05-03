package Factory;

import comInf.MessageException;
import comInf.MessageFactory;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class FactoryBroker {

    /**
     * Repository server host name
     * @serialField RPserverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serialField RPserverPortNumb
     */
    private int RPserverPortNumb;
    
    /**
     * Total number of Craftmans
     * @serialField nCraftmans
     */
    private final int nCraftmans;
    
    /**
     * Factory Monitor Object
     * @serialField factory
     */
    private final Factory factory;

    /**
     * Constructor of Factory Broker
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     * @param nCraftmans            Number of Craftmans
     */
    public FactoryBroker(Factory factory, String RPserverHostName, int RPserverPortNumb, int nCraftmans) {
        this.factory = factory;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
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
                // Tem de se validar com o custId devido a forma como esta feito o Message.
                if ((inMessage.getId()< 0) || (inMessage.getId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageFactory.GOTOWORKSHOP:
                // Owner Messages
                if (inMessage.getId()!= -1 || inMessage.getId() != -1){
                    throw new MessageException("Invalid Owner message!", inMessage);
                }
                break;
            case MessageFactory.REPLENISHSTOCK:
                // Owner Messages
                if (inMessage.getId()!= -1 || inMessage.getId() != -1  || inMessage.getValue()<0){
                    throw new MessageException("Invalid Owner message!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        boolean result = false;
        int value = -1;
        int id = inMessage.getId(); // Tem de se usar o custId
        // seu processamento
        switch (inMessage.getType()) {
            //*************** Craftmans Messages
            case MessageFactory.CHECKFORRESTOCK:
                result = factory.checkForRestock();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            case MessageFactory.CHECKFORMATERIALS:
                result = factory.checkForMaterials();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            case MessageFactory.COLLECTMATERIALS:
                value = factory.collectMaterials();
                outMessage = new MessageFactory(MessageFactory.ACK, id, value);
                break;
            case MessageFactory.GOTOSTORE:
                value = factory.goToStore(inMessage.getValue());
                outMessage = new MessageFactory(MessageFactory.ACK, id, value);
                break;
            case MessageFactory.READYFORTRANSFER:
                factory.batchReadyForTransfer();
                outMessage = new MessageFactory(MessageFactory.ACK, id);
                break;
            case MessageFactory.CHECKCONTACTPRODUCT:
                result = factory.checkContactProduct();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            case MessageFactory.PRIMEMATERIALSNEEDED:
                result = factory.primeMaterialsNeeded();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            case MessageFactory.FLAGPRIMEACTIVATED:
                result = factory.flagPrimeActivated();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            case MessageFactory.GETNPRIMEPERPRODUCT:
                value = factory.getnPrimePerProduct();
                outMessage = new MessageFactory(MessageFactory.ACK, id, value);
                break;
            case MessageFactory.ENDOFPRIMEMATERIALS:
                result = factory.endOfPrimeMaterials();
                outMessage = new MessageFactory(MessageFactory.ACK, id, result);
                break;
            //*************** Owner Messages
            case MessageFactory.GOTOWORKSHOP:
                value = factory.goToWorkshop();
                outMessage = new MessageFactory(MessageFactory.ACK, id, value);
                break;
            case MessageFactory.REPLENISHSTOCK:
                factory.replenishStock(inMessage.getValue());
                outMessage = new MessageFactory(MessageFactory.ACK);
                break;
        }

        return (outMessage);
    }

}
