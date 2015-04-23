package Customer;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface CustomerShopInterface {

    /**
     * The customer enters the shop.
     */
    void enterShop(int customerId);

    /**
     * The Customer leaves the Shop.
     * If he his the last one leaving the Shop, wakes up the Owner (important to Owner
     * finish working, if he his waiting for all the Customers to buy products,
     * but the last one don't buy anything).
     */
    void exitShop(int customerId);

    /**
     * The Customer goes to the queue, and waits till the owner call him.
     * When the owner calls a Customer and he is in the front of the queue, he 
     * makes the purchase.
     * @param customerId Id of the customer that wants to buy something
     * @param nGoods Number of goods that the Customer wants to buy
     */
    void iWantThis(int customerId, int nGoods);

    /**
     * The Customer sees if the door is open
     * @return True if the shop is OPEN
     */
    boolean isDoorOpen(int customerId);

    /**
     * The Customer is perusing around.
     * A random number is generated, then if it is less than 0.5 and 
     * exists goods to buy, that number*100 is divided by the number 
     * of goods for sell and returns his remainder.
     * @return number of goods to buy
     */
    int perusingAround(int customerId);
    
    /**
     * See if the owner and the customer can stop.
     * Checks if all products have been transferred to Shop, and all the products
     * are sold.
     * @return true if they can stop working
     */
    boolean endOper();
}
