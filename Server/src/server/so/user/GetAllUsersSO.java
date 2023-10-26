/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.user;

import java.util.ArrayList;
import java.util.List;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryUser;
import server.so.AbstractSO;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class GetAllUsersSO extends AbstractSO{

    //private final DbRepository repositoryUser;
    private final DbRepository repositoryGeneric;

    public GetAllUsersSO() {
        //this.repositoryUser = new RepositoryUser();
        this.repositoryGeneric = new RepositoryGeneric();
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        User u = new User();
        ArrayList<User> listUsers = (ArrayList<User>) repositoryGeneric.getAll(u);
        
        for (User user : listUsers) {
            ((ArrayList<User>)param).add(user);
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
}
