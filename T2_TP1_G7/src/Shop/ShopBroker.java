package Shop;

import comInf.MessageShop;
import comInf.MessageException;

/**
 *
 * @author Daniel
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
     *
     * @param shop Shop Monitor Object
     * @param nCustomers Total Number of Customers
     */
    public ShopBroker(Shop shop, int nCustomers, int nCraftmans) {
        this.shop = shop;
        this.nCustomers = nCustomers;
        this.nCraftmans = nCraftmans;
    }

    /**
     * Processamento das mensagens através da execução da tarefa correspondente. Geração de uma
     * mensagem de resposta.
     *
     * @param inMessage mensagem com o pedido
     * @return mensagem de resposta
     * @throws MessageException se a mensagem com o pedido for considerada inválida
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
                if ((inMessage.getCustId() < 0) || (inMessage.getCustId() >= nCustomers)) {
                    throw new MessageException("Invalid Customer Id!", inMessage);
                }
                break;
            case MessageShop.cenasDoCraftmanParaAShop:
                if ((inMessage.getCraftId() >= nCraftmans)) {
                    throw new MessageException("Invalid Craftman Id!", inMessage);
                }
                break;
            case MessageShop.cenasDaOwnerParaAShop:
                
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        // seu processamento
        switch (inMessage.getType()) {
            case MessageShop.CHKDOOROPEN:
                if(shop.isDoorOpen())
                    outMessage = new MessageShop(MessageShop.DOOROPEN);
                else
                    outMessage = new MessageShop(MessageShop.DOORCLOSED);
                break;
            case MessageShop.ENTERSHOP:
                shop.enterShop(); // customer enters in the Shop
                outMessage = new MessageShop(MessageShop.ACK); // Sends an acknowledge
                break;
            case MessageShop.PERUSINGAROUND:
                int nGoods = shop.perusingAround();
                outMessage = new MessageShop(MessageShop.ACK,inMessage.getCustId(),nGoods);
                break;
            case MessageShop.IWANTTHIS:
                break;
            case MessageShop.EXITSHOP:
                break;
            case MessageShop.ENDOPER:
                break;
        }

        return (outMessage);
    }
}
