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
import zcommon.domain.OrderItems;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class RepositoryOrderItem implements DbRepository<OrderItems, Integer, String>{

    Connection connection;
    
    

    /*@Override
    public List<OrderItems> getAllById(Integer typek) throws Exception {
        String query = "SELECT * FROM PRODUCT p JOIN orderitem oi ON (oi.IDProduct = p.ProductID) WHERE IDOrder =" + typek;
        List<OrderItems> listOfOrderItems = new ArrayList<>();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt(1));
            product.setTitle(rs.getString(2));
            product.setDescription(rs.getString(3));
            product.setPrice(rs.getDouble(4));
            product.setStock(rs.getInt(5));
            product.setReservation(rs.getInt(6));
            OrderItems oi = new OrderItems(rs.getInt(7), null, rs.getInt(10), product);
        listOfOrderItems.add(oi);
        }
       
        rs.close();
        st.close();
        return listOfOrderItems;
    }*/
    
    @Override
    public void add(OrderItems oi) throws Exception {
        String query = "INSERT INTO Orderitem(IDOrder, IDProduct, Quantity) VALUES (?,?,?)";
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, oi.getOrderID().getOrderID());
        ps.setInt(2, oi.getProductID().getProductID());
        ps.setInt(3, oi.getQuantity());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void edit(OrderItems table) throws Exception {
        
    }

    @Override
    public void delete(OrderItems table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void editSmt(OrderItems table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editSmtElse(OrderItems table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public int getCountOf(OrderItems oi, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderItems> getAll(OrderItems table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderItems> getAllById(OrderItems table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public List<OrderItems> getAllById(OrderItems table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderItems> getAllByIdMoreSQL(OrderItems table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
