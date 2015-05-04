package comInf;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageRepository extends Message {

    /**
     * Serialization key
     * 
     * @serial serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** OWNER MESSAGE TYPES
    /**
     * Owner messages: Set Owner Present State
     * @serial SETOWNERSTATE 
     */
    public static final int SETOWNERSTATE = 1;
    
    //*************** CUSTOMERS MESSAGE TYPES
    /**
     * Customer messages: Set Customer State in Repository
     * @serial SETCUSTOMERSTATE 
     */
    public static final int SETCUSTOMERSTATE = 2;
    
    /**
     * Customer messages: Set number of goods by Customer
     * @serial SETGOODSBYCUST Set number of goods by Customer
     */
    public static final int SETGOODSBYCUST = 3;
    
    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Craftman messages: Set Craftman Present State
     * @serial SETCRAFTMANSTATE 
     */
    public static final int SETCRAFTMANSTATE = 4;
    
    /**
     * Craftman messages: Set total number of products crafted by Craftman
     * @serial SETGOODSCRAFTEDBYCRAFTMAN 
     */
    public static final int SETGOODSCRAFTEDBYCRAFTMAN = 5;
    
    //*************** SHOP MESSAGE TYPES
    /**
     * Shop messages: Set Shop State
     * @serial SETSHOPSTATE 
     */
    public static final int SETSHOPSTATE = 6;
    
    /**
     * Shop messages: Set number of Customers inside the Shop
     * @serial SETCUSTINSHOP 
     */
    public static final int SETCUSTINSHOP = 7;
    
    /**
     * Shop messages: Set number of goods in display
     * @serial SETGOODSINDISP 
     */
    public static final int SETGOODSINDISP = 8;
    
    /**
     * Shop messages: Set if the craftsman requested the transfer of finished products to the Shop
     * @serial SETTRANSPRODTOSHOP 
     */
    public static final int SETTRANSPRODTOSHOP = 9;
    
    /**
     * Shop messages: Set if the craftsman requested the supply of prime materials to the Factory
     * @serial SETSUPPLYMATTOFACT 
     */
    public static final int SETSUPPLYMATTOFACT = 10;
    
    //*************** FACTORY MESSAGE TYPES
    /**
     * Shop messages: Change the amount of prime materials presently in the Factory
     * @serial SETPRIMEMATERIALSINFACT  
     */
    public static final int SETPRIMEMATERIALSINFACT = 11;
    
    /**
     * Shop messages: Change the number of finished products presently in the Factory
     * @serial SETFINISHEDPRODUCTSINFACT 
     */
    public static final int SETFINISHEDPRODUCTSINFACT = 12;
    
    /**
     * Shop messages: Change the number of times that a supply of prime materials was delivered to the Factory
     * @serial SETSUPPLIEDTIMES 
     */
    public static final int SETSUPPLIEDTIMES = 13;
    
    /**
     * Shop messages: Change the total amount of prime materials that have already been supplied (accumulation)
     * @serial SETPRIMEMATSUPPLIED 
     */
    public static final int SETPRIMEMATSUPPLIED = 14;
    
    /**
     * Shop messages: Change the total number of products that have already been manufactured (accumulation)
     * @serial SETSUPPLYMATTOFACT
     */
    public static final int SETPRODSMANUFACTURED = 15;
    
    //*************** Internal variables
    /**
     * Value of something in integer.
     * @serial value
     */
    private int value = -1;
    
    /**
     * Variable to save a boolean value.
     * @serial bool
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
