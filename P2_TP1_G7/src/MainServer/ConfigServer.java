package MainServer;

import genclass.GenericIO;

/**
 *
 * @author silva
 */
public class ConfigServer {
    
    /**
     * Configurations Server port number.
     * 
     * @serialField portNumb
     */
    private static final int portNumb = 22177;
    
    /**
     * Servidor de configuracoes globais. Todos se vao ligar aqui para darem e pedirem
     * informacao que e relevante. Clientes perguntam por servidores, e servidores informam
     * os seus ip's e portas.
     * Configura-se também os valores pre-definidos para o problema. (num. clientes etc)
     * 
     * @param args 
     */
    public static void main(String[] args){
        
        ConfigData data = new ConfigData();

        // String fName = "log.txt";
        // int nCraftsman = 3;
        // int nCustomers = 3;
        // int nPrimeMaterialsInFactory = 10;
        // int nInitialProductsInShop = 10;
        // int nInitialPrimeMaterialsInStorage = 20;
        // int nPrimeMaterialsByProduct = 2;
        // int nPrimeOwnerCarry = 10;
        // int nMinPrimeMaterialsForRestock = 10;
        // int nMaxProductsCollect = 5;

        GenericIO.writelnString("\tConfiguration Server of problem 2: Aveiro Handicraft\n");
        
        // Option to select values when the program starts.
        GenericIO.writeString("Use default values?(y/n) ");
        if(GenericIO.readlnString().equalsIgnoreCase("n")){
            // Logging file name
            GenericIO.writeString ("Name of logging file? ");
            data.setfName(GenericIO.readlnString());

            // nCustomers
            GenericIO.writeString("Number of Customers: ");
            data.setnCustomers(GenericIO.readlnInt());

            // nCraftmans
            GenericIO.writeString("Number of Craftmans: ");
            data.setnCraftmans(GenericIO.readlnInt());

            // Initial number of prime materials in the Factory
            GenericIO.writeString("Number of initial prime materials in Factory: ");
            data.setnPrimeMaterialsInFactory(GenericIO.readlnInt());

            // Initial number of products in the Shop
            GenericIO.writeString("Number of initial products in Shop: ");
            data.setnInitialProductsInShop(GenericIO.readlnInt());

            // Initial number of prime materials in the Storage
            GenericIO.writeString("Number of initial prime materials in Storage: ");
            data.setnInitialPrimeMaterialsInStorage(GenericIO.readlnInt());
            
            // Prime materials needed per product
            GenericIO.writeString("Number of prime materials needed by product: ");
            data.setnPrimeMaterialsByProduct(GenericIO.readlnInt());

            // Minimum number of prime materials for restock
            GenericIO.writeString("Number of minimum prime materials for restock: ");
            data.setnMinPrimeMaterialsForRestock(GenericIO.readlnInt());

            // Maximum number of products that the owner can carry
            GenericIO.writeString("Number of maximum products that the owner can carry: ");
            data.setnMaxProductsCollect(GenericIO.readlnInt());
        }
        // Number of total products
        int totalProducts = ((data.getnPrimeMaterialsInFactory() + data.getnInitialPrimeMaterialsInStorage()) / data.getnPrimeMaterialsByProduct()) + data.getnInitialProductsInShop();
        data.settotalProducts(totalProducts);


        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        ConfigBroker configInterface;

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico

        GenericIO.writelnString("Configuration Server");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                              // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, configInterface); // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
