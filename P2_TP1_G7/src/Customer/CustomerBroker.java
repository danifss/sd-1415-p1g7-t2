package Customer;

import comInf.*;

/**
 * @author Daniel 51908
 * @author Raphael 64044
 * @version 2.0
 */
public class CustomerBroker implements CustomerShopInterface, CustomerRepositoryInterface {

    /**
     * Repository host name
     * @serial RPserverHostName
     */
    private String RPserverHostName = null;

    /**
     * Repository Server port
     * @serial RPserverPortNumb
     */
    private int RPserverPortNumb;
    
    /**
     * Shop host name
     * @serial ShopServerHostName
     */
    private String ShopServerHostName = null;

    /**
     * Shop Server port
     * @serial ShopServerPortNumb
     */
    private int ShopServerPortNumb;
    
    public CustomerBroker(String ShopServerHostName, int ShopServerPortNumb, String RPserverHostName, int RPserverPortNumb) {
        this.ShopServerHostName = ShopServerHostName;
        this.ShopServerPortNumb = ShopServerPortNumb;
        this.RPserverHostName = RPserverHostName;
        this.RPserverPortNumb = RPserverPortNumb;
    }

    
    //*************** Shop communication
    /**
     * Communicate with Shop: Customer verifies if the door is open.
     * @param customerId id
     * @return if door is open or not
     */
    @Override
    public boolean isDoorOpen(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.CHKDOOROPEN, customerId); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        boolean result = false;
        switch(inMessage.getType()){
            case MessageShop.DOOROPEN:
                result = true;
                break;
            case MessageShop.DOORCLOSED:
                //result =  false; // Nao precisa
                break;
            default:
                System.out.println("Customer: "+customerId+" - Error checking Shop door.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }
    
    /**
     * Communicate with Shop: Customer enter in the Shop.
     * @param customerId id
     */
    @Override
    public void enterShop(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.ENTERSHOP, customerId); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: "+customerId+" - Error entering in the Shop.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }
    
    /**
     * Communicate with Shop: Customer perusingAround in Shop and chooses what to buy.
     * @param customerId id
     * @return number of products to Customer buy
     */
    @Override
    public int perusingAround(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.PERUSINGAROUND, customerId); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        int result = 0;
        switch(inMessage.getType()){
            case Message.ACK:
                result = inMessage.getValue();
                break;
            default:
                System.out.println("Customer: "+customerId+" - Error on perusingAround in Shop.");
                System.out.println(inMessage.toString());
                System.exit(1);
        }
        con.close();
        return result;
    }
    
    /**
     * Communicate with Shop: Customer buys the products.
     * @param customerId id
     * @param nGoods Number of goods to buy
     */
    @Override
    public void iWantThis(int customerId, int nGoods) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.IWANTTHIS, customerId, nGoods); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: "+customerId+" - Error iWantThis in Shop.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Shop: Customer wants to exit from Shop.
     * @param customerId  id
     */
    @Override
    public void exitShop(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.EXITSHOP, customerId); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: "+customerId+" - Error on exiting from Shop.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Shop: Customer checks if can die.
     * @param customerId id
     * @return true if can die and false in otherwise
     */
    @Override
    public boolean endOper(int customerId) {
        ClientCom con = new ClientCom(ShopServerHostName, ShopServerPortNumb);
        MessageShop inMessage, outMessage;
        
        outMessage = new MessageShop(MessageShop.ENDOPER, customerId); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageShop) con.readObject();
        boolean result = true;
        switch(inMessage.getType()){
            case Message.ACK:
                if(inMessage.isEndOper())
                    result = true;
                else
                    result = false;
                break;
            default:
                System.out.println("Customer: "+customerId+" - Error checking endOper in Shop.");
                System.out.println(inMessage.toString());
                System.exit(1);
                break;
        }
        con.close();
        return result;
    }

    
    //*************** Repository communication
    /**
     * Communicate with Repository: Set Customer present state.
     * @param customerId id
     * @param state Customer state
     */
    @Override
    public void setCustomerState(int customerId, int state) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETCUSTOMERSTATE, customerId, state); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: "+customerId+" - Error setting Customer state on Repository.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Repository: Set number of Customers inside the Shop.
     * @param customerId id
     * @param nCustomersInsideShop number of Customers
     */
    @Override
    public void setnCustomersInsideShop(int customerId, int nCustomersInsideShop) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETCUSTINSHOP, customerId, nCustomersInsideShop); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: - Error setting number of Customers inside the shop on Repository.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Communicate with Repository: Sum number of bought products for a specific Customer.
     * @param customerId id
     * @param nGoods number of products
     */
    @Override
    public void setnGoodsByCustomer(int customerId, int nGoods) {
        ClientCom con = new ClientCom(RPserverHostName, RPserverPortNumb);
        MessageRepository inMessage, outMessage;
        
        outMessage = new MessageRepository(MessageRepository.SETGOODSBYCUST, customerId, nGoods); // pede a realizacao do servico
        while (!con.open ()){                                 // aguarda ligação
            try{ 
                Thread.sleep ((long) (10));
            }catch (InterruptedException e) {}
        }
        con.writeObject(outMessage);
        inMessage = (MessageRepository) con.readObject();
        if (inMessage.getType() != Message.ACK) {
            System.out.println("Customer: - Error setting number of goods by Customer on Repository.");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

}
