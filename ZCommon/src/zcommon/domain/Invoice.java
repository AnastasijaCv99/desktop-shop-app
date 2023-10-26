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
public class Invoice implements Serializable, GenericEntity{
    private int invoiceID;
    private Double totalAmountPrice;
    private boolean sentToUser;
    private Order orderID;
    private Admin adminID;

    public Invoice() {
    }

    public Invoice(int invoiceID, Double amount, boolean sentToUser, Order orderID, Admin adminID) {
        this.invoiceID = invoiceID;
        this.totalAmountPrice = amount;
        this.sentToUser = sentToUser;
        this.orderID = orderID;
        this.adminID = adminID;
    }


    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Double getAmount() {
        return totalAmountPrice;
    }

    public void setAmount(Double amount) {
        this.totalAmountPrice = amount;
    }

    public boolean isSent() {
        return sentToUser;
    }

    public void setSent(boolean sent) {
        this.sentToUser = sent;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getTableName() {
        return "invoice";
    }

    @Override
    public String getColumnNames() {
        return "InvoiceID, TotalAmountPrice, SentToUser, IDOrder, IDAdmin";
    }

    @Override
    public String getColumnNamesForAdding() {
        return "TotalAmountPrice, SentToUser, IDOrder, IDAdmin";
    }

    @Override
    public String getColumnNamesWithJoin() {
        return "invoice i JOIN ordert o ON (i.IDOrder = o.OrderID)";
    }

    @Override
    public String getSelect(String arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getSecondWhereCondition() {
        return "o.IDUser =";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
            sb.append(totalAmountPrice).append(",")
                .append(1).append(",")
                .append(orderID.getOrderID()).append(",")
                .append(adminID.getAdminID());
        System.out.println(sb.toString());
        return sb.toString(); 
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdate2(int g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> listOfInvoices = new ArrayList<>();
       
        while(rs.next()) {
            User u = new User(rs.getInt("IDUser"), null, null, null, null, null, null);
            Order o = new Order(rs.getInt("OrderID"), rs.getDouble("TotalAmountPrice"), u, null, null);
            Invoice i = new Invoice(rs.getInt("InvoiceID"), rs.getDouble("TotalAmountPrice"), true, o, null);
            
            listOfInvoices.add(i);
        }
        return listOfInvoices;
    }

    @Override
    public List<GenericEntity> getList2(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer getNumber(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(int id) {
        invoiceID = id;
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
        return "i.IDAdmin = 1;";
    }
    
    
}
