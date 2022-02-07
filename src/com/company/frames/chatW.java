package com.company.frames;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Calendar;

public class chatW extends JFrame{
    private JPanel panelPrincipal;
    private JTextArea mensaje;
    private JButton enviarButton;
    private JTextArea chat;
    private JLabel nombreEntidad;
    private String usuario;
    private String motivo;

    public chatW() {
        initWindow();
        nombreEntidad.setText("At. Cliente");
        chat.setForeground(Color.blue);
        chat.append(usuario + "[" + LocalDateTime.now()  + "]" + ": " + motivo);
        add(panelPrincipal);
        setVisible(true);
    }

    public chatW(String usuario, String motivo) {
        initWindow();
        this.usuario = usuario;
        this.motivo = motivo;
        nombreEntidad.setText(usuario);
        chat.setForeground(Color.blue);
        chat.append(usuario + "[" + LocalDateTime.now()  + "]" + ": " + motivo);
        add(panelPrincipal);
        setVisible(true);
    }

    public void initWindow(){
        setSize(400,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Chat Atencion Al Cliente");

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
}
