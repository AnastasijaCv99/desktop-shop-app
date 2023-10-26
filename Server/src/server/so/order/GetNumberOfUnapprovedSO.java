/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.order;

import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryOrder;
import server.so.AbstractSO;
import zcommon.domain.HelpClass;
import zcommon.domain.Order;

/**
 *
 * @author Korisnik
 */
public class GetNumberOfUnapprovedSO extends AbstractSO{
    
    //private final DbRepository repositoryOrder;
    private final DbRepository repositoryGeneric;
    
    public GetNumberOfUnapprovedSO() {
        //this.repositoryOrder = new RepositoryOrder();
        this.repositoryGeneric = new RepositoryGeneric();
    }

    @Override
    protected void executeTransaction(Object param) throws Exception {
        Order o = new Order();
        String s = "s";
        int result = repositoryGeneric.getCountOf(o, s);
        ((HelpClass)param).setNumber(result);
    }
    
    
    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
}
