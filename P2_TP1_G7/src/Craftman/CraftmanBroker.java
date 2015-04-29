package Craftman;

import comInf.MessageShop;
import comInf.MessageRepository;
import comInf.MessageFactory;
import genclass.GenericIO;

/**
 *
 * @author Daniel
 */
public class CraftmanBroker implements CraftmanRepositoryInterface, CraftmanShopInterface, CraftmanFactoryInterface {
    
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
     * Craftman Broker constructor
     * 
     * @param ShopServerHostName    Shop Server Host Name
     * @param ShopServerPortNumb    Shop Server Port Number
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     * @param FactoryServerHostName Factory Server Host Name
     * @param FactoryServerPortNumb Factory Server Port Number
     */
    public CraftmanBroker(String ShopServerHostName, int ShopServerPortNumb, String RPserverHostName, int RPserverPortNumb, 
                            String FactoryServerHostName, int FactoryServerPortNumb) {
        this.ShopServerHostName = ShopServerHostName;
        this.ShopServerPortNumb = ShopServerPortNumb;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
        this.FactoryServerHostName = FactoryServerHostName;
        this.FactoryServerPortNumb = FactoryServerPortNumb;
    }
    
    
    // Repository Communication
    /**
     * Communicate with Repository: Set Craftman present state.
     * @param craftmanId id
     * @param state Craftman future state
     */
    @Override
    public void setCraftmanState(int craftmanId, int state) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETCRAFTMANSTATE, craftmanId, state);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Craftman: "+craftmanId+" - Error setting Craftman State.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Repository: Set total number of crafted goods by Craftman.
     * @param craftmanId id
     * @param nGoodsCraftedByCraftman total goods of craftman
     */
    @Override
    public void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN, craftmanId, nGoodsCraftedByCraftman);
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Craftman: "+craftmanId+" - Error setting total goods crafted by him.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    
    // Shop Communication
    /**
     * Communicate with Shop: Craftman indicates that prime materials are needed in the Factory.
     * @param craftmanId
     */
    @Override
    public void primeMaterialsNeededShop(int craftmanId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.PRIMEMATERIALSNEEDED, craftmanId);
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageShop.ACK){
            GenericIO.writelnString("Craftman: "+craftmanId+" - Error setting prime materials needed.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    /**
     * Communicate with Shop: Craftman indicates that the Owner can go to factory to collect products.
     * @param craftmanId 
     */
    @Override
    public void batchReadyForTransferShop(int craftmanId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.READYFORTRANSFER, craftmanId);
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageShop.ACK){
            GenericIO.writelnString("Craftman: "+craftmanId+" - Error notifying batch is ready for transfer.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    
    // Factory Communication
    /**
     * Communicate with Factory: The Craftman indicates that the owner has products to collect.
     * @param craftmanId
     */
    @Override
    public void batchReadyForTransferFactory(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.READYFORTRANSFER, craftmanId);
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        if(inMessage.getType() != MessageFactory.ACK){
            GenericIO.writelnString("Craftman: "+craftmanId+" - Error notifying owner to collect products.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Factory: The Craftman verifies if he needs to contact the owner to collect products.
     * @param craftmanId
     * @return if he needs to contact owner
     */
    @Override
    public boolean checkContactProduct(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.CHECKCONTACTPRODUCT, craftmanId);
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                GenericIO.writelnString("Craftman: "+craftmanId+" - Error verifying if he needs to contact the owner.");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    @Override
    public boolean checkForMaterials(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForRestock(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int collectMaterials(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean endOfPrimeMaterials(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean flagPrimeActivated(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getnPrimePerProduct(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int goToStore(int craftmanId, int nProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean primeMaterialsNeededFactory(int craftmanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
