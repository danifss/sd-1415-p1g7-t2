package Repository;

import comInf.MessageException;
import comInf.MessageRepository;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
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
            case MessageRepository.SETOWNERSTATE:
                // Owner Messages
                if(inMessage.getValue() < 0){
                    throw new MessageException("Invalid value!", inMessage);
                }
                break;
            case MessageRepository.SETCUSTOMERSTATE:
            case MessageRepository.SETGOODSBYCUST:
                // Customers Messages
                if ((inMessage.getId() < 0) || (inMessage.getId() >= nCustomers)) {
                    throw new MessageException("Invalid Customer Id!", inMessage);
                }
                break;
            case MessageRepository.SETCRAFTMANSTATE:
            case MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN:
                // Craftmans Messages
                if ((inMessage.getId() < 0) || (inMessage.getId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageRepository.SETSHOPSTATE:
            case MessageRepository.SETCUSTINSHOP:
            case MessageRepository.SETGOODSINDISP:
                // Shop Messages
                if(inMessage.getValue() < 0){
                    throw new MessageException("Invalid value!", inMessage);
                }
                break;
            case MessageRepository.SETTRANSPRODTOSHOP:
            case MessageRepository.SETSUPPLYMATTOFACT:
                break;
            case MessageRepository.SETPRIMEMATERIALSINFACT:
            case MessageRepository.SETFINISHEDPRODUCTSINFACT:
            case MessageRepository.SETSUPPLIEDTIMES:
            case MessageRepository.SETPRIMEMATSUPPLIED:
            case MessageRepository.SETPRODSMANUFACTURED:
                // Factory Messages
                if(inMessage.getValue() < 0){
                    throw new MessageException("Invalid value!", inMessage);
                }
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        // seu processamento
        switch (inMessage.getType()) {
            //*************** Owner Messages
            case MessageRepository.SETOWNERSTATE:
                int ownerState = inMessage.getValue();
                repository.setOwnerState(ownerState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            //*************** Customers Messages
            case MessageRepository.SETCUSTOMERSTATE:
                int custState = inMessage.getValue();
                repository.setCustomerState(inMessage.getId(), custState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETGOODSBYCUST:
                int nGoods = inMessage.getValue();
                repository.setnGoodsByCustomer(inMessage.getId(), nGoods);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            //*************** Craftmans Messages
            case MessageRepository.SETCRAFTMANSTATE:
                int craftState = inMessage.getValue();
                repository.setCraftmanState(inMessage.getId(), craftState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN:
                int nGoodsCraftedByCraftman = inMessage.getValue();
                repository.setnGoodsCraftedByCraftman(inMessage.getId(), nGoodsCraftedByCraftman);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break; 
            //*************** Shop Messages
            case MessageRepository.SETSHOPSTATE:
                int shopState = inMessage.getValue();
                repository.setShopState(shopState);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETCUSTINSHOP:
                int nCustomersInsideShop = inMessage.getValue();
                repository.setnCustomersInsideShop(nCustomersInsideShop);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETGOODSINDISP:
                int goodsInDisp = inMessage.getValue();
                repository.setnGoodsInDisplay(goodsInDisp);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETTRANSPRODTOSHOP:
                boolean transProdToShop = inMessage.isBool();
                repository.setTranfsProductsToShop(transProdToShop);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETSUPPLYMATTOFACT:
                boolean supplyMatToFact = inMessage.isBool();
                repository.setSupplyMaterialsToFactory(supplyMatToFact);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            //*************** Factory Messages
            case MessageRepository.SETPRIMEMATERIALSINFACT:
                int primeMaterialsInFact = inMessage.getValue();
                repository.setnPrimeMaterialsInFactory(primeMaterialsInFact);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETFINISHEDPRODUCTSINFACT:
                int finishedProductsInFact = inMessage.getValue();
                repository.setnFinishedProductsInFactory(finishedProductsInFact);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETSUPPLIEDTIMES:
                int suppliedTimes = inMessage.getValue();
                repository.setnSuppliedTimes(suppliedTimes);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETPRIMEMATSUPPLIED:
                int primeMatsSupplied = inMessage.getValue();
                repository.setnPrimeMaterialsSupplied(primeMatsSupplied);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
            case MessageRepository.SETPRODSMANUFACTURED:
                int prodsManufactured = inMessage.getValue();
                repository.setnProductsManufactured(prodsManufactured);
                outMessage = new MessageRepository(MessageRepository.ACK);
                break;
        }
        return (outMessage);
    }
}
