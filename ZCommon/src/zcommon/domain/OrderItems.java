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
public class OrderItems implements Serializable, GenericEntity{
    private int orderItemsID;
    private Order orderID;
    private int quantity;
    private Product productID;

    public OrderItems() {
    }

    public OrderItems(int orderItemsID, Order orderID, int amount, Product productID) {
        this.orderItemsID = orderItemsID;
        this.orderID = orderID;
        this.quantity = amount;
        this.productID = productID;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public int getOrderItemsID() {
        return orderItemsID;
    }

    public void setOrderItemsID(int orderItemsID) {
        this.orderItemsID = orderItemsID;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getTableName() {
        return "orderitem";
    }

    @Override
    public String getColumnNames() {
        return "OrderItemID, IDOrder, IDProduct, Quantity";
    }

    @Override
    public String getColumnNamesForAdding() {
        return "IDOrder, IDProduct, Quantity";
    }

    @Override
    public String getWhereCondition() {
        return "";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
            sb.append(orderID.getOrderID()).append(", ")
              .append(productID.getProductID()).append(", ")
              .append(quantity);
            
        System.out.println(sb.toString());
        return sb.toString();  
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
         List<GenericEntity> orderItems = new ArrayList<>();
        
        while(rs.next()) {
            Product p = new Product(rs.getInt(5), rs.getString(6),
                    rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10));
            OrderItems oi = new OrderItems(rs.getInt(1), null, rs.getInt(4), p);
            orderItems.add(oi);
        }
        return orderItems;
    }

    @Override
    public void setId(int id) {
        orderItemsID = id;
    }


    @Override
    public String getUpdate2(int g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNamesWithJoin() {
        return "OrderItem oi JOIN product p ON (oi.IDProduct = p.ProductID)";
    }

    @Override
    public String getSelect(String arg) {
        return "";
    }

    @Override
    public String getSecondWhereCondition() {
        return "IDOrder = ";
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
    public String getUpdate3(int g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getThirdWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getFourthWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
