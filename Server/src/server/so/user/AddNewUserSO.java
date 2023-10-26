/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.user;

import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryUser;
import server.so.AbstractSO;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class AddNewUserSO extends AbstractSO{

    //private final DbRepository repositoryUser;
    private final DbRepository repositoryGeneric;

    
    public AddNewUserSO() {
        //this.repositoryUser = new RepositoryUser();
        this.repositoryGeneric = new RepositoryGeneric();
    }

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid data!");
        } else if(((User)param).getName().isEmpty() || ((User)param).getLastName().isEmpty() || ((User)param).getUsername().isEmpty() 
                || ((User)param).getPassword().isEmpty() || ((User)param).getAddress().isEmpty() || ((User)param).getPhoneNumber().isEmpty()){
            throw new Exception("Incomplete data!");
        } else {
            User uForC = (User)param;
            String un = uForC.getUsername();
            int result = repositoryGeneric.getCountOf(uForC,un);
            System.out.println("resut iz addSo "+result);
            if (result != 0) throw new Exception("User with this username already exists!");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        repositoryGeneric.add((User)param);
        
    }

    @Override
    protected void commitTransaction() throws Exception {
        repositoryGeneric.commit();
    }

    @Override
    protected void rollbackTransaction() throws Exception {
        repositoryGeneric.rollback();
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
}
