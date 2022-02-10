package com.company.frames;

import com.company.entidades.mensaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class chatServer extends JFrame implements Runnable, ActionListener, KeyListener {
    private JPanel panelPrincipal;
    private JTextArea mensaje;
    private JButton enviarButton;
    private JEditorPane chat;
    private JLabel nombreEntidad;
    private String usuario;
    private String motivo;
    private String cliente;
    private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private mensaje EMensaje;

    public chatServer(String usuario, String cliente, String motivo) {
        initWindow();
        this.usuario = usuario;
        this.motivo = motivo;
        this.cliente =  cliente;
        nombreEntidad.setText("At. Cliente");
        setLocation(pantalla.width/4,pantalla.height/4);
        chat.setContentType("text/html");
        chat.setText("<p> " +
                "<span style=\"color: #3366ff;\">" + usuario + "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))  + "]" + ": " + motivo + "</br>" +
                "</p>");
        add(panelPrincipal);
        Thread thread = new Thread(this);
        thread.start();
        setVisible(true);
    }

    public void initWindow(){
        setSize(400,400);
        setAlwaysOnTop(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Chat Atencion Al Cliente");
        enviarButton.addActionListener(this);
        mensaje.addKeyListener(this);


    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextArea getMensaje() {
        return mensaje;
    }

    public void setMensaje(JTextArea mensaje) {
        this.mensaje = mensaje;
    }

    public JButton getEnviarButton() {
        return enviarButton;
    }

    public void setEnviarButton(JButton enviarButton) {
        this.enviarButton = enviarButton;
    }

    @Override
    public void run() {
        final int PORT = 9995;
        ServerSocket ss;
        Socket so;
        try {
            ss = new ServerSocket(PORT);

            while(true){
                so = ss.accept();
                InputStream is = so.getInputStream();
                DataInputStream DIS = new DataInputStream(is);
                String mensaje = DIS.readUTF();
                String chatRecord = chat.getText();
                String newChat = chatRecord.concat("<p> " +
                        "<span style=\"color: #ff0000;\">" + mensaje + "</br>" +
                        "</p>");
                // FIXME: 10/02/2022 ARRAYLIST DE MENSAJES QUE SE INTRODUZCAN EN MEDIO DEL HTML Y QUE EL SETTEXT SIMPLEMENTE LO RECORRA EN EJECUCION PARA AñADIRLO
                chat.setText(newChat);
                DIS.close();
                so.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMesagge(){
        Socket so;
        try {
            so =  new Socket("localhost", 9996);

            OutputStream os = so.getOutputStream();
            DataOutputStream DOS = new DataOutputStream(os);
            EMensaje = new mensaje(usuario,LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), mensaje.getText().replaceAll("\n",""),"\"color: #3366ff;\">");
            String chatRecord = chat.getText();
            String newChat = chatRecord.concat("<p> " +
                    "<span style=\"color: #3366ff;\">" + EMensaje.toString() + "</br>" +
                    "</p>");
            chat.setText(newChat);
            // FIXME: 10/02/2022 ARRAYLIST DE MENSAJES QUE SE INTRODUZCAN EN MEDIO DEL HTML Y QUE EL SETTEXT SIMPLEMENTE LO RECORRA EN EJECUCION PARA AñADIRLO
            mensaje.setText("");
            DOS.writeUTF(EMensaje.toString());

            DOS.close();
            so.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        writeMesagge();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            writeMesagge();
        }

    }
}
