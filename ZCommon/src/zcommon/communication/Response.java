/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zcommon.communication;

import java.io.Serializable;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class Response implements Serializable{
    private Object response;
    private Exception exception;
    private boolean signal;
    private Operation operation;
    
    public Response() {
    }

    public Response(Object response, Exception exception) {
        this.response = response;
        this.exception = exception;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public boolean isSignal() {
        return signal;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
    
    
}
