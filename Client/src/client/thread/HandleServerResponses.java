/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.thread;

import client.communication.Communication;
import client.controller.ControllerClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import zcommon.communication.Response;
import zcommon.domain.Admin;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class HandleServerResponses extends Thread{

    @Override
    public void run() {
        while (true) {            
            try {
                Response response = (Response) Communication.getInstance().getReceiver().receive();
                switch (response.getOperation()) {
                    case LOGIN_ADMIN:
                        Admin admin = (Admin) response.getResponse();
                        ControllerClient.getInstance().loginAdmin(admin);
                        break;
                    case LOGIN_USER:
                        User user = (User) response.getResponse();
                        ControllerClient.getInstance().loginUser(user);
                        break;
                    case LOGIN:
                        //if client who is trying to log in is not either a admin or an user
                        String messLogin = (String) response.getResponse();
                        ControllerClient.getInstance().handleError(messLogin);
                        break;
                    case ADD_NEW_USER:
                        User userAdded = (User) response.getResponse();
                        boolean signal = response.isSignal();
                        Exception mEx = response.getException();
                        ControllerClient.getInstance().handleNewUserAdded(userAdded, signal, mEx);
                        break;
                    case GET_ALL_USERS:
                        ArrayList<User> users = (ArrayList<User>) response.getResponse();
                        boolean signal1 = response.isSignal();
                        ControllerClient.getInstance().handleListOfUsers(users, signal1);
                        break;
                    case GET_ALL_PRODUCTS:
                        ArrayList<Product> products = (ArrayList<Product>) response.getResponse();
                        boolean sig = response.isSignal();
                        ControllerClient.getInstance().handleListOfProducts(products, sig);
                        break;
                    case ADD_NEW_PRODUCT:
                        boolean signal2 = response.isSignal();
                        ControllerClient.getInstance().handleNewProductAdded(signal2);
                        break;
                    case UPDATE_PROFILE:
                        User updatedUser = (User) response.getResponse();
                        boolean signal3 = response.isSignal();
                        ControllerClient.getInstance().handleProfileUpdated(updatedUser, signal3);
                        break;
                    case SAVE_ORDER:
                        boolean signal4 = response.isSignal();
                        ControllerClient.getInstance().handleSaveOrder(signal4);
                        break;
                    case DELETE_PRODUCT:
                        boolean signal5 = response.isSignal();
                        ControllerClient.getInstance().handleDeleteProduct(signal5);
                        break;
                    case EDIT_PRODUCT:
                        boolean signal6 = response.isSignal();
                        ControllerClient.getInstance().handleEditedProduct(signal6);
                        break;
                    case GET_ALL_ORDERS_FOR_ADMIN:
                        boolean signal7 = response.isSignal();
                        ArrayList<Order> ordersForAdmin = (ArrayList<Order>) response.getResponse();
                        ControllerClient.getInstance().handleOrdersForAdmin(ordersForAdmin, signal7);
                        break;
                    case GET_ORDERITEMS_BY_ORDERID:
                        boolean signal8 = response.isSignal();
                        ArrayList<OrderItems> listOfORderItemsForAdminByID = (ArrayList<OrderItems>) response.getResponse();
                        ControllerClient.getInstance().handleListOfOrderITems(listOfORderItemsForAdminByID, signal8);
                        break;
                    case SAVE_INVOICE:
                        boolean signal9 = response.isSignal();
                        System.out.println("save invoice check for signal " + signal9);
                        ControllerClient.getInstance().handleSavedInvoice(signal9);
                        break;
                    case GET_INVOICE:
                        boolean signal10 = response.isSignal();
                        ArrayList<Invoice> invoices = (ArrayList<Invoice>) response.getResponse();
                        ControllerClient.getInstance().handleListOfInvoicesForUSer(invoices, signal10);
                        break;
                    case GET_ORDERS_FOR_USER:
                        boolean signal11 = response.isSignal();
                        ArrayList<Order> ordersForUser = (ArrayList<Order>) response.getResponse();
                        ControllerClient.getInstance().handleOrdersForUser(ordersForUser, signal11);
                        break;
                    case SERVER_STOPPED:
                        String message = (String) response.getResponse();
                        ControllerClient.getInstance().handleServerStopped(message);
                        break;
                    case LOGOUT:
                        String messageLogout = (String) response.getResponse();
                        ControllerClient.getInstance().handleLogout(messageLogout);
                        break;
                    case GET_NUMBER_OF_UNAPPROVED:
                        boolean signal12 = response.isSignal();
                        int result = (int) response.getResponse();
                        ControllerClient.getInstance().handleNumberOfUnapproved(result, signal12);
                        break;       
                    case GET_ORDERS_OF_USER_FOR_ADMIN:
                        boolean signal13 = response.isSignal();
                        ArrayList<Order> ordersOfUserForAdmin = (ArrayList<Order>) response.getResponse();
                        ControllerClient.getInstance().handleOrdersOfAUser(ordersOfUserForAdmin, signal13);
                        break;
                    case GET_INVOICE_OF_USER_FOR_ADMIN:
                        boolean signal14 = response.isSignal();
                        ArrayList<Invoice> invoicesOfUserForAdmin = (ArrayList<Invoice>) response.getResponse();
                        ControllerClient.getInstance().handleInvoicesOfUserForAdmin(invoicesOfUserForAdmin, signal14);
                        break;
                    case EXPORT_ORDER_TO_PDF:
                        boolean signal15 = response.isSignal();
                        String messagePDF = (String) response.getResponse();
                        ControllerClient.getInstance().handlePDFCreationOrder(signal15, messagePDF);
                        break;
                    case EXPORT_INVOICE_TO_PDF:
                        String messagePDFInvoice = (String) response.getResponse();
                        ControllerClient.getInstance().handlePDFCreationInvoice( messagePDFInvoice);
                        break;
                    case EXPORT_ORDERS_TO_EXCEL:
                        String messageEXCELOrder = (String) response.getResponse();
                        ControllerClient.getInstance().handleExcelCreationOrders( messageEXCELOrder);
                        break;
                    case EXPORT_INVOICE_TO_EXCEL:
                        String messageEXCELInvoice = (String) response.getResponse();
                        ControllerClient.getInstance().handleExcelCreationInvoices( messageEXCELInvoice);
                        break;
                    case GET_AND_EXPORT_INVOICES_FOR_ADMIN:
                        boolean signal16 = response.isSignal();
                        Exception mEx16 = response.getException();
                        ControllerClient.getInstance().handleAllInvoicesToExcelForAdmin(signal16, mEx16);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(HandleServerResponses.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleServerResponses.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
