/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.communication;

import zcommon.communication.Receiver;
import zcommon.communication.Sender;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import zcommon.communication.Operation;
import zcommon.communication.Request;
import zcommon.domain.Admin;
import zcommon.domain.Invoice;
import zcommon.domain.NewClient;
import zcommon.domain.Order;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class Communication {
    private static Communication instance;
    Socket socketClient;

    Sender sender;
    Receiver receiver;
    
    
    public Communication() throws IOException {
        socketClient = new Socket("localhost", 9000);
        sender = new Sender(socketClient);
        receiver = new Receiver(socketClient);
    }
    
    public static Communication getInstance() throws IOException {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }
    
    public Receiver getReceiver() {
        return receiver;
    }
    
    public void loginAdmin(Admin a) throws ClassNotFoundException {
        Request request = new Request(Operation.LOGIN_ADMIN, a);
        sender.send(request);
    }

    public void login(NewClient nc) {
        Request request = new Request(Operation.LOGIN, nc);
        sender.send(request);
    }

    public void addNewUser(User newUser) {
        Request request = new Request(Operation.ADD_NEW_USER, newUser);
        sender.send(request);
    }

    public void getListOfAllUsers() {
        Request request = new Request();
        request.setOperation(Operation.GET_ALL_USERS);
        sender.send(request);
    }

    public void getListOfAllProducts() {
        Request request = new Request();
        request.setOperation(Operation.GET_ALL_PRODUCTS);
        sender.send(request);
    }

    public void addNewProduct(Product product) {
        Request request = new Request(Operation.ADD_NEW_PRODUCT, product);
        sender.send(request);
    }

    public void updateUserProfile(User newUser) {
        Request request = new Request(Operation.UPDATE_PROFILE, newUser);
        sender.send(request);
    }

    public void saveOrder(Order newOrder) {
        Request request = new Request(Operation.SAVE_ORDER, newOrder);
        sender.send(request);
    }

    public void deleteProduct(Product pForDelete) {
        Request request = new Request(Operation.DELETE_PRODUCT, pForDelete);
        sender.send(request);
    }

    public void editProduct(Product pForedit) {
        Request request = new Request(Operation.EDIT_PRODUCT, pForedit);
        sender.send(request);
    }

    public void getListOfAllOrdersForAdmin() {
        Request request = new Request();
        request.setOperation(Operation.GET_ALL_ORDERS_FOR_ADMIN);
        sender.send(request);
    }

    public void getOrderItemsByOrder(Order order) {
        Request request = new Request(Operation.GET_ORDERITEMS_BY_ORDERID, order);
        sender.send(request);
    }

    public void saveInvoice(Invoice invoice) {
        Request request = new Request(Operation.SAVE_INVOICE, invoice);
        sender.send(request);
    }

    public void getListOfInvoicesForUser(User u) {
        Request request = new Request(Operation.GET_INVOICE, u);
        sender.send(request);
    }

    public void getAllDissapprovedOrders(User u) {
        Request request = new Request(Operation.GET_ORDERS_FOR_USER, u);
        sender.send(request);
    }

    public void logout() {
        Request request = new Request(Operation.LOGOUT, null);
        sender.send(request);
    }

    public void getNumberOfUnapproved() {
        Request request = new Request();
        request.setOperation(Operation.GET_NUMBER_OF_UNAPPROVED);
        sender.send(request);
    }

    public void getOrdersOfUserForAdmin(User u) {
        Request request = new Request(Operation.GET_ORDERS_OF_USER_FOR_ADMIN, u);
        sender.send(request);
    }

    public void getInvoicesOfUserForAdmin(User u) {
        Request request = new Request(Operation.GET_INVOICE_OF_USER_FOR_ADMIN, u);
        sender.send(request);
    }

    public void exportOrdersToPdf(ArrayList<Order> orders) {
        Request request = new Request(Operation.EXPORT_ORDER_TO_PDF, orders);
        sender.send(request);
    }

    public void exportInvoicesToPdf(ArrayList<Invoice> invoices) {
        Request request = new Request(Operation.EXPORT_INVOICE_TO_PDF, invoices);
        sender.send(request);
    }

    public void exportOrdersToExcel(ArrayList<Order> orders) {
        Request request = new Request(Operation.EXPORT_ORDERS_TO_EXCEL, orders);
        sender.send(request);
    }

    public void exportInvoicesToExcel(ArrayList<Invoice> invoices) {
        Request request = new Request(Operation.EXPORT_INVOICE_TO_EXCEL, invoices);
        sender.send(request);
    }

    public void exportAllInvoicesToExcellForAdmin() {
        Request request = new Request();
        request.setOperation(Operation.GET_AND_EXPORT_INVOICES_FOR_ADMIN);
        sender.send(request);
    }
    
    
}
