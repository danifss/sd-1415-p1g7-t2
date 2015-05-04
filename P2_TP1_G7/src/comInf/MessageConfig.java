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
     * @serial serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    //*************** CONFIGURATIONS MESSAGES TYPES
    /**
     * Message type: Get logging file name
     * @serial GETFNAME
     */
    public static final int GETFNAME = 1;
    
    /**
     * Message type: Get numbers of Craftmans
     * @serial GETNCRAFTMANS
     */
    public static final int GETNCRAFTMANS = 2;
    
    /**
     * Message type: Get number of Customers
     * @serial GETNCUSTOMERS
     */
    public static final int GETNCUSTOMERS = 3;
    
    /**
     * Message type: Initial number of prime materials in the Factory
     * @serial GETNPRIMEMATERIALSINFACTORY
     */
    public static final int GETNPRIMEMATERIALSINFACTORY = 4;
    
    /**
     * Message type: Initial number of products in the Shop
     * @serial GETNINITIALPRODUCTSINSHOP
     */
    public static final int GETNINITIALPRODUCTSINSHOP = 5;
    
    /**
     * Message type: Initial number of prime materials in the Storage
     * @serial GETNINITIALPRIMEMATERIALSINSTORAGE
     */
    public static final int GETNINITIALPRIMEMATERIALSINSTORAGE = 6;
    
    /**
     * Message type: Prime materials needed per product
     * @serial GETNPRIMEMATERIALSBYPRODUCT
     */
    public static final int GETNPRIMEMATERIALSBYPRODUCT = 7;

    /**
     * Message type: Maximum number of products that the owner can carry
     * @serial GETNMAXPRODUCTSCOLLECT
     */
    public static final int GETNMAXPRODUCTSCOLLECT = 8;
    
    /**
     * Message type: Minimum number of prime materials for restock
     * @serial GETNMINPRIMEMATERIALSFORRESTOCK
     */
    public static final int GETNMINPRIMEMATERIALSFORRESTOCK = 9;
    
    /**
     * Message type: Number of total products
     * @serial GETTOTALPRODUCTS
     */
    public static final int GETTOTALPRODUCTS = 10;
    
    
    //*************** SETTING MESSAGES OF HOSTS AND PORTS
    // Repository
    /**
     * Message type: Set Repository Host name
     * @serial SETREPOSITORYHOST
     */
    public static final int SETREPOSITORYHOST = 20;
    
    /**
     * Message type: Get Repository Host name
     * @serial GETREPOSITORYHOST
     */
    public static final int GETREPOSITORYHOST = 21;
    
    /**
     * Message type: Set Repository port number
     * @serial SETREPOSITORYPORT
     */
    public static final int SETREPOSITORYPORT = 22;
    
    /**
     * Message type: Get Repository port number
     * @serial GETREPOSITORYPORT
     */
    public static final int GETREPOSITORYPORT = 23;
    
    // SHOP
    /**
     * Message type: Set Shop Host name
     * @serial SETSHOPHOST
     */
    public static final int SETSHOPHOST = 24;
    
    /**
     * Message type: Get Shop Host name
     * @serial GETSHOPHOST
     */
    public static final int GETSHOPHOST = 25;
    
    /**
     * Message type: Set Shop port number
     * @serial SETSHOPPORT
     */
    public static final int SETSHOPPORT = 26;
    
    /**
     * Message type: Get Shop port number
     * @serial GETSHOPPORT
     */
    public static final int GETSHOPPORT = 27;
    
    // Factory
    /**
     * Message type: Get Factory Host name
     * @serial SETFACTORYHOST
     */
    public static final int SETFACTORYHOST = 32;
    
    /**
     * Message type: Get Factory Host name
     * @serial GETFACTORYHOST
     */
    public static final int GETFACTORYHOST = 33;
    
    /**
     * Message type: Set Factory port number
     * @serial SETFACTORYPORT
     */
    public static final int SETFACTORYPORT = 34;
    
    /**
     * Message type: Get Factory port number
     * @serial GETFACTORYPORT
     */
    public static final int GETFACTORYPORT = 35;
    
    // STORAGE
    /**
     * Message type: Set Storage Host name
     * @serial SETSTORAGEHOST
     */
    public static final int SETSTORAGEHOST = 28;
    
    /**
     * Message type: Get Storage Host name
     * @serial GETSTORAGEHOST
     */
    public static final int GETSTORAGEHOST = 29;
    
    /**
     * Message type: Set Factory port number
     * @serial SETSTORAGEPORT
     */
    public static final int SETSTORAGEPORT = 30;
    
    /**
     * Message type: Get Storage port number
     * @serial GETSTORAGEPORT
     */
    public static final int GETSTORAGEPORT = 31;
    
    
    //*************** Internal Variables
    /**
     * Variable to save an integer value.
     * @serial value
     */
    private int value = -1;
    
    /**
     * Variable to save a String value.
     * @serial str
     */
    private String str = "";
    
    
    //*************** CONSTRUCTORS
    /**
     * Message Config Constructor (form 1)
     * @param type Message type
     */
    public MessageConfig(int type){
        super(type);
    }
    
    /**
     * Message Config Constructor (form 2)
     * @param type Message type
     * @param value value
     */
    public MessageConfig(int type, int value){
        super(type);
        this.value = value;
    }
    
    /**
     * Message Config Constructor (form 3)
     * @param type Message type
     * @param str string
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
