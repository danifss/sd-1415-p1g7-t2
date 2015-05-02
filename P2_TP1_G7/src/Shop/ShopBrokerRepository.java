package Shop;

import comInf.MessageRepository;
import genclass.GenericIO;

/**
 *
 * @author Daniel
 */
public class ShopBrokerRepository implements ShopRepositoryInterface {

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
     * Constructor of ShopRepository
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     */
    public ShopBrokerRepository(String RPserverHostName, int RPserverPortNumb) {
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
    }

    
    /**
     * Set if the craftsman requested the transfer of finished products to the Shop.
     * @param tranfsProductsToShop Boolean indicating if the Craftman requested
     */
    @Override
    public void setTranfsProductsToShop(boolean tranfsProductsToShop) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETTRANSPRODTOSHOP, tranfsProductsToShop);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting the boolean to transfer products to Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Set number of goods in display.
     * @param nGoodsInDisplay Number of goods in display
     */
    @Override
    public void setnGoodsInDisplay(int nGoodsInDisplay) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETGOODSINDISP, -1, nGoodsInDisplay);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting number of goods in display.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Set if the craftsman requested the supply of prime materials to the Factory.
     * @param supplyMaterialsToFactory Boolean indicating if the Craftman requested
     */
    @Override
    public void setSupplyMaterialsToFactory(boolean supplyMaterialsToFactory) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETSUPPLYMATTOFACT, supplyMaterialsToFactory);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting boolean to supply materials to Factory.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Set number of customers inside.
     * @param nCustomersInsideShop Number of customers inside
     */
    @Override
    public void setnCustomersInsideShop(int nCustomersInsideShop) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETCUSTINSHOP, -1, nCustomersInsideShop);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting number of customers inside Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Set Shop State.
     * @param state State of the shop
     */
    @Override
    public void setShopState(int state) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETSHOPSTATE, -1, state);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Shop: - Error setting the state of the Shop.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
