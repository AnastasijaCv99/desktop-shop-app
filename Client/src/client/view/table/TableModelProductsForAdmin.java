/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import zcommon.domain.Product;


/**
 *
 * @author Korisnik
 */
public class TableModelProductsForAdmin extends AbstractTableModel{
    ArrayList<Product> listOfProducts;
    String[] columns = {"Title", "Description", "Stock", "Reservation", "Price"};
    
  
    public TableModelProductsForAdmin() {
        listOfProducts = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listOfProducts.size();
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
        Product p = listOfProducts.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getTitle();
            case 1: return p.getDescription();
            case 2: return p.getStock();
            case 3: return p.getReservation();
            case 4: return p.getPrice();
            default: return "ret";
        }
                
                
    }

    public void fill(ArrayList<Product> products) {
        listOfProducts = products;
        fireTableDataChanged();
    }

    public Product getAProduct(int row) {
        return listOfProducts.get(row);        
    }

    public void deleteAProduct(Product p) {
        listOfProducts.remove(p);
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void updateTable(ArrayList<Product> wholeList) {
        listOfProducts = wholeList;
        fireTableDataChanged();
    }

    
    
    
    
}

