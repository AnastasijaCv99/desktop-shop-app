/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryOrder;
import server.repository.db.impl.RepositoryOrderItem;
import server.so.AbstractSO;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class GetOrderItemsByOrderIDSO extends AbstractSO{

    //private final DbRepository repositoryOrderItems;
    private final DbRepository repositoryGeneric;

    
    public GetOrderItemsByOrderIDSO() {
        //this.repositoryOrderItems = new RepositoryOrderItem();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    OrderItems oiLocal = new OrderItems();

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Invalid data!");
        }
    }
    
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //param je lista orderitems 
        //u njoj ima jedan item koji ima setovan ORderID
        //treba to da izvucemo
        ArrayList<OrderItems> orderItems = (ArrayList<OrderItems>)param;
        
        OrderItems oiForComparing = new OrderItems(0, null, 0, null);
        for (OrderItems orderItem : orderItems) {
            if (orderItem.getOrderItemsID() == oiForComparing.getOrderItemsID()) {
                oiLocal = orderItem;
                
            }
        }
        //ArrayList<OrderItems> listOfOrderItemsFromDB = new ArrayList<>();
        //OrderItems orderItemFromDB = (OrderItems) repositoryOrderItems.getById(oiii.getOrderID().getOrderID());
        //for (OrderItems orderItems1 : listOfOrderItemsFromDB) {
        //    ((ArrayList<OrderItems>)param).add(orderItemFromDB);
        //}
        
        ArrayList<OrderItems> listOfOrderItemsFromDB = (ArrayList<OrderItems>) repositoryGeneric.getAllById(oiLocal, oiLocal.getOrderID().getOrderID());
        for (OrderItems orderItems1 : listOfOrderItemsFromDB) {
            orderItems1.setOrderID(oiLocal.getOrderID());
            ((ArrayList<OrderItems>)param).add(orderItems1);
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
}
