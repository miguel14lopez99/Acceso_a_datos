/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_proyecto1;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author chipi
 */
public class PruebasBBDD {
    
    public static Connection con;
    
    public static void main(String[] args) {
        
        con = ConexionOracle();
        
        EjecutarSentencia(con, "INSERT INTO PRODUCTOS VALUES(1, 'Ordenador de Sobremesa', 20, 5, 1000)");
        EjecutarSentencia(con, "INSERT INTO PRODUCTOS VALUES(2, 'Ratón RGB', 20, 5, 50)");
        EjecutarSentencia(con, "INSERT INTO PRODUCTOS VALUES(3, 'Teclado mecánico', 20, 5, 100)");
        
        CerrarConexion(con);
        
    }
    
    public static Connection ConexionOracle(){ 
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/PDB18C";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "DAM3");
            propiedades.setProperty("password", "dam3");
            
            Class.forName(driver);
            
            Connection conexion = DriverManager.getConnection(urlconnection, propiedades);
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static ResultSet EjecutarSentencia(Connection conexion, String sql){
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute(sql);
            
            if (valor){
                ResultSet result = sentencia.getResultSet();
                return result;
            } else {
                int filas = sentencia.getUpdateCount();
                System.out.print("\n"+ filas +" filas afectadas.\n");
            }
 
        } catch (SQLException ex) {
            MostrarError(ex);
        }
        return null;
    }
    
    public static void MostrarError (SQLException e){       
        System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +e.getMessage());
            System.out.println ("SQL Estado: " +e.getSQLState());
            System.out.println ("Código de error: " +e.getErrorCode());
    }
    
    public static void CerrarConexion(Connection conexion){
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
