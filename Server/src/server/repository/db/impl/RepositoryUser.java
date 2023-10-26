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
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class RepositoryUser implements DbRepository<User, Integer, String>{

    Connection connection;
    
   
    @Override
    public void add(User u) throws Exception {
        String query = "INSERT INTO User (Name, Lastname, Username, Password, PhoneNumber, Address) VALUES (?,?,?,?,?,?)";
        
        connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, u.getName());
        ps.setString(2, u.getLastName());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getPassword());
        ps.setString(5, u.getPhoneNumber());
        ps.setString(6, u.getAddress());
        
        ps.executeUpdate();
        ps.close();
        
        //connection.close();
    }

    @Override
    public void edit(User u) throws Exception {
        //update
        String query = "UPDATE User SET Name = ?, Lastname = ?, Username = ?, Password = ?, PhoneNumber = ?, Address = ? WHERE UserID=" + u.getUserID();
        
        connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setString(1, u.getName());
        ps.setString(2, u.getLastName());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getPassword());
        ps.setString(5, u.getPhoneNumber());
        ps.setString(6, u.getAddress());
        
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(User u) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public void editSmt(User table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public void editSmtElse(User table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int getCountOf(User u, String s) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public List<User> getAll(User table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getAllById(User table) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public List<User> getAllById(User table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getAllByIdMoreSQL(User table, Integer typeK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
