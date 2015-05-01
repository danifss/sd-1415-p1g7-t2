package Owner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 1.0
 */

public interface OwnerFactoryInterface {
    
    /**
     * Owner brings prime materials.
     * He increases the number of prime materials in Factory, and also the total number
     * of prime materials supplied and the number of times he came to the Factory.
     * @param nPrimeMaterials Amount of prime materials to restock
     */
    void replenishStockFactory(int nPrimeMaterials);
    
    /**
     * Owner goes to factory to collect finished products.
     * If he can collect all the products (depends of the number of products that the
     * owner can carry), he collect all products setting the number of finished products
     * in Factory to zero. If he can't collect all the products, he collect the number
     * of products he can, decreasing from the number of finished products the number 
     * of products collected. He also decreases the flagNProductsCall.
     * @return number of products collected
     */
    int goToWorkshopFactory();
}
