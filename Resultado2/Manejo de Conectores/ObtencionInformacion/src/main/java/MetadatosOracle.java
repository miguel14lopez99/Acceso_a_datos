
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

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
            UtilidadesSQL util = new UtilidadesSQL();
            
            Connection conexion = util.ConexionOracle();
            
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
            
            System.out.println("\n\nTABLAS:");
            
            ResultSet result = null;
            String[] tipos = {"TABLE"};
            result = dbmd.getTables(null, usuario, null, tipos);
            
            while(result.next()){
                System.out.printf("%s, %s, %s, %s, %s %n",
                        result.getString("TABLE_CAT"),          //(1)
                        result.getString("TABLE_SCHEM"),        //(2)
                        result.getString("TABLE_NAME"),         //(3)
                        result.getString("TABLE_TYPE"),         //(4)
                        result.getString("REMARKS"));           //(5)
            }    
            
            System.out.println("\nCOLUMNAS:");
            
            ResultSet columnas = null;
            columnas = dbmd.getColumns(null, usuario, "DEPARTAMENTOS", null); //El nombre tiene que estar en mayusculas
            
            while(columnas.next()){
                System.out.printf("%s%n",
                        columnas.getString("COLUMN_NAME"));     //nombre de la columna
            } 
            
            util.CerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(MetadatosOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
}
