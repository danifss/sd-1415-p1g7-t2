package Craftman;

/**
 *
 * @author Daniel
 */
public class CraftmanMain {
    public static void main(String[] args){
        //TODO: create and start 3 Craftmans
        int nCraftmans = 3;
        // Array de Craftmans
        Craftman[] craftman = new Craftman[nCraftmans];
        // PORTAS: 221GX -> G = grupo 7 -> X 0-9
        CraftmanBroker broker = new CraftmanBroker("localhost", 22170, "localhost", 220171, "localhost", 220172);
        
        //Initialization of Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i] = new Craftman(i, broker, broker, broker);
        
        // Starting Craftmans
        for (int i = 0; i < nCraftmans; i++)
            craftman[i].start();
    }
}
