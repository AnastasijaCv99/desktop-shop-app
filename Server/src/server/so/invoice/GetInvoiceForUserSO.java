/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.invoice;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryInvoice;
import server.repository.db.impl.RepositoryOrder;
import server.so.AbstractSO;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class GetInvoiceForUserSO extends AbstractSO{

    //private final DbRepository repositoryInvoice;
    //private final DbRepository repositoryOrder;
    private final DbRepository repositoryGeneric;


    public GetInvoiceForUserSO() {
        //this.repositoryInvoice = new RepositoryInvoice();
        //this.repositoryOrder = new RepositoryOrder();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    Invoice iiii;

    @Override
    protected void executeTransaction(Object param) throws Exception {
        
        Invoice i = new Invoice(0, 0.0, true, null, null);
        ArrayList<Invoice> invoices = (ArrayList<Invoice>)param;
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceID() == i.getInvoiceID()) {
                iiii = invoice;
            }
        }
        Order oo = new Order();                
        ArrayList<Order> ordersOfUser = (ArrayList<Order>) repositoryGeneric.getAllByIdMoreSQL(oo, iiii.getOrderID().getUserID().getUserID());
        
        for (Order order : ordersOfUser) {
            OrderItems oi = new OrderItems();
            ArrayList<OrderItems> ordersItemsList = (ArrayList<OrderItems>) repositoryGeneric.getAllById(oi, order.getOrderID());
            for (OrderItems orderItems : ordersItemsList) {
                orderItems.setOrderID(order);
            }
            order.setListOfItem(ordersItemsList);
            order.setUserID(iiii.getOrderID().getUserID());
        }
        
        
        ArrayList<Invoice> invoicesFromDB = (ArrayList<Invoice>) repositoryGeneric.getAllById(i,iiii.getOrderID().getUserID().getUserID());
        for (Invoice invoice1 : invoicesFromDB) {
            invoice1.getOrderID().setUserID(iiii.getOrderID().getUserID());
            for (Order order1 : ordersOfUser) {
                if (invoice1.getOrderID().getOrderID() == order1.getOrderID()) {
                    invoice1.setOrderID(order1);
                }
                else continue;
            }
            ((ArrayList<Invoice>)param).add(invoice1);
        }
        
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
        repositoryGeneric.disconnect();
        
    }
    
    
    
    
}
