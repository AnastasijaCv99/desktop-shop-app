/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

import client.communication.Communication;
import client.view.forms.AdminMainForm;
import client.view.forms.FormAddNewClientAdmin;
import client.view.forms.FormAddNewProductAdmin;
import client.view.forms.FormAllClientsAdmin;
import client.view.forms.FormAllOrdersAdmin;
import client.view.forms.FormAllOrdersOfAUserForAdmin;
import client.view.forms.FormAllOrdersUser;
import client.view.forms.FormAllProductsView;
import client.view.forms.FormEditProductAdmin;
import client.view.forms.FormProfileUser;
import client.view.forms.FormYourCartUser;
import client.view.forms.LoginForm;
import client.view.forms.UserMainForm;
import java.io.IOException;
import java.util.ArrayList;
import zcommon.communication.Operation;
import zcommon.communication.Request;
import zcommon.communication.Sender;
import zcommon.domain.Admin;
import zcommon.domain.Invoice;
import zcommon.domain.NewClient;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class ControllerClient {
    private static ControllerClient instance;
    private Sender sender;
    
    private LoginForm lf;
    private UserMainForm usermf;
    private AdminMainForm adminmf;
    private FormAllClientsAdmin allusersf;
    private FormAllProductsView allproductsf;
    private FormEditProductAdmin editproductf;
    private FormYourCartUser yourCartf;
    private FormAddNewClientAdmin addnewclientf;
    private FormAddNewProductAdmin addnewproductf;
    private FormProfileUser userprofile;
    private FormAllOrdersAdmin allordersadmin;
    private FormAllOrdersUser allordersuser;
    private FormAllOrdersOfAUserForAdmin allOrdersOfUserForAdmin;
    
    public static ControllerClient getInstance() {
        if (instance == null) {
            instance = new ControllerClient();
        }
        return instance;
    }

    public ControllerClient() {
        try {
            sender = Communication.getInstance().getSender();
        } catch (IOException e) {
            System.out.println("exc cought " + e.getMessage());
        }
    }    
        
        
    public void setLf(LoginForm lf) {
        this.lf = lf;
    }

    public void setAdminmf(AdminMainForm adminmf) {
        this.adminmf = adminmf;
    }

    public void setUsermf(UserMainForm usermf) {
        this.usermf = usermf;
    }

    public void setAllusersf(FormAllClientsAdmin allusersf) {
        this.allusersf = allusersf;
    }

    public void setAllproductsf(FormAllProductsView allproductsf) {
        this.allproductsf = allproductsf;
    }

    public void setYourCartf(FormYourCartUser yourCart) {
        this.yourCartf = yourCart;
    }

    public void setAddnewclientf(FormAddNewClientAdmin addnewclientf) {
        this.addnewclientf = addnewclientf;
    }

    public void setAddnewproductf(FormAddNewProductAdmin addnewproductf) {
        this.addnewproductf = addnewproductf;
    }

    public void setEditproductf(FormEditProductAdmin editproductf) {
        this.editproductf = editproductf;
    }

    public void setUserprofile(FormProfileUser userprofile) {
        this.userprofile = userprofile;
    }

    public void setAllordersadmin(FormAllOrdersAdmin allordersadmin) {
        this.allordersadmin = allordersadmin;
    }

    public void setAllordersuser(FormAllOrdersUser allordersuser) {
        this.allordersuser = allordersuser;
    }

    public void setAllOrdersOfUserForAdmin(FormAllOrdersOfAUserForAdmin allOrdersOfUserForAdmin) {
        this.allOrdersOfUserForAdmin = allOrdersOfUserForAdmin;
    }
    
    public void login(NewClient nc){
        System.out.println("slanje");
        Request request = new Request(Operation.LOGIN, nc);
        sender.send(request);
        System.out.println("poslat");
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
    
    public void loginAdmin(Admin admin) {
        lf.handleLoginAdmin(admin);
    }

    public void loginUser(User user) {
        System.out.println("primeljno"); 
        lf.handleLoginUser(user);
    }

    public void handleError(String mess) {
        //if client who is trying to log in is not either a admin or an user
        lf.handleError(mess);
    }

    public void handleNewUserAdded(User userAdded, boolean signal, Exception mEx) {
        addnewclientf.handleNewUserAdded(userAdded, signal, mEx);
    }

    public void handleListOfUsers(ArrayList<User> users, boolean signal1) {
        allusersf.handleListOfUsers(users, signal1);
    }

    public void handleListOfProducts(ArrayList<Product> products, boolean sig) {
        allproductsf.handleListOfProducts(products, sig);
    }

    public void handleNewProductAdded(boolean signal2) {
        addnewproductf.handleNewProduct(signal2);
    }

    public void handleProfileUpdated(User updatedUser, boolean signal3) {
        usermf.handleProfileUpdated(updatedUser, signal3);
    }

    public void handleSaveOrder(boolean signal4) {
        usermf.handleSaveOrder(signal4);
    }

    public void handleDeleteProduct(boolean signal5) {
        allproductsf.handleDeleteProduct(signal5);
    }

    public void handleEditedProduct(boolean signal6) {
        allproductsf.handleEditedProduct(signal6);
        //editproductf.handleEditedProduct(signal6);
    }

    public void handleOrdersForAdmin(ArrayList<Order> ordersForAdmin, boolean signal7) {
        if (allordersadmin != null) allordersadmin.handleAllOrders(ordersForAdmin, signal7);
        
    }

    public void handleListOfOrderITems(ArrayList<OrderItems> listOfORderItemsForAdminByID, boolean signal8) {
        allordersadmin.handleListOfOrderITems(listOfORderItemsForAdminByID, signal8);
    }

    public void handleSavedInvoice(boolean signal9) {
        if (allordersadmin != null) {
            allordersadmin.handleSavedInvoice(signal9);
        } else if (allOrdersOfUserForAdmin != null) allOrdersOfUserForAdmin.handleSavedInvoice(signal9);
    }

    public void handleListOfInvoicesForUSer(ArrayList<Invoice> invoices, boolean signal10) {
        allordersuser.handleListOfInvoices(invoices, signal10);
    }

    public void handleOrdersForUser(ArrayList<Order> ordersForUser, boolean signal11) {
        allordersuser.handleListOfOrders(ordersForUser, signal11);
    }

    public void handleServerStopped(String message) {
        if (adminmf != null) {
            adminmf.handleServerStopped(message);
        }
        if (usermf != null) {
            usermf.handleServerStopped(message);
        }
        
    }

    public void handleLogout(String messageLogout) {
        if (adminmf != null) {
            adminmf.handleLogout(messageLogout);
        }
        if (usermf != null) {
            usermf.handleLogout(messageLogout);
        }
    }

    public void handleNumberOfUnapproved(int result, boolean signal12) {
        adminmf.handleNumberOfUnapproved(result, signal12);
    }

    public void handleOrdersOfAUser(ArrayList<Order> ordersOfUserForAdmin, boolean signal13) {
        allOrdersOfUserForAdmin.handleListOfOrdersOfUser(ordersOfUserForAdmin, signal13);
    }

    public void handleInvoicesOfUserForAdmin(ArrayList<Invoice> invoicesOfUserForAdmin, boolean signal14) {
        allOrdersOfUserForAdmin.handleListOfInvoicesOfUser(invoicesOfUserForAdmin, signal14);
    }

    public void handlePDFCreationOrder(boolean signal15, String messagePDF) {
        allordersuser.handlePDFCreationOrder(signal15, messagePDF);
    }

    public void handlePDFCreationInvoice(String messagePDFInvoice) {
        allordersuser.handlePDFCreationInvoice(messagePDFInvoice);
    }

    public void handleExcelCreationOrders(String messageEXCELOrder) {
        if (allordersuser != null ) {
            allordersuser.handleExcelCreationOrder(messageEXCELOrder);
        } else if (allordersadmin != null){
            allordersadmin.handleExcelCreationOrder(messageEXCELOrder);
        }
    }

    public void handleExcelCreationInvoices(String messageEXCELInvoice) {
        allordersuser.handleExcelCreationInvoice(messageEXCELInvoice);
    }

    public void handleAllInvoicesToExcelForAdmin(boolean signal16, Exception mEx16) {
        allordersadmin.handleAllInvoicesToExcellForAdmin(signal16, mEx16);
    }
    
    
}
