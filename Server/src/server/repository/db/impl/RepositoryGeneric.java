/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;
import server.repository.db.DbConnectionFactory;
import server.repository.db.DbRepository;
import zcommon.domain.GenericEntity;

/**
 *
 * @author Korisnik
 */
public class RepositoryGeneric implements DbRepository<GenericEntity, Integer, String>{
    
    
    @Override
    public List<GenericEntity> getAll(GenericEntity entity) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        String query = "SELECT * FROM " + entity.getTableName();
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return entity.getList(rs);
    }
    

    @Override
    public List<GenericEntity> getAllById(GenericEntity entity) throws Exception {
        //ne po IDu ali mi treba ovaj typel
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        //SELECT * FROM ordert o JOIN USER u ON (o.IDUser = u.UserID) WHERE IDAdmin IS NULL;";
        //String query = "SELECT * FROM " + entity.getColumnNamesWithJoin() + " WHERE " +entity.getWhereCondition();
        //SELECT * FROM invoice i JOIN ordert o ON (i.IDOrder = o.OrderID) WHERE i.IDAdmin = 1
        
        String query = "SELECT * FROM " + entity.getColumnNamesWithJoin() + " WHERE " + entity.getFourthWhereCondition();
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return entity.getList(rs);
    }

    @Override
    public List<GenericEntity> getAllById(GenericEntity entity, Integer typeK) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        //SELECT * FROM OrderItem oi JOIN product p ON (oi.IDProduct = p.ProductID) WHERE IDOrder = " + order.getOrderID();
        //SELECT * FROM ordert WHERE IDAdmin IS NULL AND IDUser=" + typek;
        //SELECT * FROM invoice i JOIN ordert o ON (i.IDOrder = o.OrderID) WHERE o.IDUser =" + typek;
        
        String query = "SELECT * FROM " + entity.getColumnNamesWithJoin() + " WHERE " + entity.getSecondWhereCondition() + typeK;         
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return entity.getList(rs);
    }
    
    @Override
    public List<GenericEntity> getAllByIdMoreSQL(GenericEntity entity, Integer typeK) throws Exception {
        
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        //SELECT * FROM ordert WHERE IDUser=" + typek;
                
        String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition()+ typeK;
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return entity.getList2(rs);
    }

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForAdding()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            
            System.out.println(query);
            Statement statement = connection.createStatement();

            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                int id = rsKey.getInt(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void edit(GenericEntity p) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        String query = "UPDATE " + p.getTableName() + " SET " + p.getUpdateValues() + " WHERE " + p.getWhereCondition();
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void editSmt(GenericEntity p, Integer typeK) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        String query = "UPDATE " + p.getTableName() + " SET " + p.getUpdate2(typeK) + " WHERE " + p.getWhereCondition();
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void editSmtElse(GenericEntity p, Integer typeK) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        String query = "UPDATE " + p.getTableName() + " SET " + p.getUpdate3(typeK) + " WHERE " + p.getThirdWhereCondition();
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void delete(GenericEntity entity) throws Exception {
        try {
            String query = "DELETE FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();
            System.out.println(query);
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement st = connection.createStatement();
        
            st.executeUpdate(query);
            
            st.close();
            
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Cannot delete this");
        }
    }

    @Override
    public int getCountOf(GenericEntity entity, String arg) throws Exception {
        
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        
        //SELECT COUNT(orderid) AS COUNT FROM ordert WHERE IDAdmin IS NULL;
        //getSelect: COUNT(orderid) AS COUNT
        
        String query = "SELECT " + entity.getSelect(arg);
        System.out.println(query);
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return entity.getNumber(rs);
        
        //opcije su da se ovde stavi u argument GenericEntity i da se salje Order a kao uslov da se napravi novi
        //ono getColumnNamesWithJoin(); koji ce da sadrzi ovaj count(orderid) as count from ordert where IDAdm
        //i onda ono  return entity.getList(rs); samo umesto getList da bude getNumber(rs)
        //a to getN u order da bude result = rs.getInt("count");
    }

    

    
}
