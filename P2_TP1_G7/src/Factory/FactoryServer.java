package Factory;

import genclass.GenericIO;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class FactoryServer {

    /**
     * Server Factory port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22172;
    
    public static void main(String[] args){
        Factory factory;                                        // Shop (servico a ser prestado)
        FactoryBroker factoryInterface;                         // Interface a Shop
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        FactoryBrokerRepository repository;                     // Repository Broker client
        
        int nCraftmans = 3;
        int nPrimeMaterialsInFactory = 10;
        int nTotalPrime = 20;
        int nPrimePerProduct = 2;
        int nPrimeRestock = 10;
        int nProductsCollect = 5;
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        //repository = new FactoryBrokerRepository("localhost", 22170); // ativacao do servico
        repository = new FactoryBrokerRepository("l040101-ws01.ua.pt", 22170); // ativacao do servico
        factory = new Factory(repository, nPrimeMaterialsInFactory, nTotalPrime, nPrimePerProduct, nPrimeRestock, nProductsCollect); // activacao do servico
        //factoryInterface = new FactoryBroker(factory, "localhost", 22170, nCraftmans); // activacao do interface com o servico
        factoryInterface = new FactoryBroker(factory, "l040101-ws01.ua.pt", 22170, nCraftmans); // activacao do interface com o servico
        GenericIO.writelnString("Factory");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                                  // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, factoryInterface);    // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
