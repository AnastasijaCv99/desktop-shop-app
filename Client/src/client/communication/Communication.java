/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.communication;

import zcommon.communication.Receiver;
import zcommon.communication.Sender;
import java.io.IOException;
import java.net.Socket;


/**
 *
 * @author Anastasija Cvetkovic
 */
public class Communication {
    private static Communication instance;
    Socket socketClient;

    Sender sender;
    Receiver receiver;
    
    
    public Communication() throws IOException {
        socketClient = new Socket("localhost", 9000);
        sender = new Sender(socketClient);
        receiver = new Receiver(socketClient);
    }
    
    public static Communication getInstance() throws IOException {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }
    
    public Receiver getReceiver() {
        return receiver;
    }

    public Sender getSender() {
        return sender;
    }

    
}
