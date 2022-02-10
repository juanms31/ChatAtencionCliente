package com.miniChat.frames;

import javax.swing.*;
import java.awt.event.*;

public class loginW extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombre;
    private JTextField usuario;
    private JPasswordField pass;
    private JTextField motivo;

    public loginW() {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        chatServer chatServer = new chatServer("Servicio Tecnico", usuario.getText(), motivo.getText());
        chatCliente chatCliente = new chatCliente(usuario.getText(), motivo.getText());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        loginW dialog = new loginW();
        dialog.pack();
        dialog.setVisible(true);
    }
}
