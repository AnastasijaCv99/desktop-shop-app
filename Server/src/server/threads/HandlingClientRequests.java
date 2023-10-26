/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.threads;

import java.io.IOException;
import zcommon.communication.Request;
import zcommon.communication.Receiver;
import zcommon.communication.Sender;
import zcommon.communication.Response;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.Controller;
import zcommon.communication.Operation;
import zcommon.domain.Admin;
import zcommon.domain.HelpClass;
import zcommon.domain.Invoice;
import zcommon.domain.NewClient;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class HandlingClientRequests extends Thread{
    Socket s;
    Sender sender;
    Receiver receiver;
    Admin a;
    User u;
    ServerThread serverThread;
    boolean active = true;
    
    public HandlingClientRequests(Socket s) {
        this.s = s;
        sender = new Sender(s);
        receiver = new Receiver(s);
    }

    @Override
    public void run() {
        while (active) {            
            try {
                Request request = (Request) receiver.receive();
                Response response = handleRequest(request);
                if (response != null) {
                    sender.send(response);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandlingClientRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

    public void setServerThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public Admin getAdmin() {
        return a;
    }

    public User getUser() {
        return u;
    }
    
    private Response handleRequest(Request request) {
        
        Response response = null;
        switch (request.getOperation()) {
                    case LOGIN:
                        response = login(request);
                        break;
                    case ADD_NEW_USER:
                        response = addNewUser(request);
                        break;
                    case GET_ALL_USERS:
                        response = getAllUsers(request);
                        break;
                    case GET_ALL_PRODUCTS:
                        response = getAllProducts(request);
                        break;
                    case ADD_NEW_PRODUCT:
                        response = addNewProduct(request);
                        break;
                    case UPDATE_PROFILE:
                        response = updateUserProfile(request);
                        break;
                    case SAVE_ORDER:
                        response = saveOrder(request);
                        break;
                    case DELETE_PRODUCT:
                        response = deleteProduct(request);
                        break;
                    case EDIT_PRODUCT:
                        response = editProduct(request);
                        break;
                    case GET_ALL_ORDERS_FOR_ADMIN:
                        response = getAllOrdersAdmin(request);
                        break;
                    case GET_ORDERITEMS_BY_ORDERID:
                        response = getOrderItemsByOrderID(request);
                        break;
                    case SAVE_INVOICE:
                        response = saveInvoice(request);
                        break;
                    case GET_INVOICE:
                        response = getInvoices(request);
                        break;
                    case GET_ORDERS_FOR_USER:
                        response = getOrdersForUser(request);
                        break;
                    case LOGOUT:
                        response = logout(request);
                        break;
                    case GET_NUMBER_OF_UNAPPROVED:
                        response = getNumberOfUnapproved(request);
                        break;
                    case GET_ORDERS_OF_USER_FOR_ADMIN:
                        response = getOrdersOfsUserForAdmin(request);
                        break;
                    case GET_INVOICE_OF_USER_FOR_ADMIN:
                        response = getInvoicesOfsUserForAdmin(request);
                        break;
                    case EXPORT_ORDER_TO_PDF:
                        response = exportOrderToPdf(request);
                        break;
                    case EXPORT_INVOICE_TO_PDF:
                        response = exportInvoiceToPdf(request);
                        break;
                    case EXPORT_ORDERS_TO_EXCEL:
                        response = exportOrderToExcel(request);
                        break;
                    case EXPORT_INVOICE_TO_EXCEL:
                        response = exportInvoicesToExcel(request);
                        break;
                    case GET_AND_EXPORT_INVOICES_FOR_ADMIN:
                        response = getAndExportInvoicesForAdmin();
                        break;
        }
        if (request.getOperation() == Operation.DELETE_PRODUCT || request.getOperation() == Operation.EDIT_PRODUCT) {
            sendAgain(getAllProducts(request));
        }if (request.getOperation() == Operation.SAVE_ORDER) {
            sendToAdmin(getNumberOfUnapproved(request));
        }else if (request.getOperation() == Operation.SAVE_INVOICE) {
            sendAgain(getAllOrdersAdmin(request));
            sendAgain(getNumberOfUnapproved(request));
        }
        return response;
    }


    private Response login(Request request) {
        
        Response response = new Response();
        NewClient client = (NewClient) request.getArgument();
        
        Admin newAdmin = new Admin(-1, null, null, client.getUsername(), client.getPassword());
        User newUser = new User(-1, null, null, client.getUsername(), client.getPassword(), null, null);
        
        
        Admin adminLogged = Controller.getInstance().loginAdmin(newAdmin);
        
        if (adminLogged != null) {
            
            boolean check = true;
            
            for (HandlingClientRequests th : ServerThread.clientThreads) {
                check = !(th.getAdmin() != null && th.getAdmin().getUsername().equals(adminLogged.getUsername()));
                if (check == false) break;
            }
            
            if (check == false ){
                response.setSignal(false);
                response.setResponse("You are already signed in!");
                response.setOperation(Operation.LOGIN);
            } else {
                response.setResponse(adminLogged);
                response.setSignal(true);
                response.setOperation(Operation.LOGIN_ADMIN);
                a = adminLogged;
            }
            
        
        } else {
            User userLogged = Controller.getInstance().loginUser(newUser);
            if (userLogged != null) { 
                
                boolean checkU = true;
                for (HandlingClientRequests th : ServerThread.clientThreads) {
                    checkU = !(th.getUser() != null && th.getUser().getUsername().equals(userLogged.getUsername()));
                    if (checkU == false) break;
                }
                
                if (checkU == false) {
                    response.setSignal(false);
                    response.setResponse("You are already signed in!");
                    response.setOperation(Operation.LOGIN);
                } else {
                    response.setResponse(userLogged);
                    response.setSignal(true);
                    response.setOperation(Operation.LOGIN_USER);
                    u = userLogged;
                }
                
            } else {
                response.setSignal(false);
                response.setResponse("There is no client with this username and password!");
                response.setOperation(Operation.LOGIN);
            }
        }
        return response;
       
    }

    private Response addNewUser(Request request) {
        Response response = new Response();
        User newUser = (User) request.getArgument();

        try {
            User addedUser = Controller.getInstance().addNewUser(newUser);
            response.setResponse(addedUser);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setResponse(null);
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.ADD_NEW_USER);
        return response;
    }

    private Response getAllUsers(Request request) {
        Response response = new Response();
        
        ArrayList<User> listOfUsers = new ArrayList<>();
        try {
            Controller.getInstance().getAllUsers(listOfUsers);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfUsers);
        response.setOperation(Operation.GET_ALL_USERS);
        return response;
    }

    private Response getAllProducts(Request request) {
        Response response = new Response();
        
        ArrayList<Product> listOfProducts = new ArrayList<>();
        try {
            Controller.getInstance().getAllProducts(listOfProducts);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfProducts);
        response.setOperation(Operation.GET_ALL_PRODUCTS);
        return response;
    }

    private Response addNewProduct(Request request) {
        Response response = new Response();
        Product newProduct = (Product) request.getArgument();
        try {
            Controller.getInstance().addNewProduct(newProduct);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.ADD_NEW_PRODUCT);
        return response;
    }

    private Response updateUserProfile(Request request) {
        Response response = new Response();
        User updateUser = (User) request.getArgument();
        User updatedUser = new User();
        try {
            updatedUser = Controller.getInstance().updateUserProfile(updateUser);
            response.setSignal(true);
            response.setResponse(updatedUser);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.UPDATE_PROFILE);
        return response;
    }

    private Response saveOrder(Request request) {
        
        Response response = new Response();
        try {
            Order newOrder = (Order) request.getArgument();
            
            ArrayList<OrderItems> listOfItems = newOrder.getListOfItem();
            /*for (OrderItems listOfItem : listOfItems) {
                if (listOfItem.getProductID() != null) {
                    System.out.println("u obradi kz postoji proizvod " +listOfItem.getProductID().getProductID() );
                } else System.out.println("u obradi kz ne psotoji proizvod");
            }*/
            //treba prvo da se sacuva order da bi se sacuvali i orderItems
            
            Controller.getInstance().saveOrder(newOrder);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.SAVE_ORDER);
        return response;
        
    }

    private Response deleteProduct(Request request) {
        Response response = new Response();
        
        Product pForDelete = (Product) request.getArgument();
        try {
            Controller.getInstance().deleteProduct(pForDelete);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.DELETE_PRODUCT);
        return response;
    }

    private Response editProduct(Request request) {
        Response response = new Response();
        
        Product pForedit = (Product) request.getArgument();
        try {
            Controller.getInstance().editProduct(pForedit);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.EDIT_PRODUCT);
        return response;
    }

    private Response getAllOrdersAdmin(Request request) {
        Response response = new Response();
        
        ArrayList<Order> listOfAllOrders = new ArrayList<>();
        try {
            Controller.getInstance().getAllOrdersAdmin(listOfAllOrders);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfAllOrders);
        response.setOperation(Operation.GET_ALL_ORDERS_FOR_ADMIN);
        return response;
    }

    private Response getOrderItemsByOrderID(Request request) {
        Response response = new Response();
        
        ArrayList<OrderItems> listOfOrdersItemsByOrderID = new ArrayList<>();
        Order order = (Order) request.getArgument();
        OrderItems oi = new OrderItems(0, order, 0, null);
        listOfOrdersItemsByOrderID.add(oi);
        try {
            Controller.getInstance().getOrderItemsByOrderID(listOfOrdersItemsByOrderID);
            listOfOrdersItemsByOrderID.remove(oi);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfOrdersItemsByOrderID);
        response.setOperation(Operation.GET_ORDERITEMS_BY_ORDERID);
        return response;
    }

    private Response saveInvoice(Request request) {
        Response response = new Response();
        try {
            Invoice invoice = (Invoice) request.getArgument();
            Controller.getInstance().saveInvoice(invoice);
            response.setSignal(true);
            System.out.println("izvrsena save invoice + " + response.isSignal());
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setOperation(Operation.SAVE_INVOICE);
        return response;
    }

    private Response getInvoices(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();
        
        Order o = new Order(0, 0.0, u, null, null);
        Invoice i = new Invoice(0, 0.0, true, o, null);
        
        ArrayList<Invoice> listOfInvoicesForUser = new ArrayList<>();
        listOfInvoicesForUser.add(i);
        try {
            Controller.getInstance().getAllInvoicesForUser(listOfInvoicesForUser);
            listOfInvoicesForUser.remove(i);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfInvoicesForUser);
        response.setOperation(Operation.GET_INVOICE);
        return response;
    }

    private Response getOrdersForUser(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();
        Order o = new Order(0, 0.0, u, null, null);
        ArrayList<Order> listOfAllOrdersForUSer = new ArrayList<>();
        listOfAllOrdersForUSer.add(o);
        try {
            Controller.getInstance().getAllOrdersUser(listOfAllOrdersForUSer);
            listOfAllOrdersForUSer.remove(o);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfAllOrdersForUSer);
        response.setOperation(Operation.GET_ORDERS_FOR_USER);
        return response;
    }

    public void sendMessageClosedServer() {
        Response response = new Response();
        String message = "Server stopped, you will be logged out!";
        response.setResponse(message);
        response.setOperation(Operation.SERVER_STOPPED);
        sender.send(response);
    }

    private void sendAgain(Response response) {
        sender.send(response);
    }
    
    private void sendToAdmin(Response response) {
        for (HandlingClientRequests threads : ServerThread.clientThreads) {
            if (threads.a != null) {
                threads.sendAgain(response);
            }
        }
    }

    private Response logout(Request request) {
        Response response = new Response();
        String message = "You have been logged out!";
        response.setResponse(message);
        response.setOperation(Operation.LOGOUT);
      
        ServerThread.clientThreads.remove(this);
       
        active = false;
        return response;
    }

    private Response getNumberOfUnapproved(Request request) {
        Response response = new Response();
     
        HelpClass num = new HelpClass();
        int result = 0;
        
        try {
            Controller.getInstance().getNumberOfUnapproved(num);
            result = num.getNumber();
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));        
        }
        response.setResponse(result);
        System.out.println("res handl cli: " + result);
        response.setOperation(Operation.GET_NUMBER_OF_UNAPPROVED);
        
        return response;
    }

    private Response getOrdersOfsUserForAdmin(Request request) {
        Response response = new Response();
        
        User u = (User) request.getArgument();
        Order o = new Order(0, 0.0, u, null, null);
        ArrayList<Order> listOfAllOrdersOfAUSer = new ArrayList<>();
        listOfAllOrdersOfAUSer.add(o);
        
        try {
            Controller.getInstance().getAllOrdersUser(listOfAllOrdersOfAUSer);
            listOfAllOrdersOfAUSer.remove(o);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfAllOrdersOfAUSer);
        response.setOperation(Operation.GET_ORDERS_OF_USER_FOR_ADMIN);
        
        return response;
    }

    private Response getInvoicesOfsUserForAdmin(Request request) {
        Response response = new Response();
        User u = (User) request.getArgument();
        
        Order o = new Order(0, 0.0, u, null, null);
        Invoice i = new Invoice(0, 0.0, true, o, null);
        
        ArrayList<Invoice> listOfInvoicesForUser = new ArrayList<>();
        listOfInvoicesForUser.add(i);
        try {
            Controller.getInstance().getAllInvoicesForUser(listOfInvoicesForUser);
            listOfInvoicesForUser.remove(i);
            response.setSignal(true);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setResponse(listOfInvoicesForUser);
        response.setOperation(Operation.GET_INVOICE_OF_USER_FOR_ADMIN);
        return response;
    }

    private Response exportOrderToPdf(Request request) {
        Response response = new Response();
        ArrayList<Order> orders = (ArrayList<Order>) request.getArgument();
        
        try {
            //rln ako ne baci ex onda je sve ok
            Controller.getInstance().exportOrderToPdf(orders);
            response.setSignal(true);
            response.setResponse("PDF created successfully!");
        } catch (IOException ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
            response.setResponse("PDF creation failed...");
        }
        response.setOperation(Operation.EXPORT_ORDER_TO_PDF);
        return response;
    }

    private Response exportInvoiceToPdf(Request request) {
        Response response = new Response();
        ArrayList<Invoice> invoices = (ArrayList<Invoice>) request.getArgument();
        
        try {
            Controller.getInstance().exportInvoiceToPdf(invoices);
            response.setSignal(true);
            response.setResponse("PDF created successfully!");
        } catch (IOException ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
            response.setResponse("PDF creation failed...");
        }
        response.setOperation(Operation.EXPORT_INVOICE_TO_PDF);
        return response;
    }

    private Response exportOrderToExcel(Request request) {
        Response response = new Response();
        ArrayList<Order> orders = (ArrayList<Order>) request.getArgument();
        
        try {
            Controller.getInstance().exportOrderToExcel(orders);
            response.setSignal(true);
            response.setResponse("Excel created successfully!");
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
            response.setResponse("Excel creation failed...");
        }
        response.setOperation(Operation.EXPORT_ORDERS_TO_EXCEL);
        return response;
    }

    private Response exportInvoicesToExcel(Request request) {
        Response response = new Response();
        ArrayList<Invoice> invoices = (ArrayList<Invoice>) request.getArgument();
        
        try {
            Controller.getInstance().exportInvoiceToExcel(invoices);
            response.setSignal(true);
            response.setResponse("Excel created successfully!");
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
            response.setResponse("Excel creation failed...");
        }
        response.setOperation(Operation.EXPORT_INVOICE_TO_EXCEL);
        return response;
    }

    
    private Response getAndExportInvoicesForAdmin() {
        Response response = new Response();
        
        //prvo da se vrate svi invoice i onda moze da se salje na excel
        ArrayList<Invoice> listOfInvoicesForAdmin = new ArrayList<>();
        
        try {
            Controller.getInstance().getAllInvoicesForAdmin(listOfInvoicesForAdmin);
        } catch (Exception ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
            //response.setResponse("Excel creation failed...");
        }
        
        try {
            Controller.getInstance().exportInvoiceToExcel(listOfInvoicesForAdmin);
        } catch (IOException ex) {
            response.setSignal(false);
            ex.printStackTrace();
            response.setException(new Exception(ex.getMessage()));
        }
        response.setSignal(true);
        response.setOperation(Operation.GET_AND_EXPORT_INVOICES_FOR_ADMIN);
        return response;
    }
    
    
}
