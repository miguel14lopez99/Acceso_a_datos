/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chipi
 */
public class File1SelectOracle {
    
    public static void main(String[] args) {
        
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/PDB18C";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");
            
            Class.forName(driver);
            
            Connection conexion = DriverManager.getConnection(urlconnection, propiedades);

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
            Logger.getLogger(File1SelectOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(File1SelectOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
