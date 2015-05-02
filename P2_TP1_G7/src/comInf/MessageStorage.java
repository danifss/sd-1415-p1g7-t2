package comInf;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageStorage extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** OWNER MESSAGE TYPES
    /**
     * Message type: Owner sees if the Storage has prime materials.
     * @serialField PRIMEMATERIALSAVAILABLE
     */
    public static final int PRIMEMATERIALSAVAILABLE = 1;
    
    /**
     * Message type: Owner collects some prime materials.
     * @serialField VISITSUPPLIERS
     */
    public static final int VISITSUPPLIERS = 2;
    
    
    //*************** OTHER MESSAGE TYPES
    /**
     * Message type: Other messages types to get info from Storage.
     * @serialField GETNPRIMEMATERIALSDELIVERED
     * @serialField GETMAXPRIMEMATERIALSTODELIVER
     */
    public static final int GETNPRIMEMATERIALSDELIVERED = 3;
    public static final int GETMAXPRIMEMATERIALSTODELIVER = 4;
    
    
    //*************** Internal Variables
    /**
     * Variable to save a integer value.
     * @serialField value
     */
    private int value = -1;
    
    /**
     * Generic boolean variable
     * @serialField bool
     */
    private boolean bool = false;
    
    //*************** Constructors
    /**
     * Message Storage constructor (form 1)
     * @param type Message type
     */
    public MessageStorage(int type) {
        super(type);
    }
    
    /**
     * Message Storage Constructor (form 2)
     * @param type Message type
     * @param value Variable value
     */
    public MessageStorage(int type, int value){
        super(type);
        this.value = value;
    }
    
    /**
     * Message Storage Constructor (form 3)
     * @param type Message type
     * @param bool Generic boolean variable
     */
    public MessageStorage(int type, boolean bool){
        super(type);
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
