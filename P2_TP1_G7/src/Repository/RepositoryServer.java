package Repository;

import genclass.GenericIO;

/**
 *
 * @author Daniel
 */
public class RepositoryServer {

    /**
     * Server Repository port number.
     *
     * @serialField portNumb
     */
    private static final int portNumb = 22170;

    public static void main(String[] args){
        //TODO: Aqui e onde corre o servidor e lanca threads(ClientProxy) para atender cada cliente.
        Repository repository;                                  // Repository (servico a ser prestado)
        RepositoryBroker repositoryInterface;                   // Interface a Repository
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico

        int nCustomers = 3;
        int nCraftmans = 3;
        String fName = "log.txt";
        int nPrimeMaterialsInFactory = 10;                      // Initial number of prime materials in the Factory

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        repository = new Repository(nCraftmans, nCustomers, fName, nPrimeMaterialsInFactory); // activacao do servico
        repositoryInterface = new RepositoryBroker(repository, nCustomers, nCraftmans);       // activacao do interface com o servico
        GenericIO.writelnString("Repository");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true){
            sconi = scon.accept();                              // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, repositoryInterface);    // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
