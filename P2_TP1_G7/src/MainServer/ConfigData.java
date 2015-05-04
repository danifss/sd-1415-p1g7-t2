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
     * @param nPrimeMaterialsInFactory 
     */
    @Override
    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
    }

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop
     */
    @Override
    public int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop 
     */
    @Override
    public void setnInitialProductsInShop(int nInitialProductsInShop){
        this.nInitialProductsInShop = nInitialProductsInShop;
    }

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage
     */
    @Override
    public int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage 
     */
    @Override
    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
    }

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct
     */
    @Override
    public int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct 
     */
    @Override
    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
    }

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect
     */
    @Override
    public int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect 
     */
    @Override
    public void setnMaxProductsCollect(int nMaxProductsCollect){
        this.nMaxProductsCollect = nMaxProductsCollect;
    }

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock
     */
    @Override
    public int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock 
     */
    @Override
    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
    }

    /**
     * Get total Products.
     * @return totalProducts
     */
    @Override
    public int gettotalProducts(){
        return totalProducts;
    }

    /**
     * Set total Products.
     * @param totalProducts 
     */
    @Override
    public void settotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }

    public String getRepositoryHostName(){
        return repositoryHostName;
    }

    public void setRepositoryHostName(String repositoryHostName){
        this.repositoryHostName = repositoryHostName;
    }

    public int getRepositoryPortNum(){
        return repositoryPortNum;
    }

    public void setRepositoryPortNum(int repositoryPortNum){
        this.repositoryPortNum = repositoryPortNum;
    }

    public String getShopHostName(){
        return shopHostName;
    }

    public void setShopHostName(String shopHostName){
        this.shopHostName = shopHostName;
    }

    public int getShopPortNum(){
        return shopPortNum;
    }

    public void setShopPortNum(int shopPortNum){
        this.shopPortNum = shopPortNum;
    }

    public String getStorageHostName(){
        return storageHostName;
    }

    public void setStorageHostName(String storageHostName){
        this.storageHostName = storageHostName;
    }

    public int getStoragePortNum(){
        return storagePortNum;
    }

    public void setStoragePortNum(int storagePortNum){
        this.storagePortNum = storagePortNum;
    }

    public String getFactoryHostName(){
        return factoryHostName;
    }

    public void setFactoryHostName(String factoryHostName){
        this.factoryHostName = factoryHostName;
    }

    public int getFactoryPortNum(){
        return factoryPortNum;
    }

    public void setFactoryPortNum(int factoryPortNum){
        this.factoryPortNum = factoryPortNum;
    }
    
}
