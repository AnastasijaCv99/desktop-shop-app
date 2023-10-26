/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
//import server.repository.db.impl.RepositoryOrder;
//import server.repository.db.impl.RepositoryOrderItem;
//import server.repository.db.impl.RepositoryProduct;
import server.so.AbstractSO;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class AddNewOrderSO extends AbstractSO{

    //private final DbRepository dbRepositoryOrderItems;
    //private final DbRepository dbRepositoryOrder;
    //private final DbRepository dbRepositoryProduct;
    private final DbRepository repositoryGeneric;
    
    public AddNewOrderSO() {
        //this.dbRepositoryOrderItems = new RepositoryOrderItem();
        //this.dbRepositoryOrder = new RepositoryOrder();
        //this.dbRepositoryProduct = new RepositoryProduct();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    Order newOrder;

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof Order)) {
            throw new Exception("Invalid data!");
        } else if (((Order)param).getListOfItem() == null || ((Order)param).getListOfItem().isEmpty()){
            throw new Exception("Incomplete data!");
        } 
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        
        //first add the Order to db so we could take OrderID cause we need it for orderItems
        repositoryGeneric.add((Order)param);
        //set it to the local Order object
        newOrder = (Order)param;

        //get list of orderItems
        ArrayList<OrderItems> listOfItems = ((Order)param).getListOfItem();
        
        for (OrderItems item : listOfItems) {
            if (item.getProductID() != null) {
                System.out.println("there is a product in this orderItem from the list");
                //set orderID 
                item.setOrderID(newOrder);
                //save that item to dbb
                repositoryGeneric.add(item);
                //now set the reservation number for that product
                int quantity = item.getQuantity();
                repositoryGeneric.editSmt(item.getProductID(), quantity);
            } else {
                System.out.println("there is no product in orderItem");
                throw new Exception("Incomplete data! There is no product in orderItem");
            }

        }
       
    }

    @Override
    protected void commitTransaction() throws Exception {
        //first commit the items and then the whole order
        repositoryGeneric.commit();
        repositoryGeneric.commit();
        repositoryGeneric.commit();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        //if there is an error in orderItems, don't save the Order
        repositoryGeneric.rollback();
        repositoryGeneric.rollback();
        repositoryGeneric.commit();
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
        repositoryGeneric.disconnect();
        repositoryGeneric.disconnect();
    }
    
    
    
}
