
import java.io.File;
import java.sql.*;
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
public class MetadatosSQLite {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
            
            Connection conexion = util.ConexionSQLite();

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
            columnas = dbmd.getColumns(null, usuario, "DEPARTAMENTOS", null);
            
            while(columnas.next()){       
                System.out.printf("%s%n",
                        columnas.getString("COLUMN_NAME"));     //nombre de la columna
            } 
            
            System.out.println("\nPRIMARY KEYS:");
            
            ResultSet pk = dbmd.getPrimaryKeys(null, usuario, "DEPARTAMENTOS");
            
            while(pk.next()){
                System.out.printf("%s, %s, %s, %s, %s %n",
                        pk.getString("PK_NAME"),                //nombre de la pk
                        pk.getString("TABLE_CAT"),              //(1)
                        pk.getString("TABLE_SCHEM"),            //(2)
                        pk.getString("TABLE_NAME"),             //(3)
                        pk.getString("COLUMN_NAME"));           //(4)
            }
            
            System.out.println("\nFOREIGN KEYS: (las que apuntan)");
            
            ResultSet fkExported = dbmd.getExportedKeys(null, usuario, "DEPARTAMENTOS"); //las fk que apuntan a departamentos
            
            while(fkExported.next()){
                System.out.printf("%s, %s, %s, %s, %s %n",
                        fkExported.getString("PKTABLE_CAT"),    //(1)
                        fkExported.getString("PKTABLE_SCHEM"),  //(2)
                        fkExported.getString("PKTABLE_NAME"),   //(3)
                        fkExported.getString("PKCOLUMN_NAME"),  //(4)
                        fkExported.getString("FKTABLE_NAME"));  //de donde vienen las fk
            }
            
            System.out.println("\nFOREIGN KEYS: (las que salen)");
            
            ResultSet fkImported = dbmd.getImportedKeys(null, usuario, "EMPLEADOS"); //las fk que salen de empleados
            
            while(fkImported.next()){
                System.out.printf("%s, %s, %s, %s %n",
                        fkImported.getString("PKTABLE_CAT"),    //(1)
                        fkImported.getString("PKTABLE_SCHEM"),  //(2)
                        fkImported.getString("PKTABLE_NAME"),   //(3)
                        fkImported.getString("PKCOLUMN_NAME")); //(4)
            }

            util.CerrarConexion(conexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MetadatosSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
