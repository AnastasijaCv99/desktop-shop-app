/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.user;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryGeneric;
import server.repository.db.impl.RepositoryUser;
import server.so.AbstractSO;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class LoginUserSO extends AbstractSO{
    
    //private final DbRepository repositoryUser;
    private final DbRepository repositoryGeneric;
    
    public LoginUserSO() {
        //this.repositoryUser = new RepositoryUser();
        this.repositoryGeneric = new RepositoryGeneric();

    }
    
    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid product data!");
        }
    }
    
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        //na param ce da se setuje odgovor iz baze
        User user = new User();
        ArrayList<User> listOfAllUsers = (ArrayList<User>) repositoryGeneric.getAll(user);
        
        boolean signal = false;
        for (User u : listOfAllUsers) {
            if (u.getUsername().equals(((User)param).getUsername()) && u.getPassword().equals(((User)param).getPassword())) {
                ((User)param).setName(u.getName());
                ((User)param).setLastName(u.getLastName());
                ((User)param).setPhoneNumber(u.getPhoneNumber());
                ((User)param).setAddress(u.getAddress());
                ((User)param).setUserID(u.getUserID());
                
                signal = true;
                break;
                
            }
        }
        if (!signal) {
            throw new Exception("Unknown user!");
        }
    }

    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
    
    
}
