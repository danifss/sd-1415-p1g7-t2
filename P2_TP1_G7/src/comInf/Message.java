package comInf;

import java.io.*;

/**
 * Este tipo de dados define as mensagens que são trocadas entre os clientes e o servidor numa
 * solução do Problema Aveiro Handicraft que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads. A comunicação propriamente
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
     * Operation with error. (Server response)
     *
     * @serialField ERROR
     */
    public static final int ERROR = -1;
    
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
     * ID
     *
     * @serialField id
     */
    private int id = -1;

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
     * @param id ID
     */
    public Message(int type, int id) {
        msgType = type;
        this.id = id;
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
     * Get the Id
     * @return Id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Printing internal fields. Used to debugging.
     *
     * @return string with field and his value respectively
     */
    @Override
    public String toString() {
        return ("Tipo = " + msgType + "\nID = " + id);
    }
}
