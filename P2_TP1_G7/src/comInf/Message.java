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
     * Serialization key.
     *
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 220415L;

    /**
     * Operation completed with success. (Server response)
     *
     * @serialField ACK
     */
    public static final int ACK = 0;

    /**
     * Message type.
     *
     * @serialField msgType
     */
    private int msgType = -1;

    /**
     * Customer ID
     *
     * @serialField customerId
     */
    private int customerId = -1;
    
    /**
     * Craftman ID
     * 
     * @serialField craftmanId
     */
    private int craftmanId = -1;

    /**
     * Instantiation of a message (form 1)
     *
     * @param type Message type
     */
    public Message(int type) {
        msgType = type;
    }

    /**
     * Instantiation of a message (form 2)
     *
     * @param type Message type
     * @param customerId Customer ID
     */
    public Message(int type, int customerId) {
        msgType = type;
        this.customerId = customerId;
    }

    /**
     * Get the type of the Message.
     *
     * @return Message type
     */
    public int getType() {
        return msgType;
    }

    /**
     * Get the Customer Id
     * @return customer Id
     */
    public int getCustId() {
        return customerId;
    }

    /**
     * Get the Craftman Id
     * @return craftman Id
     */
    public int getCraftId() {
        return craftmanId;
    }
    
    /**
     * Printing internal fields. Used to debugging.
     *
     * @return string with field and his value respectively
     */
    @Override
    public String toString() {
        return ("Tipo = " + msgType + "\nCustomer ID = " + customerId);
    }
}
