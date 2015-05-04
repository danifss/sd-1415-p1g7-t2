package Storage;

import comInf.MessageException;
import comInf.MessageStorage;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma solução do Aveiro
 * Handicraft que implementa o modelo cliente-servidor de tipo 2 (replicação do servidor) com
 * lançamento estático dos threads Shop. A comunicação baseia-se em passagem de mensagens sobre
 * sockets usando o protocolo TCP.
 */
public class ClientProxy extends Thread {

    /**
     * Threads counter
     *
     * @serial nProxy
     */
    private static int nProxy;

    /**
     * Communication Channel.
     *
     * @serial sconi
     */
    private ServerCom sconi;

    /**
     *
     *
     * @serial storageInter
     */
    private StorageBroker storageInter;

    /**
     * Instanciação do interface à Storage.
     *
     * @param sconi canal de comunicação
     * @param storageInter interface à Storage
     */
    public ClientProxy(ServerCom sconi, StorageBroker storageInter) {
        super("Proxy_" + getProxyId());

        this.sconi = sconi;
        this.storageInter = storageInter;
    }

    /**
     * Ciclo de vida do thread agente prestador de serviço.
     */
    @Override
    public void run() {
        MessageStorage inMessage = null,                            // mensagem de entrada
                outMessage = null;                                  // mensagem de saída

        inMessage = (MessageStorage) sconi.readObject();            // ler pedido do cliente
        try {
            outMessage = storageInter.processAndReply(inMessage);   // processá-lo
        } catch (MessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMessageVal().toString());
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
            cl = (Class<ClientProxy>) Class.forName("Storage.ClientProxy");
        } catch (ClassNotFoundException e) {
            System.out.println("O tipo de dados ClientProxy não foi encontrado!");
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
