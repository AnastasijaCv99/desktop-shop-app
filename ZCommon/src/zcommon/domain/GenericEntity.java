/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zcommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public interface GenericEntity extends Serializable{
    
    String getTableName(); //should return table name in DB

    String getColumnNames(); //should return column names in that table in DB
    String getColumnNamesForAdding(); //should return names of column which are being added to, supposably the whole table
    String getColumnNamesWithJoin();
    String getSelect(String arg);
    
    String getWhereCondition(); //if there is a where condition in SQL queries
    String getSecondWhereCondition();
    String getThirdWhereCondition();
    String getFourthWhereCondition();
    
    String getInsertValues(); //returns values for inserting into DB
    String getUpdateValues(); //return part of the SQL query for editing an table in DB
    String getUpdate2(int g); //columns for editing 
    String getUpdate3(int g); //columns for editing
    
    List<GenericEntity> getList(ResultSet rs) throws Exception; //return part of the SQL query SELECT
    List<GenericEntity> getList2(ResultSet rs) throws Exception; //return part of the SQL query SELECT
    Integer getNumber(ResultSet rs) throws Exception;
    
    void setId(int id); 
    
    
}
