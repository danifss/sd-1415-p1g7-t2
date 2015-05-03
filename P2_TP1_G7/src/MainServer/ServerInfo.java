package MainServer;

/**
 *
 * @author silva
 */
public class ServerInfo {
    
    /**
     * Main Server Host Name
     * @serialField MainServerHostName
     */
    private static final String MainServerHostName = "192.168.8.171"; // PC1
    
    /**
     * Main Server Port Number
     * @serialField MainServerPortNum
     */
    private static final int MainServerPortNum = 22177;

    
    /**
     * Server Info Constructor.
     */
    public ServerInfo(){
    }

    
    /**
     * Get Main Server Host Name.
     * @return MainServerHostName
     */
    public static String getMainServerHostName(){
        return MainServerHostName;
    }

    
    /**
     * Get Main Server Port Number.
     * @return MainServerPortNum
     */
    public static int getMainServerPortNum(){
        return MainServerPortNum;
    }

}
