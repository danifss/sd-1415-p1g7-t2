package MainServer;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */

public interface ConfigDataInterface {

    /**
     * Get logging file name.
     * @return log name
     */
    String getfName();

    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    int getnCraftmans();

    /**
     * Number of Customers.
     * @return number of customers
     */
    int getnCustomers();

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage
     */
    int getnInitialPrimeMaterialsInStorage();

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop
     */
    int getnInitialProductsInShop();

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect
     */
    int getnMaxProductsCollect();

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock
     */
    int getnMinPrimeMaterialsForRestock();

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct
     */
    int getnPrimeMaterialsByProduct();

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    int getnPrimeMaterialsInFactory();

    /**
     * Get total Products.
     * @return totalProducts
     */
    int gettotalProducts();

    /**
     * Logging file name.
     * @param fName file name
     */
    void setfName(String fName);

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
    void setnCraftmans(int nCraftmans);

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
    void setnCustomers(int nCustomers);

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage);

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop Products in Shop
     */
    void setnInitialProductsInShop(int nInitialProductsInShop);

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Products to collect
     */
    void setnMaxProductsCollect(int nMaxProductsCollect);

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Prima materials for restock
     */
    void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock);

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     */
    void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct);

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials in Factory
     */
    void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory);

    /**
     * Set total Products.
     * @param totalProducts total products
     */
    void settotalProducts(int totalProducts);
    
}
