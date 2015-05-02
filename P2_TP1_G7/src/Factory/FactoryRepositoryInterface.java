package Factory;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface FactoryRepositoryInterface {
    /**
     * Change the amount of prime materials presently in the Factory.
     * @param nPrimeMaterialsInFactory Amount of prime materials available in the Factory
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory);
    
    /**
     * Change the number of finished products presently in the Factory.
     * @param nFinishedProductsInFactory Number of finished products in the Factory
     */
    void setnFinishedProductsInFactory(int nFinishedProductsInFactory);
    
    /**
     * Change the total number of products that have already been manufactured (accumulation).
     * @param nProductsManufactured Total number of products produced
     */
    void setnProductsManufactured(int nProductsManufactured);
    
    /**
     * Change the number of times that a supply of prime materials was delivered to the Factory.
     * @param nSuppliedTimes Number of times that the owner delivered prime materials
     */
    void setnSuppliedTimes(int nSuppliedTimes);
    
    /**
     * Change the total amount of prime materials that have already been supplied (accumulation).
     * @param nPrimeMaterialsSupplied Number of prime materials supplied
     */
    void setnPrimeMaterialsSupplied(int nPrimeMaterialsSupplied);
}
