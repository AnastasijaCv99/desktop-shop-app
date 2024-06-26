/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package client.view.forms;

import client.communication.Communication;
import client.controller.ControllerClient;
import client.view.table.TableModelOrderItems;
import client.view.table.TableModelProductsForAdmin;
import client.view.table.TableModelProductsForUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import zcommon.domain.Admin;
import zcommon.domain.Product;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class FormAllProductsView extends javax.swing.JDialog {

    /**
     * Creates new form FormAllProductsAdmin
     */
    User u;
    Admin a;
    JDialog editForm;
    
    public FormAllProductsView(java.awt.Frame parent, boolean modal, Admin a, User u) {
        super(parent, modal);
        
        initComponents();
        setLocationRelativeTo(null);
        
        btnAllProducts.setVisible(false);
        
        ControllerClient.getInstance().setAllproductsf(this);
        txtWelcome.setText("All products");
        this.a = a;
        this.u = u;
        
        
        if (a == null) {
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            tblProducts.setModel(new TableModelProductsForUser());
        }
        if (u == null) {
            btnAddToCart.setVisible(false);
            tblProducts.setModel(new TableModelProductsForAdmin());
        }
        
        fillInTheTable();
        
        setSize(600, 600);
        setName("Products");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtWelcome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtMessageAdded = new javax.swing.JLabel();
        btnAllProducts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtWelcome.setText("  ");

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProducts);

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAddToCart.setText("ADD TO CART");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Search by title:");

        jButton4.setText("SEARCH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("GO BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMessageAdded.setText(" ");

        btnAllProducts.setText("ALL PRODUCTS");
        btnAllProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllProductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(119, 119, 119)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMessageAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAddToCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAllProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtWelcome)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAllProducts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddToCart)
                    .addComponent(txtMessageAdded))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        int row = tblProducts.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(this, "Please select a product!");
        } else {
            TableModelProductsForUser tmpu = (TableModelProductsForUser) tblProducts.getModel();
            Product chosenProduct = tmpu.getProduct(row);
            //ControllerClient.getInstance().handleAddToCart(chosenProduct); => exception
            
            
            int reservation = chosenProduct.getReservation();
            int stock = chosenProduct.getStock();
            if (reservation<stock || stock != 0) {
                //chosenProduct.setReservation(++reservation);
                TableModelOrderItems.addProduct(chosenProduct);
                
                txtMessageAdded.setText("");
                txtMessageAdded.setText("Product " + chosenProduct.getTitle() + " added to cart!");
            } else {
                txtMessageAdded.setText("");
                txtMessageAdded.setText("This product is currently unavailable");
            }
            
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = tblProducts.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "You have to choose a product!");
        } else {
            //try {
                TableModelProductsForAdmin tmpa = (TableModelProductsForAdmin) tblProducts.getModel();
                Product pForDelete = tmpa.getAProduct(row);
                if (pForDelete == null) {
                    JOptionPane.showMessageDialog(this, "there is an error, sorry");
                    return;
                } else {
                    tmpa.deleteAProduct(pForDelete);
                    //Communication.getInstance().deleteProduct(pForDelete);
                    ControllerClient.getInstance().deleteProduct(pForDelete);
                }
            //} catch (IOException ex) {
              //  Logger.getLogger(FormAllProductsView.class.getName()).log(Level.SEVERE, null, ex);
            //}
        }
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int row = tblProducts.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "You have to choose a product!");
        } else {
           
                TableModelProductsForAdmin tmpa = (TableModelProductsForAdmin) tblProducts.getModel();
                Product pForedit = tmpa.getAProduct(row);
                if (pForedit == null) {
                    JOptionPane.showMessageDialog(this, "there is an error, sorry");
                    return;
                } else {
                    //open a form with product details
                    editForm = new FormEditProductAdmin(this, true, pForedit);
                    editForm.setVisible(true);
                   // Communication.getInstance().editProduct(pForedit);
                }
            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       
        String searchTerm = txtSearch.getText().toLowerCase().trim();
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setText("You have to insert product title!");
        } else {
            if (a != null) {
                TableModelProductsForAdmin tmpa = (TableModelProductsForAdmin) tblProducts.getModel();
                ArrayList<Product> wholeList = tmpa.getListOfProducts();
                ArrayList<Product> newList = new ArrayList<>();
                for (Product product : wholeList) {
                    if (product.getTitle().toLowerCase().contains(searchTerm)) {
                        newList.add(product);
                    }
                }
                if(newList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No products with that title!");
                } else {
                    tmpa.updateTable(newList);
                    btnAllProducts.setVisible(true);
                }
            }
            if (u != null){
                TableModelProductsForUser tmpu = (TableModelProductsForUser) tblProducts.getModel();
                ArrayList<Product> wholeList = tmpu.getListOfProducts();
                ArrayList<Product> newList = new ArrayList<>();
                for (Product product : wholeList) {
                    if (product.getTitle().toLowerCase().contains(searchTerm)) {
                        newList.add(product);
                    }
                }
                if(newList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No products with that title!");
                } else {
                    tmpu.updateTable(newList);
                    btnAllProducts.setVisible(true);
                }
            }
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnAllProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllProductsActionPerformed
        // TODO add your handling code here:
        fillInTheTable();
        btnAllProducts.setVisible(false);
    }//GEN-LAST:event_btnAllProductsActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnAllProducts;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProducts;
    private javax.swing.JLabel txtMessageAdded;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables

    private void fillInTheTable() {
        /*try {
            Communication.getInstance().getListOfAllProducts();
        } catch (IOException ex) {
            Logger.getLogger(FormAllProductsView.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        ControllerClient.getInstance().getListOfAllProducts();
    }

    public void handleListOfProducts(ArrayList<Product> products, boolean sig) {
        if (sig) {
            if (a == null) {
                TableModelProductsForUser tmpu = (TableModelProductsForUser) tblProducts.getModel();
                tmpu.fill(products);
            }
            if (u == null) {
                TableModelProductsForAdmin tmpa = (TableModelProductsForAdmin) tblProducts.getModel();
                tmpa.fill(products);
            }
        } else {
            JOptionPane.showMessageDialog(this, "something went wrong...");
        }
        
    }

    public void handleDeleteProduct(boolean signal5) {
        if (signal5) {
            JOptionPane.showMessageDialog(this, "Successfully deleted!");
        } else
            JOptionPane.showMessageDialog(this, "Something went wrong with deleting...");
    }

    public void handleEditedProduct(boolean signal6) {
        if (signal6) {
            //JOptionPane.showMessageDialog(this, "Successfully edited!");
            txtMessageAdded.setText("Successfully edited!");
        } else {
            //JOptionPane.showMessageDialog(this, "Something went wrong with editing...");
            txtMessageAdded.setText("Something went wrong with editing...");
        }
        
    }

}
