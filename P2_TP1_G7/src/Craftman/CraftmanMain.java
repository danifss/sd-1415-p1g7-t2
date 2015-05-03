package Craftman;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CraftmanMain {
    public static void main(String[] args){
        //TODO: create and start 3 Craftmans
        int nCraftmans = 3;
        // Array de Craftmans
        Craftman[] craftman = new Craftman[nCraftmans];
        // PORTAS: 221GX -> G = grupo 7 -> X 0-9
        CraftmanBroker broker = new CraftmanBroker("localhost", 22171, "localhost", 22170, "localhost", 22172);
        
        //Initialization of Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i] = new Craftman(i, broker, broker, broker);
        
        // Starting Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i].start();
    }
}
