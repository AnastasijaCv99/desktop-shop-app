/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.product;

import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
//import server.repository.db.impl.RepositoryProduct;
import server.so.AbstractSO;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class EditProductSO extends AbstractSO{

    //private final DbRepository dbRepositoryProduct;
    private final DbRepository dbGeneric;

    public EditProductSO() {
        //this.dbRepositoryProduct = new RepositoryProduct();
        this.dbGeneric = new RepositoryGeneric();
    }
    
    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof Product)) {
            throw new Exception("Invalid data!");
        } else if (((Product)param).getTitle().isEmpty() || ((Product)param).getDescription().isEmpty() || ((Product)param).getPrice() < 0 ||
             ((Product)param).getStock()<0){
            throw new Exception("Incomplete data!");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //dbRepositoryProduct.edit((Product)param);
        dbGeneric.edit((Product)param);
    }

    @Override
    protected void commitTransaction() throws Exception {
        dbGeneric.commit();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        dbGeneric.rollback();
    }

    @Override
    protected void disconnect() throws Exception {
        dbGeneric.disconnect();
    }
    
    
}
