/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.invoice;

import java.util.ArrayList;
import java.util.List;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.so.AbstractSO;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class GetInvoicesForAdminSO extends AbstractSO{

    private final DbRepository repositoryGeneric;

    public GetInvoicesForAdminSO() {
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
       
        Invoice i = new Invoice();
        User u = new User();
        
        ArrayList<Invoice> invoicesFromDB = (ArrayList<Invoice>) repositoryGeneric.getAllById(i);
        
        for (Invoice invoice1 : invoicesFromDB) {
            int userID = invoice1.getOrderID().getUserID().getUserID();
            //sad treba da uzmem tog usera iz baze
            List<User> users = repositoryGeneric.getAllById(u, userID);
            u = users.get(0);
            invoice1.getOrderID().setUserID(u);
            //sad uzimam listu order itema
            Order order = invoice1.getOrderID();
            
            OrderItems oi = new OrderItems();
            ArrayList<OrderItems> ordersItemsList = (ArrayList<OrderItems>) repositoryGeneric.getAllById(oi, order.getOrderID());
            for (OrderItems orderItems : ordersItemsList) {
                orderItems.setOrderID(order);
            }
            order.setListOfItem(ordersItemsList);
            order.setUserID(u);
            
            ((ArrayList<Invoice>)param).add(invoice1);
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
}
