/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryOrder;
import server.repository.db.impl.RepositoryProduct;
import server.so.AbstractSO;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class GetAllOrdersForAdminSO extends AbstractSO{

    //private final DbRepository repositoryOrder;
    private final DbRepository repositoryGeneric;

    
    public GetAllOrdersForAdminSO() {
        //this.repositoryOrder = new RepositoryOrder();
        this.repositoryGeneric = new RepositoryGeneric();
    }

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null) {
            throw  new Exception("Invalid data!");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        
        Order order = new Order();
        ArrayList<Order> ordersList = (ArrayList<Order>) repositoryGeneric.getAllById(order);
        
        for (Order o : ordersList) {
            OrderItems oi = new OrderItems();
            ArrayList<OrderItems> ordersItemsList = (ArrayList<OrderItems>) repositoryGeneric.getAllById(oi, o.getOrderID());
            for (OrderItems orderItems : ordersItemsList) {
                orderItems.setOrderID(o);
            }
            o.setListOfItem(ordersItemsList);
            ((ArrayList<Order>)param).add(o);
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
}
