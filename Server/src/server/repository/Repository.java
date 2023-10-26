/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package server.repository;

import java.util.List;

/**
 *
 * @author Anastasija Cvetkovic
 */
public interface Repository<T, K, S> {
    
    //t = class
    //k = search condition
    
    List<T> getAll(T table) throws Exception;

    List<T> getAllById(T table) throws Exception;
    
    List<T> getAllByIdMoreSQL(T table, K typeK) throws Exception;
    
    List<T> getAllById(T table, K typeK) throws Exception;
    
    void add(T table) throws Exception;

    void edit(T table) throws Exception;
    
    void editSmt(T table, K typeK) throws Exception;

    void editSmtElse(T table, K typeK) throws Exception;
    
    void delete(T table) throws Exception;
    
    int getCountOf(T table, S typeS) throws Exception;
}
