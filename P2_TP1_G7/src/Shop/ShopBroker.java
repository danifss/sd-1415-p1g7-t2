package Shop;

import comInf.MessageShop;
import comInf.MessageException;
import comInf.MessageRepository;
import genclass.GenericIO;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class ShopBroker implements ShopRepositoryInterface{

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
     * Shop Monitor
     *
     * @serialField shop
     */
    private final Shop shop;
    
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
     * Constructor of Shop Broker
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     * @param shop Shop Monitor Object
     * @param nCustomers Total Number of Customers
     */
    public ShopBroker(String RPserverHostName, int RPserverPortNumb, Shop shop, int nCustomers, int nCraftmans) {
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
        this.shop = shop;
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
    public MessageShop processAndReply(MessageShop inMessage) throws MessageException {
        MessageShop outMessage = null;                           // mensagem de resposta

        // validacao da mensagem recebida
        switch (inMessage.getType()) {
            case MessageShop.CHKDOOROPEN:
            case MessageShop.ENTERSHOP:
            case MessageShop.PERUSINGAROUND:
            case MessageShop.IWANTTHIS:
            case MessageShop.EXITSHOP:
            case MessageShop.ENDOPER:
                // Customers Messages
                if ((inMessage.getCustId() < 0) || (inMessage.getCustId() >= nCustomers)) {
                    throw new MessageException("Invalid Customer Id!", inMessage);
                }
                break;
            case MessageShop.PRIMEMATERIALSNEEDED:
            case MessageShop.READYFORTRANSFER:
                // Craftmans Messages
                if ((inMessage.getCraftId() < 0) || (inMessage.getCraftId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageShop.cenasDaOwnerParaAShop:
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
            case MessageShop.CHKDOOROPEN:
                if(shop.isDoorOpen()) // Customer sees if door is open
                    outMessage = new MessageShop(MessageShop.DOOROPEN);
                else
                    outMessage = new MessageShop(MessageShop.DOORCLOSED);
                break;
            case MessageShop.ENTERSHOP:
                shop.enterShop(); // customer enters in the Shop
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.PERUSINGAROUND:
                int nGoods = shop.perusingAround(); // Customer chooses what to buy
                outMessage = new MessageShop(MessageShop.ACK, inMessage.getCustId(), nGoods);
                break;
            case MessageShop.IWANTTHIS:
                int goodsToBuy = inMessage.getValue();
                shop.iWantThis(inMessage.getCustId(), goodsToBuy); // Customer goes to queue
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.EXITSHOP:
                shop.exitShop(); // Customer exits Shop
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.ENDOPER:
                boolean result = shop.endOper();
                outMessage = new MessageShop(MessageShop.ACK, inMessage.getCustId(), result);
                break;
            //*************** Craftmans Messages
            case MessageShop.PRIMEMATERIALSNEEDED:
                shop.primeMaterialsNeeded();
                outMessage = new MessageShop(MessageShop.ACK,inMessage.getCraftId());
                break;
            case MessageShop.READYFORTRANSFER:
                shop.batchReadyForTransfer();
                outMessage = new MessageShop(MessageShop.ACK,inMessage.getCraftId());
                break;
            //*************** Owner Messages
                //TODO: Owner cases
        }

        return (outMessage);
    }

    @Override
    public void setTranfsProductsToShop(boolean tranfsProductsToShop) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETTRANSPRODTOSHOP, tranfsProductsToShop);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting the boolean to transfer products to Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    @Override
    public void setnGoodsInDisplay(int nGoodsInDisplay) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETGOODSINDISP, -1, nGoodsInDisplay);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting number of goods in display.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    @Override
    public void setSupplyMaterialsToFactory(boolean supplyMaterialsToFactory) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETSUPPLYMATTOFACT, supplyMaterialsToFactory);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting boolean to supply materials to Factory.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    @Override
    public void setnCustomersInsideShop(int nCustomersInsideShop) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETCUSTINSHOP, -1, nCustomersInsideShop);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting number of customers inside Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    @Override
    public void setShopState(int state) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETSHOPSTATE, -1, state);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting the state of the Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
