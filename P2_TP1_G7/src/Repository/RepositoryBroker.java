package Repository;

import comInf.MessageException;
import comInf.MessageRepository;

/**
 *
 * @author Daniel
 */
class RepositoryBroker {

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
    public MessageRepository processAndReply(MessageRepository inMessage) throws MessageException {
        MessageRepository outMessage = null;                           // mensagem de resposta

        // validacao da mensagem recebida
        switch (inMessage.getType()) {
            case MessageRepository.SETCUSTOMERSTATE:
            case MessageRepository.SETCUSTINSHOP:
            case MessageRepository.SETGOODSBYCUST:
                // Customers Messages
                if ((inMessage.getCustId() < 0) || (inMessage.getCustId() >= nCustomers)) {
                    throw new MessageException("Invalid Customer Id!", inMessage);
                }
                break;
            case MessageRepository.SETCRAFTMANSTATE:
            case MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN:
                // Craftmans Messages
                if ((inMessage.getCraftId() < 0) || (inMessage.getCraftId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageRepository.cenasDaOwnerParaRepositorio:
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
            //*************** Customers Messages
            case MessageRepository.SETCUSTOMERSTATE:
                int custState = inMessage.getValue();
                repository.setCustomerState(inMessage.getCustId(), custState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETCUSTINSHOP:
                int nCustomersInsideShop = inMessage.getValue();
                repository.setnCustomersInsideShop(nCustomersInsideShop);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETGOODSBYCUST:
                int nGoods = inMessage.getValue();
                repository.setnGoodsByCustomer(inMessage.getCustId(), nGoods);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            //*************** Craftmans Messages
            case MessageRepository.SETCRAFTMANSTATE:
                int craftState = inMessage.getValue();
                repository.setCraftmanState(inMessage.getCraftId(), craftState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN:
                int nGoodsCraftedByCraftman = inMessage.getValue();
                repository.setnGoodsCraftedByCraftman(inMessage.getCraftId(), nGoodsCraftedByCraftman);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            //*************** Owner Messages
            case MessageRepository.cenasDaOwnerParaRepositorio:
                
                break;
        }

        return (outMessage);
    }
}
