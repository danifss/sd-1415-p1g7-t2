package Owner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class OwnerMain {
    public static void main(String[] args){
        Owner owner;
        OwnerBroker broker = new OwnerBroker("localhost", 22170, "localhost", 220171, "localhost", 220172, "localhost", 220173);
        
        owner = new Owner(broker, broker, broker, broker);
        
        owner.start();
    }
}
