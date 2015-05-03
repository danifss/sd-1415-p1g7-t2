package Shop;

import comInf.MessageShop;
import comInf.MessageException;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class ShopBroker {
    
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
     * @param shop Shop Monitor Object
     * @param nCustomers Total Number of Customers
     */
    public ShopBroker(Shop shop, int nCustomers, int nCraftmans) {
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
                if ((inMessage.getId() < 0) || (inMessage.getId() >= nCustomers)) {
                    throw new MessageException("Invalid Customer Id!", inMessage);
                }
                break;
            case MessageShop.PRIMEMATERIALSNEEDED:
            case MessageShop.READYFORTRANSFER:
                // Craftmans Messages
                if ((inMessage.getId() < 0) || (inMessage.getId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageShop.ADDNGOODSINDISPLAY:
                // Owner Messages
                if (inMessage.getId()!= -1 || inMessage.getId() != -1 || inMessage.getValue() < 0){
                    throw new MessageException("Invalid Owner message!", inMessage);
                }
                break;
            case MessageShop.OPENTHESHOP:
            case MessageShop.APPRAISESIT:
            case MessageShop.CLOSETHEDOOR:
            case MessageShop.CUSTOMERSINTHESHOP:
            case MessageShop.ADDRESSACUSTOMER:
            case MessageShop.SERVICECUSTOMER:
            case MessageShop.SAYGOODBYETOCUST:
            case MessageShop.ISSHOPSTILLOPEN:
            case MessageShop.GOTOWORKSHOP:
            case MessageShop.REPLENISHSTOCK:
            case MessageShop.ISSUPPLYMATERIALSTOFACT:
            case MessageShop.ISTRANFSPRODSTOSHOP:
            case MessageShop.ENDOPEROWNER:
                // Owner Messages
                if (inMessage.getId()!= -1 || inMessage.getId() != -1){
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
                outMessage = new MessageShop(MessageShop.ACK, inMessage.getId(), nGoods);
                break;
            case MessageShop.IWANTTHIS:
                int goodsToBuy = inMessage.getValue();
                shop.iWantThis(inMessage.getId(), goodsToBuy); // Customer goes to queue
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.EXITSHOP:
                shop.exitShop(); // Customer exits Shop
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.ENDOPER:
                boolean result = shop.endOper();
                outMessage = new MessageShop(MessageShop.ACK, inMessage.getId(), result);
                break;
            //*************** Craftmans Messages
            case MessageShop.PRIMEMATERIALSNEEDED:
                shop.primeMaterialsNeeded();
                outMessage = new MessageShop(MessageShop.ACK,inMessage.getId());
                break;
            case MessageShop.READYFORTRANSFER:
                shop.batchReadyForTransfer();
                outMessage = new MessageShop(MessageShop.ACK,inMessage.getId());
                break;
            //*************** Owner Messages
            case MessageShop.ADDNGOODSINDISPLAY:
                shop.addnGoodsInDisplay(inMessage.getValue());
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.OPENTHESHOP:
                shop.openTheDoor();
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.APPRAISESIT:
                int custAppId = shop.appraiseSit();
                outMessage = new MessageShop(MessageShop.ACK, -1, custAppId);
                break;
            case MessageShop.CLOSETHEDOOR:
                shop.closeTheDoor();
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.CUSTOMERSINTHESHOP:
                boolean custInTheShop = shop.customersInTheShop();
                outMessage = new MessageShop(MessageShop.ACK, -1, custInTheShop);
                break;
            case MessageShop.ADDRESSACUSTOMER:
                int custAddId = shop.addressACustomer();
                outMessage = new MessageShop(MessageShop.ACK, -1, custAddId);
                break;
            case MessageShop.SERVICECUSTOMER:
                int nGoodsCustIsBuying = shop.serviceCustomer();
                outMessage = new MessageShop(MessageShop.ACK, -1, nGoodsCustIsBuying);
                break;
            case MessageShop.SAYGOODBYETOCUST:
                shop.sayGoodByeToCustomer();
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.ISSHOPSTILLOPEN:
                boolean shopStill = shop.isShopStillOpen();
                outMessage = new MessageShop(MessageShop.ACK, -1, shopStill);
                break;
            case MessageShop.GOTOWORKSHOP:
                shop.goToWorkshop();
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.REPLENISHSTOCK:
                shop.replenishStock();
                outMessage = new MessageShop(MessageShop.ACK);
                break;
            case MessageShop.ISSUPPLYMATERIALSTOFACT:
                boolean isSupplyMatToFact = shop.isSupplyMaterialsToFactory();
                outMessage = new MessageShop(MessageShop.ACK, -1, isSupplyMatToFact);
                break;
            case MessageShop.ISTRANFSPRODSTOSHOP:
                boolean isTranfsProdsToShop = shop.isTranfsProductsToShop();
                outMessage = new MessageShop(MessageShop.ACK, -1, isTranfsProdsToShop);
                break;
            case MessageShop.ENDOPEROWNER:
                boolean endOperOwner = shop.endOper();
                outMessage = new MessageShop(MessageShop.ACK, -1, endOperOwner);
                break;
        }

        return (outMessage);
    }
}
