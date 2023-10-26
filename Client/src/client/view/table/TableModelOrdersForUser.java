/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class TableModelOrdersForUser extends AbstractTableModel{

    ArrayList<Order> orders;
    String[] columns = {"OrderID", "Full price", "Products and quantity"};
    
    public TableModelOrdersForUser () {
        orders = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return orders.size();
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
        Order o = orders.get(rowIndex);
        switch (columnIndex) {
            case 0: return o.getOrderID();
            case 1: return o.getTotalAmountPricee();
            case 2: return getProducts(o);
            default: return "n/o";
        }
    }

    private Object getProducts(Order o) {
        String allView = "";
        
        ArrayList<OrderItems> items = o.getListOfItem();
        for (OrderItems oi : items) {
            String view = oi.getProductID().getTitle() + "(" + oi.getQuantity() + "), ";
            allView = allView + view;
        } 
        
        return allView;
        
    }

    public void addOrders(ArrayList<Order> ordersForUser) {
        orders = ordersForUser;
        fireTableDataChanged();
    }
    
    public Order getOrder(int row) {
        return orders.get(row);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
}
