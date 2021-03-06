package MainServer;

import java.util.Scanner;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class ConfigServer {
    
    /**
     * Configurations Server port number.
     * 
     * @serial portNumb
     */
    private static final int portNumb = 22177;
    
    /**
     * Servidor de configuracoes globais. Todos se vao ligar aqui para darem e pedirem
     * informacao que e relevante. Clientes perguntam por servidores, e servidores informam
     * os seus ip's e portas.
     * Configura-se também os valores pre-definidos para o problema. (num. clientes etc)
     * 
     * @param args arguments
     */
    public static void main(String[] args){
        
        ConfigData data = new ConfigData();
        Scanner in = new Scanner(System.in);

        System.out.println("\tConfiguration Server of problem 2: Aveiro Handicraft\n");
        
        // Option to select values when the program starts.
        System.out.println("Use default values?(y/n) ");
        if(in.nextLine().equalsIgnoreCase("n")){
            // Logging file name
            System.out.println ("Name of logging file? ");
            data.setfName(in.nextLine());

            // nCustomers
            System.out.println("Number of Customers: ");
            data.setnCustomers(in.nextInt());

            // nCraftmans
            System.out.println("Number of Craftmans: ");
            data.setnCraftmans(in.nextInt());

            // Initial number of prime materials in the Factory
            System.out.println("Number of initial prime materials in Factory: ");
            data.setnPrimeMaterialsInFactory(in.nextInt());

            // Initial number of products in the Shop
            System.out.println("Number of initial products in Shop: ");
            data.setnInitialProductsInShop(in.nextInt());

            // Initial number of prime materials in the Storage
            System.out.println("Number of initial prime materials in Storage: ");
            data.setnInitialPrimeMaterialsInStorage(in.nextInt());
            
            // Prime materials needed per product
            System.out.println("Number of prime materials needed by product: ");
            data.setnPrimeMaterialsByProduct(in.nextInt());

            // Maximum number of products that the owner can carry
            System.out.println("Number of maximum products that the owner can carry: ");
            data.setnMaxProductsCollect(in.nextInt());            

            // Minimum number of prime materials for restock
            System.out.println("Number of minimum prime materials for restock: ");
            data.setnMinPrimeMaterialsForRestock(in.nextInt());
            
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
        
        configInterface = new ConfigBroker(data);               // activacao do interface com o servico

        System.out.println("Configuration Server");
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                              // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, configInterface); // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
