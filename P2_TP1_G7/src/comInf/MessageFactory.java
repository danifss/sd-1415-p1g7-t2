package comInf;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageFactory extends Message {
    
    /**
     * Serialization key
     * 
     * @serial serialVersionUID
     */
    private static final long serialVersionUID = 220415L;

    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Message type: The Craftman indicates that the owner has products to collect.
     * @serial READYFORTRANSFER
     */
    public static final int READYFORTRANSFER = 1;
    
    /**
     * Message type: The Craftman verifies if he needs to contact the owner to collect products.
     * @serial CHECKCONTACTPRODUCT
     */
    public static final int CHECKCONTACTPRODUCT = 2;
    
    /**
     * Message type: The Craftman checks if the Factory has prime materials to collect.
     * @serial CHECKFORMATERIALS
     */
    public static final int CHECKFORMATERIALS = 3;
    
    /**
     * Message type: Check if the Craftman needs to contact owner to bring prime materials.
     * @serial CHECKFORRESTOCK
     */
    public static final int CHECKFORRESTOCK = 4;
    
    /**
     * Message type: The Craftman collects prime materials.
     * @serial COLLECTMATERIALS
     */
    public static final int COLLECTMATERIALS = 5;
    
    /**
     * Message type: Checks if the all the prime materials from the storage were supplied.
     * @serial ENDOFPRIMEMATERIALS
     */
    public static final int ENDOFPRIMEMATERIALS = 6;
    
    /**
     * Message type: The Craftman sees if someone already contacted the owner to restock prime materials.
     * @serial FLAGPRIMEACTIVATED
     */
    public static final int FLAGPRIMEACTIVATED = 7;
    
    /**
     * Message type: Craftman sees how many prime materials needs to produce a new product
     * @serial GETNPRIMEPERPRODUCT
     */
    public static final int GETNPRIMEPERPRODUCT = 8;
    
    /**
     * Message type: The Craftman stores the products produced.
     * @serial GOTOSTORE
     */
    public static final int GOTOSTORE = 9;
    
    /**
     * Message type: The Craftman turns true the flag that indicates that prime materials are needed.
     * @serial PRIMEMATERIALSNEEDED
     */
    public static final int PRIMEMATERIALSNEEDED = 10;
    
    
    //*************** OWNER MESSAGE TYPES
    /**
     * Message type: Owner goes to Factory to restock prime materials.
     * @serial REPLENISHSTOCK
     */
    public static final int REPLENISHSTOCK = 30;
    
    /**
     * Message type: Owner goes to the Factory to collect products.
     * @serial GOTOWORKSHOP
     */
    public static final int GOTOWORKSHOP = 31;
    
    
    //*************** Internal Variables
    /**
     * Variable to save a integer value.
     * @serial value
     */
    private int value = -1;
    
    /**
     * Generic boolean variable
     * @serial bool
     */
    private boolean bool = false;
    
    
    //*************** CONSTRUCTORS
    /**
     * Message Factory Constructor (form 1)
     * @param type Message type
     */
    public MessageFactory(int type) {
        super(type);
    }
    
    /**
     * Message Factory Constructor (form 2)
     * @param type Message type
     * @param id 
     */
    public MessageFactory(int type, int id){
        super(type, id);
    }
    
    /**
     * Message Factory constructor (form 3)
     * @param type Message type
     * @param id ID
     * @param value Variable value
     */
    public MessageFactory(int type, int id, int value) {
        super(type, id);
        this.value = value;
    }
    
    /**
     * Message Factory Constructor (form 4)
     * @param type Message type
     * @param id
     * @param bool Generic boolean variable
     */
    public MessageFactory(int type, int id, boolean bool){
        super(type, id);
        this.bool = bool;
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
     * Get generic boolean variable.
     * @return boolean value of variable bool
     */
    public boolean isBool() {
        return bool;
    }
    
}
