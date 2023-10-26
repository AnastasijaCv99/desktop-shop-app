/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.repository.db.impl;

import java.util.List;
import server.repository.db.DbRepository;
import zcommon.domain.Invoice;
import java.sql.*;
import java.util.ArrayList;
import server.repository.db.DbConnectionFactory;
import zcommon.domain.Order;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class RepositoryInvoice implements DbRepository<Invoice, Integer, String>{
    Connection connection;
    
    
    

    @Override
    public void add(Invoice i) throws Exception {
        
        String query = "INSERT INTO Invoice(TotalAmountPrice, SentToUser, IDOrder, IDAdmin) VALUES (?,?,?,?)";
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDouble(1, i.getAmount());
        ps.setBoolean(2, true);
        ps.setInt(3, i.getOrderID().getOrderID());
        ps.setInt(4,    i.getAdminID().getAdminID());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void edit(Invoice table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Invoice table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void editSmt(Invoice table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*@Override
    public List<Invoice> getAllById(Integer typek) throws Exception {
        //String query = "SELECT * FROM invoice i JOIN ordert o ON (i.IDOrder = o.OrderID) JOIN orderitem oi "
          //      + "ON (o.OrderID = oi.IDOrder) JOIN product p ON (oi.IDProduct = p.ProductID) WHERE o.IDUser =" + typek;
        String query = "SELECT * FROM invoice i JOIN ordert o ON (i.IDOrder = o.OrderID) WHERE o.IDUser =" + typek;
        
        List<Invoice> listOfInvoices = new ArrayList<>();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()) {
            Order o = new Order(rs.getInt(6), rs.getDouble(7), null, null, null);
            Invoice i = new Invoice(rs.getInt(1), rs.getDouble(2), true, o, null);
            
            listOfInvoices.add(i);
        }
        
        rs.close();
        st.close();
        return listOfInvoices;        
    }*/

    @Override
    public void editSmtElse(Invoice table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public int getCountOf(Invoice i, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public List<Invoice> getAll(Invoice table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Invoice> getAllById(Invoice table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public List<Invoice> getAllById(Invoice table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Invoice> getAllByIdMoreSQL(Invoice table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
