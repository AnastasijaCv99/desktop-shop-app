/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anastasija Cvetkovic
 */
public class ServerThread extends Thread{
    
    static ArrayList<HandlingClientRequests> clientThreads = new ArrayList<>();
    private ServerSocket ss;
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server is waiting...");
            while(!ss.isClosed()) {
                Socket socket = ss.accept();
                System.out.println("Client has connected!");
                handle(socket);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handle(Socket socket) {
        HandlingClientRequests threadClient = new HandlingClientRequests(socket);
        threadClient.start();
        threadClient.setServerThread(this);
        clientThreads.add(threadClient);
    }

    public ServerSocket getSs() {
        return ss;
    }

    public ArrayList<HandlingClientRequests> getClientThreads() {
        return clientThreads;
    }
    
    
    
}
