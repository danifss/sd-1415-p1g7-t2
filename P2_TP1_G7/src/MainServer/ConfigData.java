package MainServer;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */

public class ConfigData implements ConfigDataInterface {

    /**
     * Logging file name
     * @serialField fName
     */
	private String fName = "log.txt";
    
    /**
     * Number of Craftmans.
     * @serialField nCraftmans
     */
	private int nCraftmans = 3;
    
    /**
     * Number of Customers
     * @serialField nCustomers
     */
    private int nCustomers = 3;
    
    /**
     * Initial number of prime materials in the Factory
     * @serialField nPrimeMaterialsInFactory
     */
    private int nPrimeMaterialsInFactory = 10;
    
    /**
     * Initial number of products in the Shop
     * @serialField nInitialProductsInShop
     */
    private int nInitialProductsInShop = 10;
    
    /**
     * Initial number of prime materials in the Storage
     * @serialField nInitialPrimeMaterialsInStorage
     */
    private int nInitialPrimeMaterialsInStorage = 20;
    
    /**
     * Prime materials needed per product
     * @serialField nPrimeMaterialsByProduct
     */
    private int nPrimeMaterialsByProduct = 2;
    
    /**
     * 
     * @serialField nPrimeOwnerCarry
     */
    //private int nPrimeOwnerCarry = 10;
    
    /**
     * Minimum number of prime materials for restock
     * @serialField nMinPrimeMaterialsForRestock
     */
    private int nMinPrimeMaterialsForRestock = 10;
    
    /**
     * Maximum number of products that the owner can carry
     * @serialField nMaxProductsCollect
     */
    private int nMaxProductsCollect = 5;
    
    /**
     * Number of total products
     * @serialField totalProducts
     */
    private int totalProducts = 0;
    
    
    //*************** SERVERS INFO
    /**
     * Repository Server
     * @serialField repositoryHostName
     * @serialField repositoryPortNum
     */
    private String repositoryHostName = "localhost";
    private int repositoryPortNum = -1;
    
    /**
     * Shop Server
     * @serialField shopHostName
     * @serialField shopPortNum
     */
    private String shopHostName = "localhost";
    private int shopPortNum = -1;
    
    /**
     * Storage Server
     * @serialField storageHostName
     * @serialField storagePortNum
     */
    private String storageHostName = "localhost";
    private int storagePortNum = -1;
    
    /**
     * Factory Server
     * @serialField factoryHostName
     * @serialField factoryPortNum
     */
    private String factoryHostName = "localhost";
    private int factoryPortNum = -1;
    

    /**
     * Configurations Server Constructor
     */
	public ConfigData(){
	}

    
    /**
     * Get logging file name.
     * @return log name
     */
    @Override
    public String getfName(){
        return fName;
    }

    /**
     * Logging file name.
     * @param fName file name
     */
    @Override
    public void setfName(String fName){
        this.fName = fName;
    }

    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    @Override
    public int getnCraftmans(){
        return nCraftmans;
    }

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
    @Override
    public void setnCraftmans(int nCraftmans){
        this.nCraftmans = nCraftmans;
    }

    /**
     * Number of Customers.
     * @return number of customers
     */
    @Override
    public int getnCustomers(){
        return nCustomers;
    }

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
    @Override
    public void setnCustomers(int nCustomers){
        this.nCustomers = nCustomers;
    }

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    @Override
    public int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory prime materials
     */
    @Override
    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
    }

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop products in Shop
     */
    @Override
    public int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop products in Shop
     */
    @Override
    public void setnInitialProductsInShop(int nInitialProductsInShop){
        this.nInitialProductsInShop = nInitialProductsInShop;
    }

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    @Override
    public int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage prime materials in Storage
     */
    @Override
    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
    }

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct prime materials by product
     */
    @Override
    public int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct prime materials by product
     */
    @Override
    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
    }

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect Max Products Collect
     */
    @Override
    public int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect Max Products Collect
     */
    @Override
    public void setnMaxProductsCollect(int nMaxProductsCollect){
        this.nMaxProductsCollect = nMaxProductsCollect;
    }

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    @Override
    public int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock Min Prime Materials For Restock
     */
    @Override
    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
    }

    /**
     * Get total Products.
     * @return totalProducts total Products
     */
    @Override
    public int gettotalProducts(){
        return totalProducts;
    }

    /**
     * Set total Products.
     * @param totalProducts total Products
     */
    @Override
    public void settotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }

    /**
     * Get Repository Host Name
     * @return getRepositoryHostName host name
     */
    public String getRepositoryHostName(){
        return repositoryHostName;
    }

    /**
     * setRepositoryHostName
     * @param repositoryHostName host name
     */
    public void setRepositoryHostName(String repositoryHostName){
        this.repositoryHostName = repositoryHostName;
    }

    /**
     * getRepositoryPortNum
     * @return getRepositoryPortNum repositoryPortNum
     */
    public int getRepositoryPortNum(){
        return repositoryPortNum;
    }

    /**
     * setRepositoryPortNum
     * @param repositoryPortNum port number
     */
    public void setRepositoryPortNum(int repositoryPortNum){
        this.repositoryPortNum = repositoryPortNum;
    }

    /**
     * getShopHostName
     * @return shopHostName host name
     */
    public String getShopHostName(){
        return shopHostName;
    }

    /**
     * setShopHostName
     * @param shopHostName host name
     */
    public void setShopHostName(String shopHostName){
        this.shopHostName = shopHostName;
    }

    /**
     * getShopPortNum
     * @return shopPortNum port number
     */
    public int getShopPortNum(){
        return shopPortNum;
    }

    /**
     * setShopPortNum
     * @param shopPortNum port number
     */
    public void setShopPortNum(int shopPortNum){
        this.shopPortNum = shopPortNum;
    }

    /**
     * getStorageHostName
     * @return storageHostName host name
     */
    public String getStorageHostName(){
        return storageHostName;
    }

    /**
     * setStorageHostName
     * @param storageHostName host name
     */
    public void setStorageHostName(String storageHostName){
        this.storageHostName = storageHostName;
    }

    /**
     * getStoragePortNum
     * @return storagePortNum port number
     */
    public int getStoragePortNum(){
        return storagePortNum;
    }

    /**
     * setStoragePortNum
     * @param storagePortNum port number
     */
    public void setStoragePortNum(int storagePortNum){
        this.storagePortNum = storagePortNum;
    }

    /**
     * getFactoryHostName
     * @return factoryHostName host name
     */
    public String getFactoryHostName(){
        return factoryHostName;
    }

    /**
     * setFactoryHostName
     * @param factoryHostName host name
     */
    public void setFactoryHostName(String factoryHostName){
        this.factoryHostName = factoryHostName;
    }

    /**
     * getFactoryPortNum
     * @return factoryPortNum port number
     */
    public int getFactoryPortNum(){
        return factoryPortNum;
    }

    /**
     * setFactoryPortNum
     * @param factoryPortNum port number
     */
    public void setFactoryPortNum(int factoryPortNum){
        this.factoryPortNum = factoryPortNum;
    }
    
}
