/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.view.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import zcommon.domain.User;

/**
 *
 * @author Korisnik
 */
public class TableModelUsers extends AbstractTableModel{
    ArrayList<User> listOfUsers;
    String[] columns = {"Name", "Last name", "Username", "Phone number", "Address"};
    
    public TableModelUsers() {
        listOfUsers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listOfUsers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = listOfUsers.get(rowIndex);
        switch (columnIndex) {
            case 0: return u.getName();
            case 1: return u.getLastName();
            case 2: return u.getUsername();
            case 3: return u.getPhoneNumber();
            case 4: return u.getAddress();
            default: return "ret";
        }
                
                
    }

    public void fill(ArrayList<User> users) {
        /*for (User user : users) {
            listOfUsers.add(user);
        }*/
        listOfUsers = users;
        fireTableDataChanged();
    }

    public User getUser(int row) {
        return listOfUsers.get(row);
    }
    
    
    
}

