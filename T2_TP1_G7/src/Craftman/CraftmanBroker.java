package Craftman;

/**
 *
 * @author Daniel
 */
class CraftmanBroker implements CraftmanRepositoryInterface, CraftmanShopInterface, CraftmanFactoryInterface {
    
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
    @Override
    public void setCraftmanState(int craftmanId, int state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setnGoodsCraftedByCraftman(int craftmanId, int nGoodsCraftedByCraftman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // Shop Communication
    @Override
    public void primeMaterialsNeededShop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void batchReadyForTransferShop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // Factory Communication
    @Override
    public void batchReadyForTransferFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkContactProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForMaterials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForRestock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int collectMaterials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean endOfPrimeMaterials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean flagPrimeActivated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getnPrimePerProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int goToStore(int nProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean primeMaterialsNeededFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
