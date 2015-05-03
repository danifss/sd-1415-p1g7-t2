package MainServer;


public class ConfigData {

	private String fName = "log.txt";
	private int nCraftmans = 3;
    private int nCustomers = 3;
    private int nPrimeMaterialsInFactory = 10;
    private int nInitialProductsInShop = 10;
    private int nInitialPrimeMaterialsInStorage = 20;
    private int nPrimeMaterialsByProduct = 2;
    private int nPrimeOwnerCarry = 10;
    private int nMinPrimeMaterialsForRestock = 10;
    private int nMaxProductsCollect = 5;
    private int totalProducts = 0;

    /**
     * Configurations Server Constructor
     */
	public ConfigData(){
	}

    
    
    public String getfName(){
        return fName;
    }

    public void setfName(String fName){
        this.fName = fName;
    }

    public int getnCraftmans(){
        return nCraftmans;
    }

    public void setnCraftmans(int nCraftmans){
        this.nCraftmans = nCraftmans;
    }

    public int getnCustomers(){
        return nCustomers;
    }

    public void setnCustomers(int nCustomers){
        this.nCustomers = nCustomers;
    }

    public int getnPrimeMaterialsInFactory(){
        return nPrimeMaterialsInFactory;
    }

    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory){
        this.nPrimeMaterialsInFactory = nPrimeMaterialsInFactory;
    }

    public int getnInitialProductsInShop(){
        return nInitialProductsInShop;
    }

    public void setnInitialProductsInShop(int nInitialProductsInShop){
        this.nInitialProductsInShop = nInitialProductsInShop;
    }

    public int getnInitialPrimeMaterialsInStorage(){
        return nInitialPrimeMaterialsInStorage;
    }

    public void setnInitialPrimeMaterialsInStorage(int nInitialPrimeMaterialsInStorage){
        this.nInitialPrimeMaterialsInStorage = nInitialPrimeMaterialsInStorage;
    }

    public int getnPrimeMaterialsByProduct(){
        return nPrimeMaterialsByProduct;
    }

    public void setnPrimeMaterialsByProduct(int nPrimeMaterialsByProduct){
        this.nPrimeMaterialsByProduct = nPrimeMaterialsByProduct;
    }

    public int getnPrimeOwnerCarry(){
        return nPrimeOwnerCarry;
    }

    public void setnPrimeOwnerCarry(int nPrimeOwnerCarry){
        this.nPrimeOwnerCarry = nPrimeOwnerCarry;
    }

    public int getnMinPrimeMaterialsForRestock(){
        return nMinPrimeMaterialsForRestock;
    }

    public void setnMinPrimeMaterialsForRestock(int nMinPrimeMaterialsForRestock){
        this.nMinPrimeMaterialsForRestock = nMinPrimeMaterialsForRestock;
    }

    public int getnMaxProductsCollect(){
        return nMaxProductsCollect;
    }

    public void setnMaxProductsCollect(int nMaxProductsCollect){
        this.nMaxProductsCollect = nMaxProductsCollect;
    }

    public int gettotalProducts(){
        return totalProducts;
    }

    public void settotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
    }
    
}
