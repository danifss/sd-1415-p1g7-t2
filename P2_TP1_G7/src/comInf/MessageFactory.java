package comInf;

/**
 *
 * @author Daniel
 */
public class MessageFactory extends Message {
    
    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;

    //*************** CRAFTMANS MESSAGE TYPES
    /**
     * Message type: The Craftman indicates that the owner has products to collect.
     * @serialField READYFORTRANSFER
     */
    public static final int READYFORTRANSFER = 1;
    
    /**
     * Message type: The Craftman verifies if he needs to contact the owner to collect products.
     * @serialField CHECKCONTACTPRODUCT
     */
    public static final int CHECKCONTACTPRODUCT = 2;
    
    
    //*************** Internal Variables
    /**
     * Generic boolean variable
     * @serialField bool
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
     * Message Factory Constructor (form 3)
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
     * Get generic boolean variable.
     * @return boolean value of variable bool
     */
    public boolean isBool() {
        return bool;
    }
    
}
