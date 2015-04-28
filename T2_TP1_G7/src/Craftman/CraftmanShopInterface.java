package Craftman;


/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface CraftmanShopInterface {
    /**
     * The Craftman indicates that the Owner can go to factory to collect products.
     * He changes the flag and wakes up the Owner.
     */
    void batchReadyForTransferShop();
    
    /**
     * The Craftman indicates that prime materials is needed in the Factory.
     * He changes the flag and wakes up the Owner.
     */
    void primeMaterialsNeededShop();
    

}
