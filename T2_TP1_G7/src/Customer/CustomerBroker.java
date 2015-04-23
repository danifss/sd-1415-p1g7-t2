package Customer;

import comInf.*;
import genclass.GenericIO;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CustomerBroker implements CustomerShopInterface, CustomerRepositoryInterface {

    /**
     * Repository host name
     * @serialField serverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serialField serverPortNumb
     */
    private int RPserverPortNumb;
    
    /**
     * Repository host name
     * @serialField serverHostName
     */
    private String ShopServerHostName = null;

    /**
     * Repository Server port
     * @serialField serverPortNumb
     */
    private int ShopServerPortNumb;
    
    public CustomerBroker(String ShopServerHostName, int ShopServerPortNumb, String RPserverHostName, int RPserverPortNumb) {
        this.ShopServerHostName = ShopServerHostName;
        this.ShopServerPortNumb = ShopServerPortNumb;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
    }

    
    // Shop communication
    @Override
    public void enterShop(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        Message inMessage, outMessage;

        outMessage = new MessageShop(MessageShop.ENTERSHOP, customerId); // pede a realizacao do servico
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if (inMessage.getType() != MessageShop.ENTERSHOPANSWER) {
            GenericIO.writelnString("Customer: "+customerId+" - invalid message.");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    @Override
    public void exitShop(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iWantThis(int customerId, int nGoods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int perusingAround(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDoorOpen(int customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean endOper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Repository communication
    
    @Override
    public void setCustomerState(int customerId, int state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setnCustomersInsideShop(int nCustomersInsideShop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setnGoodsByCustomer(int customerId, int nGoods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
