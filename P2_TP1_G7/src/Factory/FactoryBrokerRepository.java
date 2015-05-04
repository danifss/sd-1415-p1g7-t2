package Factory;

import comInf.MessageRepository;
import genclass.GenericIO;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
class FactoryBrokerRepository implements FactoryRepositoryInterface {

    /**
     * Repository server host name
     * @serialField RPserverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serialField RPserverPortNumb
     */
    private int RPserverPortNumb;
    
    
    /**
     * Constructor of ShopRepository
     * @param RPserverHostName      Repository Server Host Name
     * @param RPserverPortNumb      Repository Server Port Number
     */
    public FactoryBrokerRepository(String RPserverHostName, int RPserverPortNumb) {
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
    }
    
    
    /**
     * Change the amount of prime materials presently in the Factory.
     * @param nPrimeMaterialsInFactory Amount of prime materials available in the Factory
     */
    @Override
    public void setnPrimeMaterialsInFactory(int nPrimeMaterialsInFactory) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETPRIMEMATERIALSINFACT, -1, nPrimeMaterialsInFactory);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Factory: - Error setting number of prime materials in Factory.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Change the number of finished products presently in the Factory.
     * @param nFinishedProductsInFactory Number of finished products in the Factory
     */
    @Override
    public void setnFinishedProductsInFactory(int nFinishedProductsInFactory) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETFINISHEDPRODUCTSINFACT, -1, nFinishedProductsInFactory);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Factory: - Error setting number of finished products in factory.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Change the total number of products that have already been manufactured (accumulation).
     * @param nProductsManufactured Total number of products produced
     */
    @Override
    public void setnProductsManufactured(int nProductsManufactured) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETPRODSMANUFACTURED, -1, nProductsManufactured);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Factory: - Error setting number of products manufactured.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Change the number of times that a supply of prime materials was delivered to the Factory.
     * @param nSuppliedTimes Number of times that the owner delivered prime materials
     */
    @Override
    public void setnSuppliedTimes(int nSuppliedTimes) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETSUPPLIEDTIMES, -1, nSuppliedTimes);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Factory: - Error setting number of supplied times.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Change the total amount of prime materials that have already been supplied (accumulation).
     * @param nPrimeMaterialsSupplied Number of prime materials supplied
     */
    @Override
    public void setnPrimeMaterialsSupplied(int nPrimeMaterialsSupplied) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETPRIMEMATSUPPLIED, -1, nPrimeMaterialsSupplied);
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if(inMessage.getType() != MessageRepository.ACK){
            GenericIO.writelnString("Factory: - Error setting number of prime materials supplied.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
}
