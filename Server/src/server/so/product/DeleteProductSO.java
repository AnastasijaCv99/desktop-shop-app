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
public class DeleteProductSO extends AbstractSO{

    //private final DbRepository dbRepositoryProduct;
    private final DbRepository dbGeneric;

    public DeleteProductSO() {
        //this.dbRepositoryProduct = new RepositoryProduct();
        this.dbGeneric = new RepositoryGeneric();
    }

   
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //dbRepositoryProduct.delete((Product)param);
        dbGeneric.delete((Product)param);
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
