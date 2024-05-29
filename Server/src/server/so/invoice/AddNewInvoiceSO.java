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
import server.repository.db.impl.RepositoryProduct;
import server.so.AbstractSO;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class AddNewInvoiceSO extends AbstractSO{

    //private final DbRepository dbRepositoryInvoice;
    //private final DbRepository dbRepositoryProduct;
    //private final DbRepository repositoryOrder;
    private final DbRepository repositoryGeneric;


    public AddNewInvoiceSO() {
        //this.dbRepositoryInvoice = new RepositoryInvoice();
        //this.dbRepositoryProduct = new RepositoryProduct();
        //this.repositoryOrder = new RepositoryOrder();
        this.repositoryGeneric = new RepositoryGeneric();
    }

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof Invoice)) {
            throw new Exception("Invalid data!");
        } else if (((Invoice)param).getOrderID() == null){
            throw new Exception("Incomplete data!");
        } else if (((Invoice)param).getOrderID().getListOfItem() == null) {
            throw new Exception("Incomplete data! (List of items is empty!)");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //save invoice
        repositoryGeneric.add((Invoice)param);
        //change product stock number and reservation
        ArrayList<OrderItems> items = ((Invoice)param).getOrderID().getListOfItem();
        for (OrderItems item : items) {
            int quantity = item.getQuantity();
            Product p = item.getProductID();
            repositoryGeneric.editSmtElse(p, quantity);
        }
        //change in table order column AdminID
        Order o = ((Invoice)param).getOrderID();
        int adminID = ((Invoice)param).getAdminID().getAdminID();
        repositoryGeneric.editSmtElse(o, adminID);
        
    }
    
    @Override
    protected void commitTransaction() throws Exception {
        repositoryGeneric.commit();
        //repositoryGeneric.commit();
        //repositoryGeneric.commit();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        repositoryGeneric.rollback();
        //repositoryGeneric.rollback();
        //repositoryGeneric.rollback();
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
        //repositoryGeneric.disconnect();
        //repositoryGeneric.disconnect();
    }
    
    
    
}
