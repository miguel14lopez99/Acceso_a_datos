/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis_beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author chipi
 */
public class BaseDatos {
    
    public Connection AperturaBDD(){
        
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
    
    public void CierreBBDD(Connection conexion){
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void InsertarProducto(Connection conexion, Producto pr){
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("INSERT INTO PRODUCTOS VALUES("+ pr.getIdproducto() +",'"+ pr.getDescripcion() +"',"+ p.getStockactual() +","+ p.getStockminimo() +","+ p.getPvp() +")");
            
            int filas = sentencia.getUpdateCount();
            System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
    }
    
    public void InsertarPedido(Connection conexion, Pedido pe){
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("INSERT INTO PEDIDOS VALUES("+ pe.getIdproducto() +",'"+ pe.getDescripcion() +"',"+ pe.getStockactual() +","+ pe.getStockminimo() +")");
            
            int filas = sentencia.getUpdateCount();
            System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
    }
    
    public void InsertarVenta(Connection conexion, Venta v){
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("INSERT INTO PRODUCTOS VALUES("+ p.getIdproducto() +",'"+ p.getDescripcion() +"',"+ p.getStockactual() +","+ p.getStockminimo() +","+ p.getPvp() +")");
            
            int filas = sentencia.getUpdateCount();
            System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
    }
    
    public void ActualizarVenta(Connection conexion){
        
    }
    
}
