/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.product;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryProduct;
import server.so.AbstractSO;
import zcommon.domain.Product;

/**
 *
 * @author Korisnik
 */
public class GetAllProductsSO extends AbstractSO{

    //private final DbRepository repositoryProduct;
    private final DbRepository repositoryGeneric;

    public GetAllProductsSO() {
        //this.repositoryProduct = new RepositoryProduct();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //ArrayList<Product> productsList = (ArrayList<Product>) repositoryProduct.getAll();
        Product product = new Product();
        ArrayList<Product> productsList = (ArrayList<Product>) repositoryGeneric.getAll(product);
        for (Product p : productsList) {
            ((ArrayList<Product>)param).add(p);
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
}
