/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class TableModelOrderItems extends AbstractTableModel{
    static ArrayList<OrderItems> listOfItems = new ArrayList<>();;
    static String[] columns = {"No.", "Product title", "Quantity", "Price"};

    static int no = 0;
    
    public TableModelOrderItems() {
    }

    public static ArrayList<OrderItems> getListOfItems() {
        return listOfItems;
    }
    
    @Override
    public int getRowCount() {
        return listOfItems.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex==2);
        //if (columnIndex==1) return true;
        //return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        //Product product = products.get(rowIndex);
        OrderItems oi = listOfItems.get(rowIndex);
        switch(columnIndex){
            case 2:
                //product.setName(String.valueOf(value));
                oi.setQuantity(Integer.parseInt((String) value));
                break;
            case 3: 
                oi.getProductID().setPrice(oi.getProductID().getPrice()*oi.getQuantity());
                break;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderItems oi = listOfItems.get(rowIndex);
        switch (columnIndex) {
            case 0: return oi.getOrderItemsID();
            case 1: return oi.getProductID().getTitle();
            case 2: return oi.getQuantity();
            //case 3: return oi.getProductID().getPrice();
            case 3: return oi.getProductID().getPrice()*oi.getQuantity();
            default: return "ret";
        }
    }

    public static void addProduct(Product chosenProduct) {
        //no = listOfItems.size();
        OrderItems newItem = new OrderItems(++no, null, 1, chosenProduct);
        listOfItems.add(newItem);
    }
    
    public void addOrderItems(ArrayList<OrderItems> oi) {
        listOfItems = oi;
        fireTableDataChanged();
    }

    public void removeProduct(int row) {
        listOfItems.remove(row);
        no = 0;
        for (OrderItems listOfItem : listOfItems) {
            listOfItem.setOrderItemsID(++no);
        }
        fireTableDataChanged();
    }
    
    public static void emptyList() {
        listOfItems.clear();
    }
    
    
}
