
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b15-04m
 */
public class MetadatosOracle {
    
    public static void main(String[] args) {
        
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/PDB18C";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");
            
            Class.forName(driver);
            
            Connection conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            DatabaseMetaData dbmd = conexion.getMetaData();
            
            String nombre = dbmd.getDatabaseProductName();
            String driverName = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();
            
            System.out.printf("Nombre: %s\n"
                    + "Driver: %s\n"
                    + "URL: %s\n"
                    + "Usuario: %s",
                    nombre, driverName, url, usuario);
            
            System.out.println("\n");
            
            ResultSet result = null;
            String[] tipos = {"TABLE"};
            result = dbmd.getTables(null, usuario, null, null);
            
            while(result.next()){       
                System.out.printf("%s, %s, %s, %s, %s %n",
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5));
            }
            
            System.out.println("\n");
            
            ResultSet columnas = null;
            columnas = dbmd.getColumns(null, usuario, "DEPARTAMENTOS", null);
            
            while(columnas.next()){       
                System.out.printf("%s%n",
                        columnas.getString("COLUMN_NAME"));
            }  
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MetadatosOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MetadatosOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
}
