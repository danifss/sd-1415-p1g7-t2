package Shop;

import comInf.MessageException;
import comInf.MessageShop;

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
     * @serial bShopInter
     */
    private ShopBroker shopInter;

    /**
     * Instanciação do interface à barbearia.
     *
     * @param sconi canal de comunicacao
     * @param shopInter interface a barbearia
     */
    public ClientProxy(ServerCom sconi, ShopBroker shopInter) {
        super("Proxy_" + getProxyId());

        this.sconi = sconi;
        this.shopInter = shopInter;
    }

    /**
     * Ciclo de vida do thread agente prestador de serviço.
     */
    @Override
    public void run() {
        MessageShop inMessage = null,                               // mensagem de entrada
                outMessage = null;                                  // mensagem de saída

        inMessage = (MessageShop) sconi.readObject();               // ler pedido do cliente
        try {
            outMessage = shopInter.processAndReply(inMessage);      // processá-lo
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
            cl = (Class<ClientProxy>) Class.forName("Shop.ClientProxy");
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
