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
public class TableModelProductsForUser extends AbstractTableModel{
    ArrayList<Product> listOfProducts;
    String[] columns = {"Title", "Description","Price"};
    
  
    public TableModelProductsForUser() {
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
            case 2: return p.getPrice();
            default: return "ret";
        }
                
                
    }

    public void fill(ArrayList<Product> products) {
        //listOfProducts = products;
        for (Product product : products) {
            if (product.getStock() > 0 && (product.getReservation() < product.getStock())){
                listOfProducts.add(product);
            }
        }
        fireTableDataChanged();
    }

    public Product getProduct(int row) {
        return listOfProducts.get(row);
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void updateTable(ArrayList<Product> wholeList) {
        listOfProducts = wholeList;
        fireTableDataChanged();
    }
    
    
    
}

