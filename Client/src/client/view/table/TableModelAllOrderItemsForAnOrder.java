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
public class TableModelAllOrderItemsForAnOrder extends AbstractTableModel{

    static ArrayList<OrderItems> listOfOrders;
    String[] columns = {"Order ID", "User", "Product title", "Quantity", "Full price", "Product stock number"};
    
    public TableModelAllOrderItemsForAnOrder() {
    }
    
    public static ArrayList<OrderItems> getListOfOrders() {
        return listOfOrders;
    }
    
    
    @Override
    public int getRowCount() {
        return listOfOrders.size();
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
        OrderItems oi = listOfOrders.get(rowIndex);
        switch (columnIndex) {
            case 0: return oi.getOrderID().getOrderID();
            case 1: return oi.getOrderID().getUserID().toString();
            case 2: return oi.getProductID().getTitle();
            case 3: return oi.getQuantity();
            case 4: return oi.getProductID().getPrice()*oi.getQuantity();
            case 5: return oi.getProductID().getStock();
            default: return "ret";
        }
    }
    
    public static void addList(ArrayList<OrderItems> listOfORderItemsForAdminByID) {
        listOfOrders = listOfORderItemsForAdminByID;
    }
    
}
