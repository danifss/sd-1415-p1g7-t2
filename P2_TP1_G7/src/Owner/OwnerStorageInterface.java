package Owner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */

public interface OwnerStorageInterface {
    
    /**
     * See if the storage has prime materials.
     * @return true if the storage has prime materials
     */
    boolean isPrimeMaterialsAvailabe();
    
    /**
     * Owner visit suppliers and get some prime materials to be delivered in the factory.
     * If the storage has more or the same prime materials than the number of prime materials that
     * the Owner can carry, the Owner collects all prime materials he can. If the storage has
     * less than the number of prime materials that the Owner can carry, he collects
     * the prime materials available.
     * @return Number of prime materials collected
     */
    int visitSuppliers();
}
