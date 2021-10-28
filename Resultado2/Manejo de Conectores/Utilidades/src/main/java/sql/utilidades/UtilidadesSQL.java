/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql.utilidades;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chipi
 */
public class UtilidadesSQL {
    
    public Connection ConexionOracle(){ 
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/PDB18C";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");
            
            Class.forName(driver);
            
            Connection conexion = DriverManager.getConnection(urlconnection, propiedades);
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilidadesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Connection ConexionSQLite(){
        try {
            Class.forName("org.sqlite.JDBC");
            
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:..\\bbdd\\ejemplo.db"); //salgo al dir padre y entro en bbdd
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UtilidadesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void CerrarConexion(Connection conexion){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet EjecutarSentencia(Connection conexion, String sql){
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet result = sentencia.executeQuery(sql);
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
