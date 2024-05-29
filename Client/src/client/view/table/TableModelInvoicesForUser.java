/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class TableModelInvoicesForUser extends AbstractTableModel{

    ArrayList<Invoice> invoices;
    String[] columns = {"NO.", "Full price", "Products and quantity"};
    //, "Number of items"
    
    public TableModelInvoicesForUser() {
        invoices = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice i = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getInvoiceID();
            case 1: return i.getAmount();
            case 2: return getProducts(i);
            default: return "n/o";
        }
    }

    public void addListOfInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
        fireTableDataChanged();
    }
    
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        fireTableDataChanged();
    }
    
    private String getProducts(Invoice i) {
        String allView = "";
        
        ArrayList<OrderItems> items = i.getOrderID().getListOfItem();
        for (OrderItems oi : items) {
            String view = oi.getProductID().getTitle() + "(" + oi.getQuantity() + "), ";
            allView = allView + view;
        } 
        
        return allView;   
    }
    
    public Invoice getInvoice(int row) {
        return invoices.get(row);
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    
    
    
    
}
