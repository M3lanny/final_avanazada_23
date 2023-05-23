/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/bd_ventas";
    String user="root";
    String pass="";
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");////////// 
            con=DriverManager.getConnection(url, user, pass);
        }catch (ClassNotFoundException ex) {
            System.out.println("Error en driver bd " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error en conexion bd " + ex.getMessage());
        }
        return con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}
