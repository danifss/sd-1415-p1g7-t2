package comInf;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageConfig extends Message {
    
    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** CONFIGURATIONS MESSAGES TYPES
    /**
     * Message type: Get logging file name
     * @serialField GETFNAME
     */
    public static final int GETFNAME = 1;
    
    // int nCraftsman = 3;
    // int nCustomers = 3;
    // int nPrimeMaterialsInFactory = 10;
    // int nInitialProductsInShop = 10;
    // int nInitialPrimeMaterialsInStorage = 20;
    // int nPrimeMaterialsByProduct = 2;
    // int nPrimeOwnerCarry = 10;
    // int nMinPrimeMaterialsForRestock = 10;
    // int nMaxProductsCollect = 5;
    
    //*************** Internal Variables
    /**
     * Variable to save an integer value.
     * @serialField value
     */
    private int value = -1;
    
    /**
     * Variable to save a String value.
     * @serialField str
     */
    private String str = "";
    
    
    //*************** CONSTRUCTORS
    /**
     * Message Config Constructor (form 1)
     * @param type Message type
     * @param value 
     */
    public MessageConfig(int type, int value){
        super(type);
        this.value = value;
    }
    
    /**
     * Message Config Constructor (form 2)
     * @param type Message type
     * @param str 
     */
    public MessageConfig(int type, String str){
        super(type);
        this.str = str;
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
     * Get a generic String value.
     * @return str variable
     */
    public String getStr(){
        return str;
    }
}
