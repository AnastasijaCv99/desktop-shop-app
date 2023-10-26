/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zcommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class Product implements Serializable, GenericEntity{
    private int productID;
    private String title;
    private String description;
    private Double price;
    private int stock;
    private int reservation; 

    public Product() {
    }

    public Product(int productID, String title, String description, Double price, int stock, int reservation) {
        this.productID = productID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.reservation = reservation;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return " int stock " + stock + "reservation" + reservation;
    }
    
    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getColumnNames() {
        return "ProductID, Title, Description, Price, Stock, Reservation";
    }
    
    @Override
    public String getColumnNamesForAdding() {
        return "Title, Description, Price, Stock, Reservation";
    }

    @Override
    public String getWhereCondition() {
        return "productID= " + productID;
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
            sb.append("'").append(title).append("',")
                .append("'").append(description).append("',")
                .append(price).append(",")
                .append(stock).append(",")
                .append(reservation);
        System.out.println(sb.toString());
        return sb.toString();       
    }


    @Override
    public void setId(int id) {
        productID = id;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> products = new ArrayList<>();
        while(rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt(1));
            product.setTitle(rs.getString(2));
            product.setDescription(rs.getString(3));
            product.setPrice(rs.getDouble(4));
            product.setStock(rs.getInt(5));
            product.setReservation(rs.getInt(6));
            
            products.add(product);
        }
        return products;
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Title=").append("'").append(title).append("', ")
            .append("Description=").append("'").append(description).append("', ")
            .append("Price=").append(price).append(", ")
                .append("Stock=").append(stock);
       
        return sb.toString();
    
    }

    @Override
    public String getUpdate2(int quantity) {
        StringBuilder sb = new StringBuilder();
        
        
        int oldReserv = reservation;
        int newReserv = oldReserv + quantity;
        
        sb.append("Reservation=").append(newReserv);
       
        return sb.toString();
    
    }
    
     @Override
    public String getUpdate3(int quantity) {
        StringBuilder sb = new StringBuilder();
       
        int oldReserv = reservation;
        int newReserv = oldReserv - quantity;
        
        int oldStock = stock;
        int newStock = oldStock - quantity;
        
        
        sb.append("Reservation=").append(newReserv).append(", ").append("Stock=").append(newStock);
       
        return sb.toString();
        
    }

    @Override
    public String getColumnNamesWithJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSelect(String arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSecondWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer getNumber(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList2(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getThirdWhereCondition() {
        return "productID= " + productID;
    }

    @Override
    public String getFourthWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    
    
}
