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
public class User implements Serializable, GenericEntity{
    private int userID;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;

    public User() {
    }

    public User(int userID, String name, String lastName, String username, String password, String phoneNumber, String address) {
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    

    public boolean equals(User u) {
        if (this.getName().equals(u.getName()) &&
            this.getLastName().equals(u.getLastName()) &&
            this.getUsername().equals(u.getUsername()) &&
            this.getPassword().equals(u.getPassword()) &&
            this.getPhoneNumber() == u.getPhoneNumber() &&
            this.getAddress().equals(u.getAddress())
                ) {
            return true;
        } else return false;
    }
/*
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.JMBG, other.JMBG)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    */

    @Override
    public String toString() {
        return name + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnNames() {
         return "UserID, Name, Lastname, Username, Password, PhoneNumber, Address";
    }

    @Override
    public String getColumnNamesForAdding() {
        return "Name, Lastname, Username, Password, PhoneNumber, Address";
    }

    @Override
    public String getWhereCondition() {
        return "UserID= " + userID;
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
            sb.append("'").append(name).append("',")
                .append("'").append(lastName).append("',")
                .append("'").append(username).append("',")
                .append("'").append(password).append("',")
                .append("'").append(phoneNumber).append("',")
                .append("'").append(address).append("'");
        System.out.println(sb.toString());
        return sb.toString(); 
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Name=").append("'").append(name).append("', ")
            .append("Lastname=").append("'").append(lastName).append("', ")
            .append("Username=").append("'").append(username).append("', ")
            .append("Password=").append("'").append(password).append("', ")
            .append("PhoneNumber=").append("'").append(phoneNumber).append("', ")
            .append("Address=").append("'").append(address).append("'");

        return sb.toString();
        
        //String query = "Password = ?, PhoneNumber = ?, Address = ? WHERE UserID=" + u.getUserID();

    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> allUSers = new ArrayList<>();
        
        while (rs.next()) {
            User user = new User();
            user.setUserID(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setUsername(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setPhoneNumber(rs.getString(6));
            user.setAddress(rs.getString(7));
            
            allUSers.add(user);
        }
        
        return allUSers;
    }

    @Override
    public void setId(int id) {
        userID = id;
    }


    @Override
    public String getUpdate2(int g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNamesWithJoin() {
        return "user";
    }

    @Override
    public String getSelect(String arg) {
        return "* from user where username = '" + arg + "'";
    }

    @Override
    public String getSecondWhereCondition() {
        return "UserID=";
    }

    @Override
    public Integer getNumber(ResultSet rs) throws Exception {
        int result = 0;
        if (!rs.isBeforeFirst() && rs.getRow() == 0) {
            return result;
        } else {
            result = 1;
        }
        //!rs.isBeforeFirst() && rs.getRow() == 0
        return result;
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
