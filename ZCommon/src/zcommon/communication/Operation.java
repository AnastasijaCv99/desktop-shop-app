/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package zcommon.communication;

import java.io.Serializable;

/**
 *
 * @author Anastasija Cvetkovic
 */
public enum Operation implements Serializable{
    LOGIN_USER,
    LOGIN_ADMIN, 
    LOGIN, 
    ADD_NEW_USER, 
    GET_ALL_USERS, 
    GET_ALL_PRODUCTS, 
    ADD_NEW_PRODUCT, 
    UPDATE_PROFILE, 
    SAVE_ORDER, 
    DELETE_PRODUCT, 
    EDIT_PRODUCT, 
    GET_ALL_ORDERS_FOR_ADMIN, 
    GET_ORDERITEMS_BY_ORDERID, 
    SAVE_INVOICE, 
    GET_INVOICE, 
    GET_ORDERS_FOR_USER, 
    SERVER_STOPPED, 
    LOGOUT, 
    GET_NUMBER_OF_UNAPPROVED,
    GET_ORDERS_OF_USER_FOR_ADMIN, 
    GET_INVOICE_OF_USER_FOR_ADMIN, 
    EXPORT_ORDER_TO_PDF, 
    EXPORT_INVOICE_TO_PDF, 
    EXPORT_ORDERS_TO_EXCEL, 
    EXPORT_INVOICE_TO_EXCEL, 
    GET_AND_EXPORT_INVOICES_FOR_ADMIN,
    
}
