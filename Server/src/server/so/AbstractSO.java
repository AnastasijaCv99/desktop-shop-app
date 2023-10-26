/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so;

/**
 *
 * @author Korisnik
 */
public abstract class AbstractSO {
    
    public void execute(Object param) throws Exception {
        try {
            prerequisits(param);
            executeTransaction(param);
            commitTransaction();
            System.out.println("Uspesno izvrsena operacija");
        } catch (Exception ex) {
            rollbackTransaction();
            System.out.println("Neuspesno izvrsena operacija");
            throw ex;
        } finally {
            disconnect();
        }
    }

    protected void commitTransaction() throws Exception {
    }

    protected void rollbackTransaction() throws Exception {
    }

    protected abstract void executeTransaction(Object param) throws Exception;

    protected void prerequisits(Object param) throws Exception{
    }

    protected void disconnect() throws Exception {
    }


}
