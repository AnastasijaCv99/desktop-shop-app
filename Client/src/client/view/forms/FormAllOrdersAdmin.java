/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package client.view.forms;

import client.communication.Communication;
import client.controller.ControllerClient;
import client.view.table.TableModelAllOrderItemsForAnOrder;
import client.view.table.TableModelOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import zcommon.domain.Admin;
import zcommon.domain.Invoice;
import zcommon.domain.Order;
import zcommon.domain.OrderItems;

/**
 *
 * @author Korisnik
 */
public class FormAllOrdersAdmin extends javax.swing.JDialog {

    /**
     * Creates new form FormAllOrdersAdmin
     */
    Admin a;
    public FormAllOrdersAdmin(java.awt.Frame parent, boolean modal, Admin a) {
        super(parent, modal);
        initComponents();
        this.a = a;
        setLocationRelativeTo(null);
        ControllerClient.getInstance().setAllordersadmin(this);
        lblMessage.setText("Approve orders, " + a.getUsername());
        tblOrders.setModel(new TableModelOrder());
        fillUpTheTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        txtWelcome = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtMessage = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        lblTextMessage = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblOrders);

        jButton1.setText("SEE ORDER ITEMS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("APPROVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GO BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("EXPORT TO EXCEL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblMessage.setText("    ");

        lblTextMessage.setText("    ");

        jButton5.setText("EXPORT ALL INVOICES TO EXCEL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addGap(65, 65, 65)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTextMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(txtWelcome)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lblMessage))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(8, 8, 8)
                .addComponent(txtMessage)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTextMessage)
                    .addComponent(jButton5))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // see order details
        TableModelOrder tmo = (TableModelOrder) tblOrders.getModel();
        int row = tblOrders.getSelectedRow();
        if (row != -1) {
            Order newOrder = tmo.getOrder(row);
            try {
                Communication.getInstance().getOrderItemsByOrder(newOrder);
            } catch (IOException ex) {
                Logger.getLogger(FormAllOrdersAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // approve order
        TableModelOrder tmo = (TableModelOrder) tblOrders.getModel();
        int row = tblOrders.getSelectedRow();
        if (row != -1) {
            Order newOrder = tmo.getOrder(row);
            Invoice i = new Invoice(0, newOrder.getTotalAmountPricee(), true, newOrder, a);
            try {
                Communication.getInstance().saveInvoice(i);
            } catch (IOException ex) {
                Logger.getLogger(FormAllOrdersAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // goBack button
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // export to excel
        TableModelOrder tmo = (TableModelOrder) tblOrders.getModel();
        ArrayList<Order> orders = tmo.getListOrder();
        
        try {
            Communication.getInstance().exportOrdersToExcel(orders);
        } catch (IOException ex) {
            Logger.getLogger(FormAllOrdersAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // EXPORT ALL INVOICES TO EXCEL
        try {
            Communication.getInstance().exportAllInvoicesToExcellForAdmin();
        } catch (IOException ex) {
            Logger.getLogger(FormAllOrdersAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTextMessage;
    private javax.swing.JTable tblOrders;
    private javax.swing.JLabel txtMessage;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables

    
    private void fillUpTheTable() {
        try {
            Communication.getInstance().getListOfAllOrdersForAdmin();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FormAllOrdersAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void handleAllOrders(ArrayList<Order> ordersForAdmin, boolean signal7) {
        if (signal7) {
            TableModelOrder tmo = (TableModelOrder) tblOrders.getModel();
            tmo.addList(ordersForAdmin);
        } else JOptionPane.showMessageDialog(this, "Something went wrong with table...");
    }

    public void handleListOfOrderITems(ArrayList<OrderItems> listOfORderItemsForAdminByID, boolean signal8) {
        if (signal8) {
            TableModelAllOrderItemsForAnOrder.addList(listOfORderItemsForAdminByID);
            new FormAllOrderItemsForAnOrder(this, true).setVisible(true);
        } else JOptionPane.showMessageDialog(this, "Something went wrong with table...");
    }

    public void handleSavedInvoice(boolean signal9) {
        if (signal9) {
            //System.out.println("uspesno cuvanje ovog sranja");
            lblTextMessage.setText("Successfully approved order!");
        } else {
            lblTextMessage.setText("Something went wrong");
            //System.out.println("jebem mu misa");
        }
        
    }

    public void handleExcelCreationOrder(String messageEXCELOrder) {
        JOptionPane.showMessageDialog(this, messageEXCELOrder);
    }

    

    public void handleAllInvoicesToExcellForAdmin(boolean signal16, Exception mEx16) {
        if (signal16) {
            lblTextMessage.setText("Successfully saved Excell file!");
        } else {
            lblTextMessage.setText(mEx16.getMessage());
        }
    }

}