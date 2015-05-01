package comInf;

import java.io.Serializable;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class MessageRep implements Serializable{
    
    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Operation with error. (Server response)
     *
     * @serialField ERROR
     */
    public static final int ERROR = -1;
    
    /**
     * Operation completed with success. (Server response)
     *
     * @serialField ACK
     */
    public static final int ACK = 0;
    
    
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
    
    /**
     * Message type.
     * @serialField msgType
     */
    private int msgType = -1;
    
    /**
     * Owner State
     * @serialField ownerState
     */
    private int ownerState = -1;
    
    /**
     * Customer ID
     * @serialField customerId
     */
    private int customerId = -1;
    
    /**
     * Customer State
     * @serialField customerState
     */
    private int customerState = -1;
    
    /**
     * Number of goods bought by the customer
     * @serialField goodsBoughtByCust
     */
    private int goodsBoughtByCust = -1;
    
    /**
     * Craftman ID
     * @serialField craftmanId
     */
    private int craftmanId = -1;
    
    /**
     * Craftman State
     * @serialField customerState
     */
    private int craftmanState = -1;
    
    /**
     * Number of products crafted by the Craftman.
     * @serialField goodsCraftedByCraftman
     */
    private int goodsCraftedByCraftman = -1;
    
    /**
     * Message Repository constructor (form 1)
     * @param type Message type
     */
    public MessageRep(int type) {
        msgType = type;
    }
    
    /**
     * Message Repository constructor (form 1)
     * @param type Message type
     * @param value1 value 1
     */
    public MessageRep(int type, int value1) {
        msgType = type;
        if(type == SETOWNERSTATE){
            ownerState = value1;
        }
    }
    
    /**
     * Message Repository constructor (form 2)
     * @param type Message type
     * @param value1 value 1
     * @param value2 value 2
     */
    public MessageRep(int type, int value1, int value2){
        msgType = type;
        if(type == SETCUSTOMERSTATE){
            customerId = value1;
            customerState = value2;
        }else if(type == SETGOODSBYCUST){
            customerId = value1;
            goodsBoughtByCust = value2;        
        }else if(type == SETCRAFTMANSTATE){
            craftmanId = value1;
            craftmanState = value2;
        }else if(type == SETGOODSCRAFTEDBYCRAFTMAN){
            craftmanId = value1;
            goodsCraftedByCraftman = value2;
        }
    }
    
}
