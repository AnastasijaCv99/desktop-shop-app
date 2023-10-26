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
public class Order implements Serializable, GenericEntity{
    private int orderID;
    private Double totalAmountPricee;
    private User userID;
    private Admin adminID;
    private ArrayList<OrderItems> listOfItem;

    public Order() {
    }

    public Order(int orderID, Double totalAmountPricee, User userID, Admin adminID, ArrayList<OrderItems> listOfItem) {
        this.orderID = orderID;
        this.totalAmountPricee = totalAmountPricee;
        this.userID = userID;
        this.adminID = adminID;
        this.listOfItem = listOfItem;
    }

    

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Double getTotalAmountPricee() {
        return totalAmountPricee;
    }

    public void setTotalAmountPricee(Double totalAmountPricee) {
        this.totalAmountPricee = totalAmountPricee;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public ArrayList<OrderItems> getListOfItem() {
        return listOfItem;
    }

    public void setListOfItem(ArrayList<OrderItems> listOfItem) {
        this.listOfItem = listOfItem;
    }

    @Override
    public String getTableName() {
        return "ordert";
    }

    @Override
    public String getColumnNames() {
        return "OrderID, TotalAmountPrice, IDAdmin, IDUser";
    }

    @Override
    public String getColumnNamesForAdding() {
        return "TotalAmountPrice, IDUser";
    }

    @Override
    public String getWhereCondition() {
        return "IDUser= ";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
            sb.append(totalAmountPricee).append(", ")
              .append(userID.getUserID());
    
        System.out.println(sb.toString());
        return sb.toString();      
    }

    @Override
    public String getUpdateValues() {
        return "";
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> orders = new ArrayList<>();
        
        while (rs.next()){
            User user = new User();
            user.setUserID(rs.getInt("UserID"));
            user.setName(rs.getString("Name"));
            user.setLastName(rs.getString("Lastname"));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setPhoneNumber(rs.getString("PhoneNumber"));
            user.setAddress(rs.getString("Address"));
            
            
            Order order = new Order(rs.getInt("OrderID"), rs.getDouble("TotalAmountPrice"), user, null, null);
            
            orders.add(order);
        }
        
        return orders;
    }
    
    @Override
    public List<GenericEntity> getList2(ResultSet rs) throws Exception {
        List<GenericEntity> orders = new ArrayList<>();
        
        while (rs.next()){
            Order order = new Order(rs.getInt("OrderID"), rs.getDouble("TotalAmountPrice"), null, null, null);
            
            orders.add(order);
        }
        
        return orders;
    }

    @Override
    public void setId(int id) {
        orderID = id;
    }

    @Override
    public String getUpdate2(int g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNamesWithJoin() {
       // return "ordert o JOIN USER u ON (o.IDUser = u.UserID)";
        
       return "ordert o JOIN USER u ON (o.IDUser = u.UserID)";
    }

    @Override
    public String getSelect(String arg) {
        return "COUNT(orderid) AS COUNT FROM ordert WHERE IDAdmin IS NULL";
    }

    @Override
    public String getSecondWhereCondition() {
        return "IDAdmin IS NULL AND IDUser= ";
        //return "IDAdmin IS NULL AND IDUser= ";
        //sad mmi treba kombinacija prvog i drugog
        
        //return "IDAdmin IS NULL;";
    }
    
    @Override
    public String getThirdWhereCondition() {
        return "OrderID=" + orderID; 
    }
    
    @Override
    public String getFourthWhereCondition() {
        return "IDAdmin IS NULL;";
    }

    @Override
    public Integer getNumber(ResultSet rs) throws Exception {
        int result = 0;
        while (rs.next()){
            result = rs.getInt("count");
        }
        
        return result;
    }

    @Override
    public String getUpdate3(int g) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("IDAdmin=").append(g);
       
        return sb.toString();
    }

    

   

    

    

    

    
    
    
}
