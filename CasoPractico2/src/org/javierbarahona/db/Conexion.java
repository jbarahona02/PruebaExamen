package org.javierbarahona.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "root";
    private static final String password = "admin";
    private static final String url = "jdbc:mysql://localhost:3306/DBCasoPractico2_2018064";

    public Conexion(){
        conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,password);
            if(conn != null){
                System.out.println("Conexiòn establecida");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    
    public static Connection getConexion(){
        return conn;
    }
    
}
