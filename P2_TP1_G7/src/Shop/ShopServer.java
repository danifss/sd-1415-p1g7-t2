package Shop;

import genclass.GenericIO;

/**
 *
 * @author Daniel
 */
public class ShopServer {
    
    /**
     * Server Shop port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22170;
    
    public static void main(String[] args){
        //TODO: Aqui e onde corre o servidor e lanca threads(ClientProxy) para atender cada cliente.
        Shop shop;                                              // Shop (servico a ser prestado)
        ShopBroker shopInterface;                               // Interface a Shop
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico

        int nCustomers = 3;
        int nInitialProductsInShop = 10;                        // Initial number of products in the Shop
        int nInitialPrimeMaterialsInStorage = 20;               // Initial number of prime materials in the Storage
        int nPrimeMaterialsInFactory = 10;                      // Initial number of prime materials in the Factory
        int nPrimeMaterialsByProduct = 2;                       // Prime materials needed per product
        int totalProducts = ((nPrimeMaterialsInFactory + nInitialPrimeMaterialsInStorage) / nPrimeMaterialsByProduct) + nInitialProductsInShop;
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        shop = new Shop(nInitialProductsInShop, nCustomers, repository, totalProducts); // activacao do servico
        shopInterface = new ShopBroker(shop, nCustomers);       // activacao do interface com o servico
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                               // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, shopInterface);    // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
