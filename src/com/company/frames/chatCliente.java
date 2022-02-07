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

import static java.lang.Thread.sleep;

public class chatCliente extends JFrame implements Runnable, ActionListener, KeyListener {
    private JPanel panelPrincipal;
    private JTextArea mensaje;
    private JButton enviarButton;
    private JTextArea chat;
    private JLabel nombreEntidad;
    private String usuario;
    private String motivo;
    private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    private mensaje EMensaje;

    public chatCliente(String usuario, String motivo) {
        initWindow();
        this.usuario = usuario;
        this.motivo = motivo;
        nombreEntidad.setText(usuario);
        setLocation(pantalla.width/2,pantalla.height/4);
        chat.setForeground(Color.red);
        chat.append(usuario + "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))  + "]" + ": " + motivo + "\n");
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

    public JTextArea getChat() {
        return chat;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    //Recibir
    @Override
    public void run() {
        final int PORT = 9996;
        ServerSocket ss;
        Socket so;
        try {
            ss = new ServerSocket(PORT);

            while(true){
                so = ss.accept();
                InputStream is = so.getInputStream();
                DataInputStream DIS = new DataInputStream(is);
                String mensaje = DIS.readUTF();
                chat.append(mensaje + "\n");
                DIS.close();
                so.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMesagge() {
        Socket so;
        try {
            so = new Socket("localhost", 9995);

            OutputStream os = so.getOutputStream();
            DataOutputStream DOS = new DataOutputStream(os);
            EMensaje = new mensaje(usuario, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), mensaje.getText().replaceAll("\n",""));
            chat.append(EMensaje.toString() + "\n");
            mensaje.setText("");
            DOS.writeUTF(EMensaje.toString());

            DOS.close();
            so.close();

        } catch (Exception ex) {
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
