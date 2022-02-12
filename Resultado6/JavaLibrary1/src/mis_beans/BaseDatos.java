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
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chipi
 */
public class BaseDatos {
    
    public Connection AperturaBBDD(){
        
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
    
    public void InsertarProducto(Producto pr){
        Connection conexion = AperturaBBDD();
        
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("INSERT INTO PRODUCTOS VALUES("+ pr.getIdproducto() +",'"+ pr.getDescripcion() +"',"+ pr.getStockactual() +","+ pr.getStockminimo() +","+ pr.getPvp() +")");
            
            int filas = sentencia.getUpdateCount();
            //System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error InsertarProducto:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
    }
    
    public void InsertarPedido(Pedido pe){
        Connection conexion = AperturaBBDD();
        
        try {
            Statement sentencia = conexion.createStatement();
            SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
            String sFecha = formato.format(pe.getFecha());
            
            boolean valor = sentencia.execute("INSERT INTO PEDIDOS VALUES("+ pe.getNumeropedido()+",'"+ pe.getProducto().getIdproducto()+"','"+ sFecha +"',"+ pe.getCantidad()+")");
            
            int filas = sentencia.getUpdateCount();
            //System.out.print("\n"+ filas +" filas afectadaaaaas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error InsertarPedido:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
    }
    
    public void InsertarVenta(Venta v){ //NO INSERTA LA VENTA
        Connection conexion = AperturaBBDD();
        
        try {
            Statement sentencia = conexion.createStatement();
            SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
            String sFecha = formato.format(v.getFecha());
              
            boolean valor = sentencia.execute("INSERT INTO VENTAS VALUES("+ v.getNumeroVenta()+","+ v.getIdProducto()+",'"+ sFecha +"',"+ v.getCantidad() +",'"+ v.getObservaciones() +"')");
            
            int filas = sentencia.getUpdateCount();
            //System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error InsertarVenta:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
    }
    
    public void ActualizarObservaciones(Venta v){ //NO INSERTA LA VENTA
        Connection conexion = AperturaBBDD();
        
        try {
            Statement sentencia = conexion.createStatement();
              
            boolean valor = sentencia.execute("UPDATE VENTAS SET OBSERVACIONES = '"+ v.getObservaciones() +"' WHERE NRO_VENTA = "+ v.getNumeroVenta());
            
            int filas = sentencia.getUpdateCount();
            //System.out.print("\n"+ filas +" filas afectadas.\n");

        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error ActualizarVenta:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
    }
    
    public Producto DevolverProducto(int idProd){
        Connection conexion = AperturaBBDD();
        
        Producto producto = new Producto();
        producto.setIdproducto(idProd);
        
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("SELECT DESCRIPCION, STOCKACTUAL, STOCKMINIMO, PVP FROM PRODUCTOS WHERE IDPRODUCTO = "+ idProd);
            
            if (valor){
                ResultSet result = sentencia.getResultSet();
                
                //RECORRER RESULTSET Y PASARSELO A PRODUCTO
                while(result.next()){
                    producto.setDescripcion(result.getString("DESCRIPCION"));
                    producto.setStockactual(result.getInt("STOCKACTUAL"));
                    producto.setStockminimo(result.getInt("STOCKMINIMO"));
                    producto.setPvp(result.getFloat("PVP"));
                }
                
            } else {
                int filas = sentencia.getUpdateCount();
                //System.out.print("\n"+ filas +" filas afectadas.\n");
            }
 
        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error DevolverProducto:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
        return producto;
    }
    
    public void ActualizarStock(Producto pr){
        Connection conexion = AperturaBBDD();
        
        try {

            Statement sentencia = conexion.createStatement();
            boolean valor = sentencia.execute("UPDATE PRODUCTOS SET STOCKACTUAL = "+ pr.getStockactual() +" WHERE IDPRODUCTO = " + pr.getIdproducto());

            int filas = sentencia.getUpdateCount();
            //System.out.print("\n"+ filas +" filas afectadas.\n");
        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error ActualizarStock:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }    

        CierreBBDD(conexion);
    }
    
    public int getMaxVenta(){
        Connection conexion = AperturaBBDD();
        int max = 1;
        
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("SELECT COUNT(*) FROM VENTAS");
            
            if (valor){
                ResultSet result = sentencia.getResultSet();
                
                //RECORRER RESULTSET Y PASARSELO A PRODUCTO
                while(result.next()){
                    max = result.getInt(1) + 1;
                }
                
            } else {
                int filas = sentencia.getUpdateCount();
                //System.out.print("\n"+ filas +" filas afectadas.\n");
            }
 
        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error getMaxVentas:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
        return max;
    }
    
    public int getMaxPedido(){
        Connection conexion = AperturaBBDD();
        int max = 1;
        
        try {
            Statement sentencia = conexion.createStatement();     
            boolean valor = sentencia.execute("SELECT COUNT(*) FROM PEDIDOS");
            
            if (valor){
                ResultSet result = sentencia.getResultSet();
                
                //RECORRER RESULTSET Y PASARSELO A PRODUCTO
                while(result.next()){
                    max = result.getInt(1) + 1;
                }
                
            } else {
                int filas = sentencia.getUpdateCount();
                //System.out.print("\n"+ filas +" filas afectadas.\n");
            }
 
        } catch (SQLException ex) {
            System.out.println ("Ha ocurrido un error getMaxPedido:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        }
        
        CierreBBDD(conexion);
        return max;
    }
    
}
