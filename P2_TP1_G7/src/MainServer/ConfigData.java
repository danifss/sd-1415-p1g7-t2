package MainServer;


public class ConfigData {

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

    /**
     * Configurations Server Constructor
     */
	public ConfigData(){
	}

    
    /**
     * Get logging file name.
     * @return log name
     */
    public String getfName(){
        return fName;
    }

    /**
     * Logging file name.
     * @param fName file name
     */
    public void setfName(String fName){
        this.fName = fName;
    }

    /**
     * Number of Craftmans.
     * @return number of craftmans
     */
    public int getnCraftmans(){
        return nCraftmans;
    }

    /**
     * Number of Craftmans.
     * @param nCraftmans number of craftmans
     */
    public void setnCraftmans(int nCraftmans){
        this.nCraftmans = nCraftmans;
    }

    /**
     * Number of Customers.
     * @return number of customers
     */
    public int getnCustomers(){
        return nCustomers;
    }

    /**
     * Number of Customers.
     * @param nCustomers number of customers
     */
    public void setnCustomers(int nCustomers){
        this.nCustomers = nCustomers;
    }

    /**
     * Num Prime Materials In Factory.
     * @return nPrimeMaterialsInFactory
     */
    public int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }

    /**
     * Set num Prime Materials In Factory.
     * @param nPrimeMaterialsInFactory 
     */
    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
    }

    /**
     * Get num Initial Products In Shop.
     * @return nInitialProductsInShop
     */
    public int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    /**
     * Set num Initial Products In Shop.
     * @param nInitialProductsInShop 
     */
    public void setnInitialProductsInShop(int nInitialProductsInShop){
        this.nInitialProductsInShop = nInitialProductsInShop;
    }

    /**
     * Get num Initial Prime Materials In Storage.
     * @return nInitialPrimeMaterialsInStorage
     */
    public int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    /**
     * Set num Initial Prime Materials In Storage.
     * @param nInitialPrimeMaterialsInStorage 
     */
    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
    }

    /**
     * Get num Prime Materials By Product.
     * @return nPrimeMaterialsByProduct
     */
    public int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    /**
     * Set num Prime Materials By Product.
     * @param nPrimeMaterialsByProduct 
     */
    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
    }

    /**
     * Get num Max Products Collect.
     * @return nMaxProductsCollect
     */
    public int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    /**
     * Set num Max Products Collect.
     * @param nMaxProductsCollect 
     */
    public void setnMaxProductsCollect(int nMaxProductsCollect){
        this.nMaxProductsCollect = nMaxProductsCollect;
    }

    /**
     * Get num Min Prime Materials For Restock.
     * @return nMinPrimeMaterialsForRestock
     */
    public int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    /**
     * Set num Min Prime Materials For Restock.
     * @param nMinPrimeMaterialsForRestock 
     */
    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
    }

    /**
     * Get total Products.
     * @return totalProducts
     */
    public int gettotalProducts(){
        return totalProducts;
    }

    /**
     * Set total Products.
     * @param totalProducts 
     */
    public void settotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }
    
}
