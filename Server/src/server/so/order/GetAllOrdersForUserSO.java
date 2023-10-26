/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryOrder;
import server.so.AbstractSO;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class GetAllOrdersForUserSO extends AbstractSO{

    //private final DbRepository repositoryOrder;
    private final DbRepository repositoryGeneric;

    public GetAllOrdersForUserSO() {
        //this.repositoryOrder = new RepositoryOrder();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    User u;

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null) {
            throw new Exception("Invalid data!");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        ArrayList<Order> list = ((ArrayList<Order>)param);
        Order o = new Order(0, 0.0, null, null, null);
        for (Order order : list) {
            if (order.getOrderID() == o.getOrderID()) {
                u = order.getUserID();
            }
        }
        
        ArrayList<Order> ordersList = (ArrayList<Order>) repositoryGeneric.getAllById(o,u.getUserID());
        
        for (Order o1 : ordersList) {
            OrderItems oi = new OrderItems();
            ArrayList<OrderItems> ordersItemsList = (ArrayList<OrderItems>) repositoryGeneric.getAllById(oi, o1.getOrderID());
            for (OrderItems orderItems : ordersItemsList) {
                orderItems.setOrderID(o1);
            }
            o1.setListOfItem(ordersItemsList);
            ((ArrayList<Order>)param).add(o1);            
        }
    }
    

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
}
