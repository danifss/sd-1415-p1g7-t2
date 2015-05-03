package Storage;

import genclass.GenericIO;

/**
 *
 * @author Daniel
 */
public class StorageServer {
    
    /**
     * Server Storage port number.
     * @serialField portNumb
     */
    private static final int portNumb = 22173;
    
    public static void main(String[] args){
        Storage storage;
        StorageBroker storageInterface;
        ServerCom scon, sconi;                                  // canais de comunicacao
        ClientProxy cliProxy;                                   // thread agente prestador do servico
        
        int nInitialPrimeMaterialsInStorage = 20;               // Initial number of prime materials in the Storage
        int nPrimeOwnerCarry = 10;                              // Number of prime materials that the owner can carry
        
        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                         // criacao do canal de escuta e sua associacao
        scon.start();                                           // com o endereco publico
        
        storage = new Storage(nInitialPrimeMaterialsInStorage, nPrimeOwnerCarry);
        storageInterface = new StorageBroker(storage);
        GenericIO.writelnString("Storage");
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

        /* processamento de pedidos */
        while (true) {
            sconi = scon.accept();                               // entrada em processo de escuta
            cliProxy = new ClientProxy(sconi, storageInterface); // lancamento do agente prestador do servico
            cliProxy.start();
        }
    }
}
