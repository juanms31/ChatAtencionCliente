package com.company.dataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class dataBase {
    private final String databaseName = "agenda";
    private final String user = "root";
    private final String pass = "";
    private final String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private Connection con = null;

    public Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            if(con != null){
                JOptionPane.showMessageDialog(null,
                        "Conexión Establecida",
                        "Estado conexión",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Error en la conexion",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
