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
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class RepositoryProduct implements DbRepository<Product, Integer, String>{

    Connection connection;
    
    /*@Override
    public List<Product> getAll() throws Exception {
         List<Product> allProduct = new ArrayList<>();
        
        String query = "SELECT * FROM Product";
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
            
            allProduct.add(product);
        }
        rs.close();
        st.close();
        return allProduct;
    }*/

    @Override
    public void add(Product p) throws Exception {
        String query = "INSERT INTO Product(Title, Description, Price, Stock, Reservation) VALUES (?,?,?,?,?)";
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, p.getTitle());
        ps.setString(2, p.getDescription());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getStock());
        ps.setInt(5, p.getReservation());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void edit(Product p) throws Exception {
        String query = "UPDATE Product SET Title = ?, Description = ?, Price = ?, Stock = ? WHERE ProductID=" + p.getProductID();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, p.getTitle());
        ps.setString(2, p.getDescription());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getStock());
        
        ps.executeUpdate();
        ps.close();
    }

   
    @Override
    public void editSmt(Product p, Integer quantity) throws Exception {
        String query = "UPDATE Product SET Reservation = ? WHERE ProductID=" + p.getProductID();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        
        //treba da se uzme prvo broj reservation i da se uradi newReservation = oldReservation + orderItemAmount
        
        Product oldProduct = new Product();// = getById(p.getProductID());
        int oldReserv = oldProduct.getReservation();
        int newReserv = oldReserv + quantity;
        ps.setInt(1, newReserv);
        
        ps.executeUpdate();
        ps.close();
    }
    
    @Override
    public void delete(Product p) throws Exception {
        try {
            String query = "DELETE FROM product WHERE productID="+p.getProductID();
            connection = DbConnectionFactory.getInstance().getConnection();
            Statement st = connection.createStatement();
        
            int rs = st.executeUpdate(query);
            
            st.close();
            
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Cannot delete this");
        }
        
        
    }

  /*  @Override
    public Product getById(Integer typeK) throws Exception {       
        String query = "SELECT * FROM product WHERE productID =" +typeK ;
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        Product product = new Product();
        while(rs.next()) {
            product = new Product(typeK, rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6));
        }
        rs.close();
        st.close();
        
        return product;
    }
*/
    

    @Override
    public void editSmtElse(Product p, Integer quantity) throws Exception {
        String query = "UPDATE Product SET Reservation = ?, Stock = ? WHERE ProductID=" + p.getProductID();
        
        System.out.println("p koje je doslo kao parametar " + p.toString());
        connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        
        //treba da se uzme prvo broj reservation i da se uradi newReservation = oldReservation + orderItemAmount
        Product oldProduct = new Product();//getById(p.getProductID());
        System.out.println("p koje je doslo kao oldProduct " + oldProduct.toString());
        int oldReserv = oldProduct.getReservation();
        int newReserv = oldReserv - quantity;
        
        int oldStock = oldProduct.getStock();
        int newStock = oldStock - quantity;
        
        ps.setInt(1, newReserv);
        ps.setInt(2, newStock);
        
        ps.executeUpdate();
        ps.close();
    }


    @Override
    public int getCountOf(Product p, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public List<Product> getAll(Product table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> getAllById(Product table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public List<Product> getAllById(Product table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> getAllByIdMoreSQL(Product table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
