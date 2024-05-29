/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.repository.db;

import java.sql.SQLException;
import server.repository.Repository;
import java.sql.*;


/**
 
 * @author Anastasija Cvetkovic
 */
public interface DbRepository<T, K, S> extends Repository<T, K, S>{
    
    default public Connection connect() throws Exception{
        return DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws SQLException, Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws SQLException, Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws SQLException, Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }

}
