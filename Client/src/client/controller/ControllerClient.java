/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.controller;

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
import java.util.ArrayList;
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
public class ControllerClient {
    private static ControllerClient instance;
    
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
    

    public void loginAdmin(Admin admin) {
        lf.handleLoginAdmin(admin);
    }

    public void loginUser(User user) {
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
