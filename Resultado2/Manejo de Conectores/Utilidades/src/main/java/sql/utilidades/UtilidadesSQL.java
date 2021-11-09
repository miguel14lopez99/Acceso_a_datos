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
    
    public void MostrarSentencia(Connection conexion, String sql){
        try {
            
            ResultSet rs = EjecutarSentencia(conexion, sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            //escribir nombres de las columnas
            for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
                System.out.printf("%10s\t|",rsmd.getColumnName(i));
            }
            System.out.println("");
            
            //escribir los datos
            while(rs.next()){
                for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
                    String tipo = rsmd.getColumnTypeName(i);
                    if(tipo.equals("VARCHAR2")){
                        System.out.printf("%10s\t|",rs.getString(i));
                    }
                    if(tipo.equals("NUMBER")){
                        System.out.printf("%10d\t|",rs.getInt(i));
                    }
                    if(tipo.equals("DATE")){
                        System.out.printf("%10s\t|",rs.getDate(i));               
                    }
                    
                }
                System.out.println("");
            }
            
        } catch (SQLException ex) {
            MostrarError(ex);
        }
    }
    
    public void MostrarError (SQLException e){       
        System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +e.getMessage());
            System.out.println ("SQL Estado: " +e.getSQLState());
            System.out.println ("Código de error: " +e.getErrorCode());
    }
    
    public boolean devuelveDatos (Connection conexion, String sql){        
        try {
            Statement sentencia = conexion.createStatement();
            return sentencia.execute(sql);
        } catch (SQLException ex) {
            MostrarError(ex);
        }
        
        return false;
    }
}
