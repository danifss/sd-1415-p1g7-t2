package comInf;

/**
 *
 * @author Daniel
 */
public class MessageRepository extends Message {

    /**
     * Serialization key
     * 
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;
    
    /**
     * Message Repository constructor
     * @param type Message type
     */
    public MessageRepository(int type) {
        super(type);
    }

}
