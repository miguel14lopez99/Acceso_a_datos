/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-04m
 */
public class File1SelectSQLite {
    
    public static void main(String[] args) {
        
        try {
            Class.forName("org.sqlite.JDBC");
        
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:.\\bbdd\\ejemplo.db");

            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM departamentos";

            ResultSet result = sentencia.executeQuery(sql);
            while(result.next()){       
                System.out.printf("%d, %s, %s %n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));

            }

            result.close();
            conexion.close();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File1SelectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(File1SelectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
