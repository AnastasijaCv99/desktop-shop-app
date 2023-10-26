/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import zcommon.domain.Order;

/**
 *
 * @author Korisnik
 */
public class TableModelOrder extends AbstractTableModel {

    ArrayList<Order> orders;
    String[] columns = {"OrderID", "Total price", "User"};
    
    public TableModelOrder () {
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
            case 2: return o.getUserID().toString();
            default: return "ret";
        }
    }

    public void addList(ArrayList<Order> ordersForAdmin) {
        orders = ordersForAdmin;
        fireTableDataChanged();
    }

    public Order getOrder(int row) {
        return orders.get(row);
    }
    
    public ArrayList<Order> getListOrder() {
        return orders;
    }
    
}
