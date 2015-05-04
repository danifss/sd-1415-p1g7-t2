package Customer;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public interface CustomerRepositoryInterface {

    /**
     * Set Customer[i] State.
     *
     * @param customerId Customer id
     * @param state State of the Customer
     */
    void setCustomerState(int customerId, int state);

    /**
     * Set number of customers inside.
     *
     * @param customerId id
     * @param nCustomersInsideShop Number of customers inside
     */
    void setnCustomersInsideShop(int customerId, int nCustomersInsideShop);
    
    /**
     * Set number of goods (accumulation) bought by the customer.
     *
     * @param customerId Customer id
     * @param nGoods Number of goods bought by the customer
     */
    void setnGoodsByCustomer(int customerId, int nGoods);
}
