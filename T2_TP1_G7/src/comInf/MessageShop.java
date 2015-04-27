package comInf;

/**
 *
 * @author Daniel
 */
public class MessageShop extends Message {

    /**
     * Serialization key.
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Message type: Customer check if door is open.
     * @serialField CHKDOOROPEN
     */
    public static final int CHKDOOROPEN = 1;
    
    /**
     * Message type: Answer to Customer that door is open.
     * @serialField DOOROPEN
     */
    public static final int DOOROPEN = 2;
    
    /**
     * Message type: Answer to Customer that door is closed.
     * @serialField DOORCLOSED
     */
    public static final int DOORCLOSED = 3;
    
    /**
     * Message type: Customer enter in the Shop.
     * @serialField ENTERSHOP
     */
    public static final int ENTERSHOP = 4;
    
    /**
     * Message type: Customer perusingAround in Shop.
     * @serialField PERUSINGAROUND
     */
    public static final int PERUSINGAROUND = 5;
    
    /**
     * Message type: Customer goes to queue to buy the products.
     * @serialField IWANTTHIS
     */
    public static final int IWANTTHIS = 6;
    
    /**
     * Message type: Customer leaves the shop.
     * @serialField EXITSHOP
     */
    public static final int EXITSHOP = 7;
    
    /**
     * Message type: Customer will check if can die.
     * @serialField ENDOPER
     */
    public static final int ENDOPER = 8;
    
    
    // Internal variables
    /**
     * Variable to save a integer value.
     * @serialField value
     */
    private int value = 0;
    
    /**
     * Variable to see if Customer can die or not.
     * @serialField endOper
     */
    private boolean endOper = false;
    
    
    // Constructors
    /**
     * Message Shop constructor (form 1)
     * @param type Message type
     */
    public MessageShop(int type) {
        super(type);
    }

    /**
     * Message Shop constructor (form 2)
     * @param type Message type
     * @param customerId Customer ID
     */
    public MessageShop(int type, int customerId) {
        super(type, customerId);
    }
    
    /**
     * Message Shop constructor (form 3)
     * @param type Message type
     * @param customerId Customer ID
     * @param value Variable value
     */
    public MessageShop(int type, int customerId, int value) {
        super(type, customerId);
        this.value = value;
    }
    
    /**
     * Message Shop constructor (form 4)
     * @param type Message type
     * @param customerId Customer ID
     * @param endOper End of Operation (true/false)
     */
    public MessageShop(int type, int customerId, boolean endOper){
        super(type, customerId);
        this.endOper = endOper;
    }

    
    // Internal Functions
    /**
     * Get the number of products that customer wants to buy
     * @return value Number of products to Customer buying
     */
    public int getValue() {
        return value;
    }

    /**
     * Get if customer can die or not.
     * @return if Customer can die.
     */
    public boolean isEndOper() {
        return endOper;
    }
    
}
