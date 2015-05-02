package Shop;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface ShopRepositoryInterface {
    /**
     * Set if the craftsman requested the transfer of finished products to the Shop.
     * @param tranfsProductsToShop Boolean indicating if the Craftman requested
     */
    void setTranfsProductsToShop(boolean tranfsProductsToShop);
    
    /**
     * Set number of goods in display.
     * @param nGoodsInDisplay Number of goods in display
     */
    void setnGoodsInDisplay(int nGoodsInDisplay);
    
    /**
     * Set if the craftsman requested the supply of prime materials to the Factory.
     * @param supplyMaterialsToFactory Boolean indicating if the Craftman requested
     */
    void setSupplyMaterialsToFactory(boolean supplyMaterialsToFactory);
    
    /**
     * Set number of customers inside.
     * @param nCustomersInsideShop Number of customers inside
     */
    void setnCustomersInsideShop(int nCustomersInsideShop);
    
    /**
     * Set Shop State.
     * @param state State of the shop
     */
    void setShopState(int state);
}
