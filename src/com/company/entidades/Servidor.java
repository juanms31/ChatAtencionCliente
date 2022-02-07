package com.company.entidades;

import com.company.frames.chatCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream DIS;
    DataOutputStream DOS;
    chatCliente chatCliente;

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(40000);
            chatCliente.setVisible(true);
            while (true){
                socket = serverSocket.accept();

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
