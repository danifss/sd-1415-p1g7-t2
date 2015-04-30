package Repository;

import genclass.GenericIO;
import comInf.MessageException;
import comInf.MessageRepository;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma solução do Aveiro
 * Handicraft que implementa o modelo cliente-servidor de tipo 2 (replicação do servidor) com
 * lançamento estático dos threads Repository. A comunicação baseia-se em passagem de mensagens sobre
 * sockets usando o protocolo TCP.
 */
public class ClientProxy extends Thread {

    /**
     * Threads counter
     *
     * @serialField nProxy
     */
    private static int nProxy;

    /**
     * Communication Channel.
     *
     * @serialField sconi
     */
    private ServerCom sconi;

    /**
     *
     *
     * @serialField repositoryInter
     */
    private RepositoryBroker repositoryInter;

    /**
     * Instanciação do interface ao Repository.
     *
     * @param sconi canal de comunicação
     * @param repositoryInter interface ao Repository
     */
    public ClientProxy(ServerCom sconi, RepositoryBroker repositoryInter) {
        super("Proxy_" + getProxyId());

        this.sconi = sconi;
        this.repositoryInter = repositoryInter;
    }

    /**
     * Ciclo de vida do thread agente prestador de serviço.
     */
    @Override
    public void run() {
        MessageRepository inMessage = null,                               // mensagem de entrada
                outMessage = null;                                        // mensagem de saída

        inMessage = (MessageRepository) sconi.readObject();               // ler pedido do cliente
        try {
            outMessage = repositoryInter.processAndReply(inMessage);      // processá-lo
        } catch (MessageException e) {
            GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
            GenericIO.writelnString(e.getMessageVal().toString());
            System.exit(1);
        }
        sconi.writeObject(outMessage);                              // enviar resposta ao cliente
        sconi.close();                                              // fechar canal de comunicação
    }

    /**
     * Geração do identificador da instanciação.
     *
     * @return identificador da instanciação
     */
    private static int getProxyId() {
        Class<ClientProxy> cl = null;             // representação do tipo de dados ClientProxy na máquina
        //   virtual de Java
        int proxyId;                              // identificador da instanciação

        try {
            cl = (Class<ClientProxy>) Class.forName("Repository.ClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados ClientProxy não foi encontrado!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }
}
