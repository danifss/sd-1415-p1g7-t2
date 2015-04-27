package Customer;

/**
 *
 * @author Daniel
 */
public class CustomerMain {
    public static void main(String[] args){
        //TODO: create and start 3 Customers
        int nCustomers = 3;
        Customer[] customer = new Customer[nCustomers];
        CustomerBroker broker = new CustomerBroker("0.0.0.0", 220101, "0.0.0.0", 220102);
        
        //Initialization of Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i] = new Customer(broker, i, broker);
        
        // Starting Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i].start();
    }
}
