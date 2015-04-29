package comInf;

/**
 *
 * @author Daniel
 */
public class MessageRepository extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** CUSTOMERS MESSAGE TYPES
    /**
     * Message type: Set Customer State in Repository.
     * @serialField SETCUSTOMERSTATE
     */
    public static final int SETCUSTOMERSTATE = 1;
    
    /**
     * Message type: Set number of Customers inside the Shop.
     * @serialField SETCUSTINSHOP
     */
    public static final int SETCUSTINSHOP = 2;
    
    /**
     * Message type: Set number of goods by Customer
     * @serialField SETGOODSBYCUST
     */
    public static final int SETGOODSBYCUST = 3;
    
    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Message type: Set Craftman Present State
     * @serialField SETCRAFTMANSTATE
     */
    public static final int SETCRAFTMANSTATE = 4;
    
    /**
     * Message type: Set total number of products crafted by Craftman
     * @serialField SETGOODSCRAFTEDBYCRAFTMAN
     */
    public static final int SETGOODSCRAFTEDBYCRAFTMAN = 5;
    
    
    //*************** Internal variables
    /**
     * Value of something in integer.
     * @serialField value
     */
    private int value;
    
    /**
     * Number of Customers inside the Shop.
     * @serialField nCustomersInsideShop
     */
    private int nCustomersInsideShop;
    
    
    //*************** Constructors
    /**
     * Message Repository constructor (form 1)
     * @param type Message type
     */
    public MessageRepository(int type) {
        super(type);
    }
    
    /**
     * Message Repository constructor (form 2)
     * @param type Message type
     * @param customerId Customer ID
     * @param value Some int value
     */
    public MessageRepository(int type, int customerId, int value) {
        super(type, customerId);
        this.value = value;
    }
    
    /**
     * Message Repository constructor (form 3)
     * @param type Message type
     * @param nCustomersInsideShop Number of Customers inside the Shop
     */
    public MessageRepository(int type, int nCustomersInsideShop){
        super(type);
        this.nCustomersInsideShop = nCustomersInsideShop;
    }

    
    //*************** Internal Functions
    /**
     * Get the variable value.
     * @return Variable value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get number of Customers inside the Shop.
     * @return Number Customers inside the Shop
     */
    public int getnCustomersInsideShop() {
        return nCustomersInsideShop;
    }
    
}
