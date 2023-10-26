/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.admin;

import java.util.ArrayList;
import server.repository.db.DbRepository;
import server.repository.db.impl.RepositoryAdmin;
import server.repository.db.impl.RepositoryGeneric;
import server.so.AbstractSO;
import zcommon.domain.Admin;

/**
 *
 * @author Korisnik
 */
public class LoginAdminSO extends AbstractSO{

    //private final DbRepository repositoryAdmin;
    private final DbRepository repositoryGeneric;

    public LoginAdminSO() {
        //this.repositoryAdmin = new RepositoryAdmin();
        this.repositoryGeneric = new RepositoryGeneric();

    }

    @Override
    protected void prerequisits(Object param) throws Exception {
        if (param == null || !(param instanceof Admin)) {
            throw new Exception("Invalid product data!");
        }
    }
    
    @Override
    protected void executeTransaction(Object param) throws Exception {
        Admin a = new Admin();
        ArrayList<Admin> listOfAllUsers = (ArrayList<Admin>) repositoryGeneric.getAll(a);
        boolean signal = false;
        for (Admin u : listOfAllUsers) {
            if (u.getUsername().equals(((Admin)param).getUsername()) && u.getPassword().equals(((Admin)param).getPassword())) {
                ((Admin)param).setName(u.getName());
                ((Admin)param).setLastName(u.getLastName());
                ((Admin)param).setAdminID(u.getAdminID());
                
                signal = true;
                break;
            }
        }
        if (!signal) {
            throw new Exception("Unknown admin!");
        }
    }


    @Override
    protected void disconnect() throws Exception {
        repositoryGeneric.disconnect();
    }
    
    
}
