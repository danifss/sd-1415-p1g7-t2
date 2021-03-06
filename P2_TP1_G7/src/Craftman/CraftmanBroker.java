package Craftman;

import comInf.MessageShop;
import comInf.MessageRepository;
import comInf.MessageFactory;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CraftmanBroker implements CraftmanRepositoryInterface, CraftmanShopInterface, CraftmanFactoryInterface {
    
    /**
     * Repository server host name
     * @serial RPserverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serial RPserverPortNumb
     */
    private int RPserverPortNumb;
    
    /**
     * Shop server host name
     * @serial ShopServerHostName
     */
    private String ShopServerHostName = null;

    /**
     * Shop Server port
     * @serial ShopServerPortNumb
     */
    private int ShopServerPortNumb;
    
    /**
     * Factory server host name
     * @serial FactoryServerHostName
     */
    private String FactoryServerHostName = null;

    /**
     * Factory Server port
     * @serial FactoryServerPortNumb
     */
    private int FactoryServerPortNumb;
    
    
    /**
     * Craftman Broker constructor
     * 
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     * @param ShopServerHostName    Shop Server Host Name
     * @param ShopServerPortNumb    Shop Server Port Number
     * @param FactoryServerHostName Factory Server Host Name
     * @param FactoryServerPortNumb Factory Server Port Number
     */
    public CraftmanBroker(String RPserverHostName, int RPserverPortNumb, String ShopServerHostName, int ShopServerPortNumb, 
                            String FactoryServerHostName, int FactoryServerPortNumb) {
        this.ShopServerHostName = ShopServerHostName;
        this.ShopServerPortNumb = ShopServerPortNumb;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
        this.FactoryServerHostName = FactoryServerHostName;
        this.FactoryServerPortNumb = FactoryServerPortNumb;
    }
    
    
    //*************** Repository Communication
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
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            System.out.println("Craftman: "+craftmanId+" - Error setting Craftman State.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Repository: Set total number of crafted goods by Craftman.
     * @param craftmanId id
     * @param nGoodsCraftedByCraftman total goods of Craftman
     */
    @Override
    public void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETGOODSCRAFTEDBYCRAFTMAN, craftmanId, nGoodsCraftedByCraftman);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            System.out.println("Craftman: "+craftmanId+" - Error setting total goods crafted by him.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    
    //*************** Shop Communication
    /**
     * Communicate with Shop: Craftman indicates that prime materials are needed in the Factory.
     * @param craftmanId id
     */
    @Override
    public void primeMaterialsNeededShop(int craftmanId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.PRIMEMATERIALSNEEDED, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageShop.ACK){
            System.out.println("Craftman: "+craftmanId+" - Error setting prime materials needed.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    /**
     * Communicate with Shop: Craftman indicates that the Owner can go to factory to collect products.
     * @param craftmanId id
     */
    @Override
    public void batchReadyForTransferShop(int craftmanId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.READYFORTRANSFER, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if(inMessage.getType() != MessageShop.ACK){
            System.out.println("Craftman: "+craftmanId+" - Error notifying batch is ready for transfer.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    
    //*************** Factory Communication
    /**
     * Communicate with Factory: The Craftman indicates that the owner has products to collect.
     * @param craftmanId id
     */
    @Override
    public void batchReadyForTransferFactory(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.READYFORTRANSFER, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        if(inMessage.getType() != MessageFactory.ACK){
            System.out.println("Craftman: "+craftmanId+" - Error notifying owner to collect products.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Factory: The Craftman verifies if he needs to contact the owner to collect products.
     * @param craftmanId id
     * @return if he needs to contact owner
     */
    @Override
    public boolean checkContactProduct(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.CHECKCONTACTPRODUCT, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error verifying if he needs to contact the owner.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: The Craftman checks if the Factory has prime materials to collect.
     * @param craftmanId id
     * @return if Factory has prime materials
     */
    @Override
    public boolean checkForMaterials(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.CHECKFORMATERIALS, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking for prime materials on Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: Check if the Craftman needs to contact owner to bring prime materials.
     * @param craftmanId id
     * @return if owner has to bring prime materials from Factory
     */
    @Override
    public boolean checkForRestock(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.CHECKFORRESTOCK, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking if needs to contact owner to bring prime materials from Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: The Craftman collects prime materials.
     * @param craftmanId id
     * @return collected materials
     */
    @Override
    public int collectMaterials(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.COLLECTMATERIALS, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        int result = 0;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.getValue();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking if needs to contact owner to bring prime materials from Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: Checks if the all the prime materials from the storage were supplied.
     * @param craftmanId id
     * @return if all prime materials have been supplied
     */
    @Override
    public boolean endOfPrimeMaterials(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.ENDOFPRIMEMATERIALS, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking if needs to contact owner to bring prime materials from Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: The Craftman sees if someone already contacted the owner to restock prime materials.
     * @param craftmanId id
     * @return if someone already contacted the owner
     */
    @Override
    public boolean flagPrimeActivated(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.FLAGPRIMEACTIVATED, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking if someone already contacted the owner to restock on Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: Craftman sees how many prime materials needs to produce a new product
     * @param craftmanId id
     * @return number of prime materials needed per product
     */
    @Override
    public int getnPrimePerProduct(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.GETNPRIMEPERPRODUCT, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        int result = 0;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.getValue();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking how many prime materials need to make a product on Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: The Craftman stores the products produced.
     * @param craftmanId id
     * @param nProd Number of products that the Craftman has
     * @return number of products the Craftman stored
     */
    @Override
    public int goToStore(int craftmanId, int nProd) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.GOTOSTORE, craftmanId, nProd);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        int result = 0;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.getValue();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error storing made products into Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    /**
     * Communicate with Factory: The Craftman turns true the flag that indicates that prime materials are needed.
     * @param craftmanId id
     * @return if he contacted the owner
     */
    @Override
    public boolean primeMaterialsNeededFactory(int craftmanId) {
        ClientCom con = new ClientCom(FactoryServerHostName, FactoryServerPortNumb);
        MessageFactory inMessage, outMessage;
        
        outMessage = new MessageFactory(MessageFactory.PRIMEMATERIALSNEEDED, craftmanId);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageFactory) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageFactory.ACK:
                result = inMessage.isBool();
                break;
            default:
                System.out.println("Craftman: "+craftmanId+" - Error checking if flag of prime materials needed is true or not in Factory.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
}
