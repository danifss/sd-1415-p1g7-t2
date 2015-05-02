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
    
    //*************** OWNER MESSAGE TYPES
    /**
     * Owner messages.
     * @serialField SETOWNERSTATE Set Owner Present State
     */
    public static final int SETOWNERSTATE = 1;
    
    //*************** CUSTOMERS MESSAGE TYPES
    /**
     * Customer messages.
     * @serialField SETCUSTOMERSTATE Set Customer State in Repository
     * @serialField SETGOODSBYCUST Set number of goods by Customer
     */
    public static final int SETCUSTOMERSTATE = 1;
    public static final int SETGOODSBYCUST = 2;
    
    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Craftman messages. 
     * @serialField SETCRAFTMANSTATE Set Craftman Present State
     * @serialField SETGOODSCRAFTEDBYCRAFTMAN Set total number of products crafted by Craftman
     */
    public static final int SETCRAFTMANSTATE = 3;
    public static final int SETGOODSCRAFTEDBYCRAFTMAN = 4;
    
    //*************** SHOP MESSAGE TYPES
    /**
     * Shop messages. 
     * @serialField SETSHOPSTATE Set Shop State
     * @serialField SETCUSTINSHOP Set number of Customers inside the Shop
     * @serialField SETGOODSINDISP Set number of goods in display
     * @serialField SETTRANSPRODTOSHOP Set if the craftsman requested the transfer of finished products to the Shop
     * @serialField SETSUPPLYMATTOFACT Set if the craftsman requested the supply of prime materials to the Factory
     */
    public static final int SETSHOPSTATE = 5;
    public static final int SETCUSTINSHOP = 6;
    public static final int SETGOODSINDISP = 7;
    public static final int SETTRANSPRODTOSHOP = 8;
    public static final int SETSUPPLYMATTOFACT = 9;
    
    //*************** FACTORY MESSAGE TYPES
    /**
     * Shop messages. 
     * @serialField SETPRIMEMATERIALSINFACT Change the amount of prime materials presently in the Factory
     * @serialField SETFINISHEDPRODUCTSINFACT Change the number of finished products presently in the Factory
     * @serialField SETSUPPLIEDTIMES Change the number of times that a supply of prime materials was delivered to the Factory
     * @serialField SETPRIMEMATSUPPLIED Change the total amount of prime materials that have already been supplied (accumulation)
     * @serialField SETSUPPLYMATTOFACT Change the total number of products that have already been manufactured (accumulation)
     */
    public static final int SETPRIMEMATERIALSINFACT = 10;
    public static final int SETFINISHEDPRODUCTSINFACT = 11;
    public static final int SETSUPPLIEDTIMES = 12;
    public static final int SETPRIMEMATSUPPLIED = 13;
    public static final int SETPRODSMANUFACTURED = 14;
    
    //*************** Internal variables
    /**
     * Value of something in integer.
     * @serialField value
     */
    private int value;
    
    /**
     * Variable to save a boolean value.
     * @serialField bool
     */
    private boolean bool = false;
    
    
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
     * @param id ID
     */
    public MessageRepository(int type, int id) {
        super(type, id);
    }
    
    /**
     * Message Repository constructor (form 3)
     * @param type Message type
     * @param id ID
     * @param value Some int value
     */
    public MessageRepository(int type, int id, int value) {
        super(type, id);
        this.value = value;
    }
    
    /**
     * Message Repository constructor (form 4)
     * @param type Message type
     * @param bool boolean
     */
    public MessageRepository(int type, boolean bool) {
        super(type);
        this.bool = bool;
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
     * Get generic boolean variable.
     * @return boolean value of variable bool
     */
    public boolean isBool(){
        return bool;
    }
}
