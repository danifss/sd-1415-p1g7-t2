package Owner;

import Craftman.*;
import genclass.GenericIO;
import java.io.*;
import java.net.*;

/**
 * This Object implements the communication channel, on client side, directed to a communication
 * based on message passing over sockets using TCP protocol. The transfer of data is based in
 * objects, one object at a time.
 */
public class ClientCom {

    /**
     * Communication Socket
     *
     * @serialField commSocket
     */
    private Socket commSocket = null;

    /**
     * Name of the computacional system where is located the server.
     *
     * @serialField serverHostName
     */
    private String serverHostName = null;

    /**
     * Number of listening port of the server.
     *
     * @serialField serverPortNumb
     */
    private int serverPortNumb;

    /**
     * Entrance stream of communication channel
     *
     * @serialField in
     */
    private ObjectInputStream in = null;

    /**
     * Exit stream of communication channel
     *
     * @serialField out
     */
    private ObjectOutputStream out = null;

    /**
     * Instantiation of communication channel
     *
     * @param hostName Name of computacional system where is located the server
     * @param portNumb Number of the listening port of server
     */
    public ClientCom(String hostName, int portNumb) {
        serverHostName = hostName;
        serverPortNumb = portNumb;
    }

    /**
     * Open communication channel. Instantiation of communication socket and his association to
     * server address. Opening of in and out streams of socket.
     *
     * @return true if the communication channel was open false otherwise
     */
    public boolean open() {
        boolean success = true;
        SocketAddress serverAddress = new InetSocketAddress(serverHostName, serverPortNumb);

        try {
            commSocket = new Socket();
            commSocket.connect(serverAddress);
        } catch (UnknownHostException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o nome do sistema computacional onde reside o servidor é desconhecido: "
                    + serverHostName + "!");
            e.printStackTrace();
            System.exit(1);
        } catch (NoRouteToHostException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o nome do sistema computacional onde reside o servidor é inatingível: "
                    + serverHostName + "!");
            e.printStackTrace();
            System.exit(1);
        } catch (ConnectException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o servidor não responde em: " + serverHostName + "." + serverPortNumb + "!");
            if (e.getMessage().equals("Connection refused")) {
                success = false;
            } else {
                GenericIO.writelnString(e.getMessage() + "!");
                e.printStackTrace();
                System.exit(1);
            }
        } catch (SocketTimeoutException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - ocorreu um time out no estabelecimento da ligação a: "
                    + serverHostName + "." + serverPortNumb + "!");
            success = false;
        } catch (IOException e) // erro fatal --- outras causas
        {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - ocorreu um erro indeterminado no estabelecimento da ligação a: "
                    + serverHostName + "." + serverPortNumb + "!");
            e.printStackTrace();
            System.exit(1);
        }

        if (!success) {
            return (success);
        }

        try {
            out = new ObjectOutputStream(commSocket.getOutputStream());
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de saída do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            in = new ObjectInputStream(commSocket.getInputStream());
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de entrada do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        return (success);
    }

    /**
     * Communication channel closed. Socket in and out streams closed. Communication socket closed.
     */
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de entrada do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            out.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de saída do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            commSocket.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reading an object from the communication channel.
     *
     * @return Read object
     */
    public Object readObject() {
        Object fromServer = null;                            // objecto

        try {
            fromServer = in.readObject();
        } catch (InvalidClassException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto lido não é passível de desserialização!");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - erro na leitura de um objecto do canal de entrada do socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto lido corresponde a um tipo de dados desconhecido!");
            e.printStackTrace();
            System.exit(1);
        }

        return fromServer;
    }

    /**
     * Writing an object to the communication channel.
     *
     * @param toServer object to be written
     */
    public void writeObject(Object toServer) {
        try {
            out.writeObject(toServer);
        } catch (InvalidClassException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto a ser escrito não é passível de serialização!");
            e.printStackTrace();
            System.exit(1);
        } catch (NotSerializableException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto a ser escrito pertence a um tipo de dados não serializável!");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - erro na escrita de um objecto do canal de saída do socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
