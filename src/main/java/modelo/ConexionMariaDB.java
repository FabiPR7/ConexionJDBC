/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class ConexionMariaDB {
    String user = "root";
    String pwd = "sge2425";
    String nombreBD = "bd_departamento";
    String url = "jdbc:mysql://localhost/"+nombreBD;

    public ConexionMariaDB() {
    }
    
    public Connection getConecction(){
        Connection conexion = null; 
        try {
            conexion = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException ex) {
            System.out.println("ERROR EN CONEXION");
            Logger.getLogger(ConexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
    } 
    
    public void desconectar(Connection con){
        if (con !=null) {
            try {
                con.close();
                con = null;
            } catch (SQLException ex) {
                System.out.println("No se puede cerrarr la conexionb");
                Logger.getLogger(ConexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
