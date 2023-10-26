/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.controller;

import java.io.IOException;
import java.util.ArrayList;
import server.so.AbstractSO;
import server.so.admin.LoginAdminSO;
import server.so.invoice.AddNewInvoiceSO;
import server.so.invoice.GetInvoiceForUserSO;
import server.so.invoice.GetInvoicesForAdminSO;
import server.so.invoice.MakeAPDFofInvoice;
import server.so.invoice.MakeAnExcelOfInvoicesSO;
import server.so.order.GetAllOrdersForAdminSO;
import server.so.order.AddNewOrderSO;
import server.so.order.GetAllOrdersForUserSO;
import server.so.order.GetNumberOfUnapprovedSO;
import server.so.order.GetOrderItemsByOrderIDSO;
import server.so.order.MakeAPDFofOrder;
import server.so.order.MakeAnExcelOfOrdersSO;
import server.so.product.AddNewProductSO;
import server.so.product.DeleteProductSO;
import server.so.product.EditProductSO;
import server.so.product.GetAllProductsSO;
import server.so.user.AddNewUserSO;
import server.so.user.GetAllUsersSO;
import server.so.user.LoginUserSO;
import server.so.user.UpdateUserProfileSO;
import zcommon.domain.Admin;
import zcommon.domain.HelpClass;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class Controller {
    private static Controller instance;
    
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User loginUser(User userReceived) {
        try {
            AbstractSO loginUser = new LoginUserSO();
            loginUser.execute(userReceived);
            return userReceived;
            //this.user = userLogged;
        } catch (Exception ex) {
            System.out.println("ex poruka za usera" + ex.getMessage());
            ex.printStackTrace();
            return null;
            
        }
    }

    public Admin loginAdmin(Admin adminReceived){
        try{
            AbstractSO loginAdmin = new LoginAdminSO();
            loginAdmin.execute(adminReceived);
            return adminReceived;    
        } catch (Exception ex) {
            System.out.println("ex za admin poruka" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        
    }

    public User addNewUser(User newUser) throws Exception {
        AbstractSO addNewUser = new AddNewUserSO();
        addNewUser.execute(newUser);
        return newUser;
    }

    public void getAllUsers(ArrayList<User> listOfUsers) throws Exception {
        AbstractSO getAllUsers = new GetAllUsersSO();
        getAllUsers.execute(listOfUsers);
    }

    public void getAllProducts(ArrayList<Product> listOfProducts) throws Exception {
        AbstractSO getAllProducts = new GetAllProductsSO();
        getAllProducts.execute(listOfProducts);
    }

    public void addNewProduct(Product newProduct) throws Exception {
        AbstractSO addNewProduct = new AddNewProductSO();
        addNewProduct.execute(newProduct);
    }

    public User updateUserProfile(User updateUser) throws Exception {
        AbstractSO updateUserProfile = new UpdateUserProfileSO();
        updateUserProfile.execute(updateUser);
        return updateUser;
    }

    /*public void saveOrder(Order newOrder) throws Exception {
        AbstractSO saveOrder = new AddNewOrderSO(); doens"T exitst anymore, done through the AddNewOrderItemsSO()
        saveOrder.execute(newOrder);
    }*/

    public void saveOrder(Order newOrder) throws Exception {
        AbstractSO saveOrderItems = new AddNewOrderSO();
        saveOrderItems.execute(newOrder);
    }

    public void deleteProduct(Product pForDelete) throws Exception {
        AbstractSO deleteProduct = new DeleteProductSO();
        deleteProduct.execute(pForDelete);
    }

    public void editProduct(Product pForedit) throws Exception {
        AbstractSO editProduct = new EditProductSO();
        editProduct.execute(pForedit);
    }

    public void getAllOrdersAdmin(ArrayList<Order> listOfAllOrders) throws Exception {
        AbstractSO getAllOrdersAdmin = new GetAllOrdersForAdminSO();
        getAllOrdersAdmin.execute(listOfAllOrders);
    }

    public void getOrderItemsByOrderID(ArrayList<OrderItems> listOfOrdersItemsByOrderID) throws Exception {
        AbstractSO getOrderItemsByOrderID = new GetOrderItemsByOrderIDSO();
        getOrderItemsByOrderID.execute(listOfOrdersItemsByOrderID);
    }

    public void saveInvoice(Invoice invoice) throws Exception {
        AbstractSO saveInvoice = new AddNewInvoiceSO();
        saveInvoice.execute(invoice);
    }

    public void getAllInvoicesForUser(ArrayList<Invoice> listOfInvoicesForUser) throws Exception {
        AbstractSO getInvoices = new GetInvoiceForUserSO();
        getInvoices.execute(listOfInvoicesForUser);
    }

    public void getAllOrdersUser(ArrayList<Order> listOfAllOrdersForUSer) throws Exception {
        AbstractSO getOrdersUser = new GetAllOrdersForUserSO();
        getOrdersUser.execute(listOfAllOrdersForUSer);
    }

    public void getNumberOfUnapproved (HelpClass result) throws Exception {
        AbstractSO getNumber = new GetNumberOfUnapprovedSO();
        getNumber.execute(result);
    }

    public void exportOrderToPdf(ArrayList<Order> orders) throws IOException {
        MakeAPDFofOrder makePDF = new MakeAPDFofOrder();
        makePDF.exportOrderToPDF(orders);
    }

    public void exportInvoiceToPdf(ArrayList<Invoice> invoices) throws IOException {
        MakeAPDFofInvoice makePDF = new MakeAPDFofInvoice();
        makePDF.exportInvoiceToPDF(invoices);
    }

    public void exportOrderToExcel(ArrayList<Order> orders) {
        MakeAnExcelOfOrdersSO makeExcel = new MakeAnExcelOfOrdersSO();
        makeExcel.exportOrderToEXCEL(orders);
    }

    public void exportInvoiceToExcel(ArrayList<Invoice> invoices) throws IOException {
        MakeAnExcelOfInvoicesSO makeExcel = new MakeAnExcelOfInvoicesSO();
        makeExcel.exportInvoiceToEXCEL(invoices);
    }

    public void getAllInvoicesForAdmin(ArrayList<Invoice> listOfInvoicesForAdmin) throws Exception{
        GetInvoicesForAdminSO invoicesForAdmin = new GetInvoicesForAdminSO();
        invoicesForAdmin.execute(listOfInvoicesForAdmin);
    }

}
