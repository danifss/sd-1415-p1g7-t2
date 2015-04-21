package comInf;

/**
 *
 * @author Daniel
 */
public class MessageShop extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Message Shop constructor
     * @param type Message type
     */
    public MessageShop(int type) {
        super(type);
    }

}
