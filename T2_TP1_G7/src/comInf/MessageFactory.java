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

    /**
     * Message Factory constructor
     * @param type Message type
     */
    public MessageFactory(int type) {
        super(type);
    }

}
