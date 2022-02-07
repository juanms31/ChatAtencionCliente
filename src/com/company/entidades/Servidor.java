package com.company.entidades;

import com.company.frames.chatW;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream DIS;
    DataOutputStream DOS;
    chatW chatW;

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(40000);
            chatW.setVisible(true);
            while (true){
                socket = serverSocket.accept();

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
