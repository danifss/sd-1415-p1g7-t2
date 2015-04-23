package comInf;

/**
 *
 * @author Daniel
 */
public class MessageShop extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Message type: Customer enter in the shop
     * @serialField ENTERSHOP
     */
    public static final int ENTERSHOP = 1;
    
    /**
     * Message type: Answer to Customer enter in the shop
     * @serialField ENTERSHOPANSWER
     */
    public static final int ENTERSHOPANSWER = 2;
    
    
    /**
     * Message Shop constructor
     * @param type Message type
     */
    public MessageShop(int type, int customerId) {
        super(type);
    }

    
}
