/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.repository.db.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import server.repository.db.DbConnectionFactory;
import server.repository.db.DbRepository;
import zcommon.domain.Admin;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class RepositoryOrder implements DbRepository<Order, Integer, String>{

    Connection connection;
    
   /* @Override
    public List<Order> getAll() throws Exception {
        String query = "SELECT * FROM ordert o JOIN USER u ON (o.IDUser = u.UserID) WHERE IDAdmin IS NULL;";
        List<Order> orders = new ArrayList<>();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
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
            ArrayList<OrderItems> listItems = (ArrayList<OrderItems>) getItemsForId(order);
            order.setListOfItem(listItems);
            
            orders.add(order);
        }
        
        rs.close();
        st.close();
        return orders;
    }*/
    
    private List<OrderItems> getItemsForId(Order order) throws Exception {
        List<OrderItems> orderItems = new ArrayList<>();
        String query = "SELECT * FROM OrderItem oi JOIN product p ON (oi.IDProduct = p.ProductID) WHERE IDOrder =" + order.getOrderID();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()) {
            Product p = new Product(rs.getInt(5), rs.getString(6),
                    rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10));
            OrderItems oi = new OrderItems(rs.getInt(1), order, rs.getInt(4), p);
            orderItems.add(oi);
        }
        
        rs.close();
        st.close();
        return orderItems;
    }

    @Override
    public void add(Order o) throws Exception {
        String query = "INSERT INTO Ordert(TotalAmountPrice, IDUser) VALUES (?,?)";
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, o.getTotalAmountPricee());
        ps.setInt(2, o.getUserID().getUserID());
        
        
        ps.executeUpdate();
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int orderID = tableKeys.getInt(1);
        o.setOrderID(orderID);
        
        ps.close();
    }

    @Override
    public void edit(Order table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Order table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public void editSmt(Order table, Integer typeK) throws Exception {
        String query = "UPDATE Ordert SET IDAdmin = ? WHERE OrderID="+table.getOrderID();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, typeK);
        
        ps.executeUpdate();
        ps.close();
    }

   /* @Override
    public List<Order> getAllById(Integer typek) throws Exception {
        String query = "SELECT * FROM ordert WHERE IDUser=" + typek;
        List<Order> orders = new ArrayList<>();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
             
            Order order = new Order(rs.getInt("OrderID"), rs.getDouble("TotalAmountPrice"), null, null, null);
            ArrayList<OrderItems> listItems = (ArrayList<OrderItems>) getItemsForId(order);
            order.setListOfItem(listItems);
            
            orders.add(order);
        }
        
        rs.close();
        st.close();
        return orders;
    }
*/
    @Override
    public void editSmtElse(Order table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Order> getAllByIdMoreSQL(Integer typek) throws Exception{
        String query = "SELECT * FROM ordert WHERE IDAdmin IS NULL AND IDUser=" + typek;
        List<Order> orders = new ArrayList<>();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
             
            Order order = new Order(rs.getInt("OrderID"), rs.getDouble("TotalAmountPrice"), null, null, null);
            ArrayList<OrderItems> listItems = (ArrayList<OrderItems>) getItemsForId(order);
            order.setListOfItem(listItems);
            
            orders.add(order);
        }
        
        rs.close();
        st.close();
        return orders;
    }

    @Override
    public int getCountOf(Order table, String s) throws Exception {
        String query = "SELECT COUNT(orderid) AS COUNT FROM ordert WHERE IDAdmin IS NULL;";

        int result = 0;
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            result = rs.getInt("count");
        }
        
        rs.close();
        st.close();
        
        System.out.println("res from repo: " + result);
        return result;
    }

   

    @Override
    public List<Order> getAll(Order table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> getAllById(Order table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    @Override
    public List<Order> getAllById(Order table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> getAllByIdMoreSQL(Order table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
