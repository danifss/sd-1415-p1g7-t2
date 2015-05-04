package comInf;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageShop extends Message {

    /**
     * Serialization key.
     * 
     * @serial serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** CUSTOMERS MESSAGE TYPES
    /**
     * Message type: Customer check if door is open.
     * @serial CHKDOOROPEN
     */
    public static final int CHKDOOROPEN = 1;
    
    /**
     * Message type: Answer to Customer that door is open.
     * @serial DOOROPEN
     */
    public static final int DOOROPEN = 2;
    
    /**
     * Message type: Answer to Customer that door is closed.
     * @serial DOORCLOSED
     */
    public static final int DOORCLOSED = 3;
    
    /**
     * Message type: Customer enter in the Shop.
     * @serial ENTERSHOP
     */
    public static final int ENTERSHOP = 4;
    
    /**
     * Message type: Customer perusingAround in Shop.
     * @serial PERUSINGAROUND
     */
    public static final int PERUSINGAROUND = 5;
    
    /**
     * Message type: Customer goes to queue to buy the products.
     * @serial IWANTTHIS
     */
    public static final int IWANTTHIS = 6;
    
    /**
     * Message type: Customer leaves the shop.
     * @serial EXITSHOP
     */
    public static final int EXITSHOP = 7;
    
    /**
     * Message type: Customer will check if can die.
     * @serial ENDOPER
     */
    public static final int ENDOPER = 8;
    
    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Message type: Craftman indicates that prime materials are needed.
     * @serial PRIMEMATERIALSNEEDED
     */
    public static final int PRIMEMATERIALSNEEDED = 9;
    
    /**
     * Message type: Craftman indicates that the Owner can go to factory to collect products.
     * @serial READYFORTRANSFER
     */
    public static final int READYFORTRANSFER = 10;
    
    
    //*************** OWNER MESSAGE TYPES
    /**
     * Message type: Owner opens the shop.
     * @serial OPENTHESHOP
     */
    public static final int OPENTHESHOP = 30;
    
    /**
     * Message type: Owner checks if the Factory needs prime materials.
     * @serial ISSUPPLYMATERIALSTOFACT
     */
    public static final int ISSUPPLYMATERIALSTOFACT = 31;
    
    /**
     * Message type: Owner checks if the he can collect products in the Factory.
     * @serial ISTRANFSPRODSTOSHOP
     */
    public static final int ISTRANFSPRODSTOSHOP = 32;
    
    /**
     * Message type: Owner sees the situation of the shop and decide what to do.
     * @serial APPRAISESIT
     */
    public static final int APPRAISESIT = 33;
    
    /**
     * Message type: Owner closes the door.
     * @serial CLOSETHEDOOR
     */
    public static final int CLOSETHEDOOR = 34;
    
    /**
     * Message type: Owner sees if there is customers inside the shop.
     * @serial CUSTOMERSINTHESHOP
     */
    public static final int CUSTOMERSINTHESHOP = 35;
    
    /**
     * Message type: Owner address a Customer on the queue.
     * @serial ADDRESSACUSTOMER
     */
    public static final int ADDRESSACUSTOMER = 36;
    
    /**
     * Message type: Owner services a customer.
     * @serial SERVICECUSTOMER
     */
    public static final int SERVICECUSTOMER = 37;
    
    /**
     * Message type: Owner says goodbye to the Customer he is attending.
     * @serial SAYGOODBYETOCUST
     */
    public static final int SAYGOODBYETOCUST = 38;
    
    /**
     * Message type: Owner sees if the shop is on state STILL_OPEN.
     * @serial ISSHOPSTILLOPEN
     */
    public static final int ISSHOPSTILLOPEN = 39;
    
    /**
     * Message type: Owner goes to the Factory to collect products.
     * @serial GOTOWORKSHOP
     */
    public static final int GOTOWORKSHOP = 40;
    
    /**
     * Message type: Owner updates the number of products that the shop is selling.
     * @serial ADDNGOODSINDISPLAY
     */
    public static final int ADDNGOODSINDISPLAY = 41;
    
    /**
     * Message type: Owner goes to Factory to restock prime materials.
     * @serial REPLENISHSTOCK
     */
    public static final int REPLENISHSTOCK = 42;
    
    /**
     * Message type: Verifies if the Owner can stop working.
     * @serial ENDOPEROWNER
     */
    public static final int ENDOPEROWNER = 43;
    
    
    //*************** Internal variables
    /**
     * Variable to save a integer value.
     * @serial value
     */
    private int value = 0;
    
    /**
     * Variable to save a boolean value.
     * @serial bool
     */
    private boolean bool = false;
    
    /**
     * Variable to see if Customer can die or not.
     * @serial endOper
     */
    private boolean endOper = false;
    
    
    //*************** Constructors
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
     * @param id ID
     */
    public MessageShop(int type, int id) {
        super(type, id);
    }
    
    /**
     * Message Shop constructor (form 3)
     * @param type Message type
     * @param id ID
     * @param value Variable value
     */
    public MessageShop(int type, int id, int value) {
        super(type, id);
        this.value = value;
    }
    
    /**
     * Message Shop constructor (form 4)
     * @param type Message type
     * @param id ID
     * @param endOper End of Operation (true/false)
     */
    public MessageShop(int type, int id, boolean endOper){
        super(type, id);
        this.endOper = endOper;
        this.bool = endOper;
    }

    
    //*************** Internal Functions
    /**
     * Get a generic integer value.
     * @return value variable
     */
    public int getValue() {
        return value;
    }

    /**
     * Check if can die or not.
     * @return if can die.
     */
    public boolean isEndOper() {
        return endOper;
    }
    
    /**
     * Get generic boolean variable.
     * @return boolean value of variable bool
     */
    public boolean isBool(){
        return bool;
    }
    
}
