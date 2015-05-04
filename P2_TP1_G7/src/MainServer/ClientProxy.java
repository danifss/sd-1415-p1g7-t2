package MainServer;

import comInf.MessageException;
import comInf.MessageConfig;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma solução do Aveiro
 * Handicraft que implementa o modelo cliente-servidor de tipo 2 (replicação do servidor) com
 * lançamento estático dos threads. A comunicação baseia-se em passagem de mensagens sobre
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
     * @serial configInter
     */
    private ConfigBroker configInter;

    /**
     * Instanciação do interface ao ConfigBroker.
     *
     * @param sconi canal de comunicação
     * @param configInter interface ao ConfigBroker
     */
    public ClientProxy(ServerCom sconi, ConfigBroker configInter) {
        super("Proxy_" + getProxyId());

        this.sconi = sconi;
        this.configInter = configInter;
    }

    /**
     * Ciclo de vida do thread agente prestador de serviço.
     */
    @Override
    public void run() {
        MessageConfig inMessage = null,                               // mensagem de entrada
                outMessage = null;                                        // mensagem de saída

        inMessage = (MessageConfig) sconi.readObject();               // ler pedido do cliente
        try {
            outMessage = configInter.processAndReply(inMessage);      // processá-lo
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
            cl = (Class<ClientProxy>) Class.forName("MainServer.ClientProxy");
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
