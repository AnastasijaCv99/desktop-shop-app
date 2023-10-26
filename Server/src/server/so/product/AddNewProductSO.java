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
public class AddNewProductSO extends AbstractSO{

    //private final DbRepository dbRepositoryProduct;
    private final DbRepository dbRepoGen;

    public AddNewProductSO() {
        //this.dbRepositoryProduct = new RepositoryProduct();
        this.dbRepoGen = new RepositoryGeneric();
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
        //dbRepositoryProduct.add((Product)param);
        dbRepoGen.add((Product)param);
    }

    @Override
    protected void commitTransaction() throws Exception {
        dbRepoGen.commit();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        dbRepoGen.rollback();
    }

    @Override
    protected void disconnect() throws Exception {
        dbRepoGen.disconnect();
    }
    
    
}
