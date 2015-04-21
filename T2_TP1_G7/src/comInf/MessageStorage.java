package comInf;

/**
 *
 * @author Daniel
 */
public class MessageStorage extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Message Storage constructor
     * @param type Message type
     */
    public MessageStorage(int type) {
        super(type);
    }

}
