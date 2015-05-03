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
    
    /**
     * Message type: Get numbers of Craftmans
     * @serialField GETNCRAFTMANS
     */
    public static final int GETNCRAFTMANS = 2;
    
    /**
     * Message type: Get number of Customers
     * @serialField GETNCUSTOMERS
     */
    public static final int GETNCUSTOMERS = 3;
    
    /**
     * Message type: Initial number of prime materials in the Factory
     * @serialField GETNPRIMEMATERIALSINFACTORY
     */
    public static final int GETNPRIMEMATERIALSINFACTORY = 4;
    
    /**
     * Message type: Initial number of products in the Shop
     * @serialField GETNINITIALPRODUCTSINSHOP
     */
    public static final int GETNINITIALPRODUCTSINSHOP = 5;
    
    /**
     * Message type: Initial number of prime materials in the Storage
     * @serialField GETNINITIALPRIMEMATERIALSINSTORAGE
     */
    public static final int GETNINITIALPRIMEMATERIALSINSTORAGE = 6;
    
    /**
     * Message type: Prime materials needed per product
     * @serialField GETNPRIMEMATERIALSBYPRODUCT
     */
    public static final int GETNPRIMEMATERIALSBYPRODUCT = 7;

    /**
     * Message type: Maximum number of products that the owner can carry
     * @serialField GETNMAXPRODUCTSCOLLECT
     */
    public static final int GETNMAXPRODUCTSCOLLECT = 8;
    
    /**
     * Message type: Minimum number of prime materials for restock
     * @serialField GETNMINPRIMEMATERIALSFORRESTOCK
     */
    public static final int GETNMINPRIMEMATERIALSFORRESTOCK = 9;
    
    
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
