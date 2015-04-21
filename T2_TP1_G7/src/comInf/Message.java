package comInf;

import java.io.*;

/**
 * Este tipo de dados define as mensagens que são trocadas entre os clientes e o servidor numa
 * solução do Problema dos Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A comunicação propriamente
 * dita baseia-se na troca de objectos de tipo Message num canal TCP.
 */
public abstract class Message implements Serializable {

    /**
     * Chave de serialização
     *
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 1001L;

    /**
     * Operação realizada com sucesso (resposta enviada pelo servidor)
     *
     * @serialField ACK
     */
    public static final int ACK = 0;

    /**
     * Tipo da mensagem
     *
     * @serialField msgType
     */
    private int msgType = -1;

    /**
     * Instanciação de uma mensagem (forma 1).
     *
     * @param type tipo da mensagem
     */
    public Message(int type) {
        msgType = type;
    }

    /**
     * Obtenção do valor do campo tipo da mensagem.
     *
     * @return tipo da mensagem
     */
    public int getType() {
        return (msgType);
    }

    /**
     * Impressão dos campos internos. Usada para fins de debugging.
     *
     * @return string contendo, em linhas separadas, a concatenação da identificação de cada campo e
     * valor respectivo
     */
    @Override
    public String toString() {
        return ("Tipo = " + msgType);
    }
}
