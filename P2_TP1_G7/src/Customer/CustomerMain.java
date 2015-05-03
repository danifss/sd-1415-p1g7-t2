package Customer;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CustomerMain {
    public static void main(String[] args){
        //TODO: create and start 3 Customers
        int nCustomers = 10;
        // Array de Clientes
        Customer[] customer = new Customer[nCustomers];
        // PORTAS: 221GX -> G = grupo 7 -> X 0-9
        //CustomerBroker broker = new CustomerBroker("localhost", 22171, "localhost", 22170);
        CustomerBroker broker = new CustomerBroker("l040101-ws03.ua.pt", 22171, "l040101-ws01.ua.pt", 22170);
        
        //Initialization of Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i] = new Customer(broker, i, broker);
        
        // Starting Customers
        for (int i = 0; i < nCustomers; i++)
            customer[i].start();
    }
}
