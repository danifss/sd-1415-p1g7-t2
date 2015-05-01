package Owner;

import comInf.MessageShop;
import comInf.MessageRepository;
import comInf.MessageFactory;
import comInf.MessageStorage;
import genclass.GenericIO;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 1.0
 */
public class OwnerBroker implements OwnerFactoryInterface, OwnerRepositoryInterface, OwnerShopInterface, OwnerStorageInterface{
    
    /**
     * Repository server host name
     * @serialField RPserverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serialField RPserverPortNumb
     */
    private int RPserverPortNumb;
    
    /**
     * Shop server host name
     * @serialField ShopServerHostName
     */
    private String ShopServerHostName = null;

    /**
     * Shop Server port
     * @serialField ShopServerPortNumb
     */
    private int ShopServerPortNumb;
    
    /**
     * Factory server host name
     * @serialField FactoryServerHostName
     */
    private String FactoryServerHostName = null;

    /**
     * Factory Server port
     * @serialField FactoryServerPortNumb
     */
    private int FactoryServerPortNumb;
    
    /**
     * Storage server host name
     * @serialField FactoryServerHostName
     */
    private String StorageServerHostName = null;

    /**
     * Storage Server port
     * @serialField FactoryServerPortNumb
     */
    private int StorageServerPortNumb;
    
    public OwnerBroker(String ShopServerHostName, int ShopServerPortNumb, String RPserverHostName, int RPserverPortNumb, 
                            String FactoryServerHostName, int FactoryServerPortNumb, String StorageServerHostName, int StorageServerPortNumb){
        this.ShopServerHostName = ShopServerHostName;
        this.ShopServerPortNumb = ShopServerPortNumb;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
        this.FactoryServerHostName = FactoryServerHostName;
        this.FactoryServerPortNumb = FactoryServerPortNumb;
        this.StorageServerHostName = StorageServerHostName;
        this.StorageServerPortNumb = FactoryServerPortNumb;
    }
    
    //*************** Repository Communication
    /**
     * Communicate with Repository: Set Owner State.
     * @param state State of the Owner
     */
    @Override
    public void setOwnerState(int state) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Owner: - Error setting Owner State.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    
    
    //*************** Shop Communication
    /**
     * Communicate with Shop: Owner opens the door.
     * The state of the Shop is changed to OPEN.
     */
    @Override
    public void openTheDoor() {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        //outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        //con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Owner: - Error openning the door.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    /**
     * Communicate with Shop: Check if the owner can collect products.
     * @return true if he needs to go to the factory
     */
    @Override
    public boolean isTranfsProductsToShop() {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        //outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        //con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageShop.ACK:
                    result = inMessage.isBool();
                break;
            default:
                GenericIO.writelnString("Owner: - Error verifying if he can collect products.");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    /**
     * Communicate with Shop: Check if the factory needs prime materials.
     * @return true if the Factory needs prime materials
     */
    @Override
    public boolean isSupplyMaterialsToFactory() {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        //outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        //con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageShop.ACK:
                    result = inMessage.isBool();
                break;
            default:
                GenericIO.writelnString("Owner: - Error verifying if Factory needs prime materials.");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }    
    
    /**
     * Communicate with Shop: Owner sees the situation of the shop and decide what to do.
     * If the shop is open, he waits until a Customer or a Craftman calls him. 
     * If the shop is still open, he wait until all Customers leave the shop, and 
     * then proceeds to the request from the Factory.
     * @return action to do
     */
    @Override
    public int appraiseSit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: Owner closes the door.
     * If the shop has Customers inside, the shop change his state to STILL_OPEN,
     * if the shop doesn't have Customers, the shop change his state to CLOSED.
     */
    @Override
    public void closeTheDoor() {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        //outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        //con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Owner: - Error closing the door.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    /**
     * Communicate with Shop: See if there is customers inside the shop.
     * @return true if the number of Customers inside the shop is greater than 0
     */
    @Override
    public boolean customersInTheShop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: The Owner address a Customer on the queue.
     * The owner wakes up the first Customer in the queue.
     * @return id of the Customer that the Owner is attending
     */
    @Override
    public int addressACustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: The Owner services a customer.
     * The Owner waits until the Customer updates the number of products he wants 
     * to buy.
     * @return number of goods that the customer is buying
     */
    @Override
    public int serviceCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: The Owner says goodbye to the Customer he is attending.
     * He updates the flag that indicates that the purchase was made and wakes up
     * the Customer. Then removes the Customer from the queue.
     */
    @Override
    public void sayGoodByeToCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: See if the shop is on state STILL_OPEN.
     * @return true if the shop is STILL_OPEN
     */
    @Override
    public boolean isShopStillOpen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: The owner goes to the Factory to collect products.
     */
    @Override
    public void goToWorkshopShop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: Update the number of products that the shop is selling.
     * @param goods Number of products to add to the number of products in display
     */
    @Override
    public void addnGoodsInDisplay(int goods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: Owner goes to Factory to restock prime materials.
     */
    @Override
    public void replenishStockShop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Shop: See if the owner and the customer can stop.
     * Checks if all products have been transferred to Shop, and all the products
     * are sold.
     * @return true if they can stop working
     */
    @Override
    public boolean endOper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    //*************** Factory Communication
    /**
     * Communicate with Factory: Owner delivers prime materials to the Factory.
     */
    @Override
    public void replenishStockFactory(int nPrimeMaterials) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        // OutMessage com erro, construtor nao feito
        //outMessage = new MessageRepository(MessageRepository.SETOWNERSTATE, state);
        //con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Owner: - Error delivering prime materials to Factory.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Factory: Owner goes to factory to collect finished products.
     * If he can collect all the products (depends of the number of products that the
     * owner can carry), he collect all products setting the number of finished products
     * in Factory to zero. If he can't collect all the products, he collect the number
     * of products he can, decreasing from the number of finished products the number 
     * of products collected. He also decreases the flagNProductsCall.
     * @return number of products collected
     */
    @Override
    public int goToWorkshopFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





    //*************** Storage Communication
    /**
     * Communicate with Storage: See if the storage has prime materials.
     * @return true if the storage has prime materials
     */
    @Override
    public boolean isPrimeMaterialsAvailabe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Communicate with Storage: Owner visit suppliers and get some prime materials to be delivered in the factory.
     * If the storage has more or the same prime materials than the number of prime materials that
     * the Owner can carry, the Owner collects all prime materials he can. If the storage has
     * less than the number of prime materials that the Owner can carry, he collects
     * the prime materials available.
     * @return Number of prime materials collected
     */
    @Override
    public int visitSuppliers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}