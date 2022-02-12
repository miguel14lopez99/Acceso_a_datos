/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_proyecto1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import mis_beans.Pedido;
import mis_beans.Producto;
import mis_beans.Venta;
import mis_beans.BaseDatos;
import java.sql.Connection;

/**
 *
 * @author chipi
 */
public class Nuevo_proyecto1 {

    /**
     * @param args the command line arguments
     */
    
    public static Connection con;
    
    public static void main(String[] args) throws ParseException {
        
        Date fecha = new Date(System.currentTimeMillis());
        
        //APERTURA BBDD
        BaseDatos basedatos = new BaseDatos();
        con = basedatos.AperturaBBDD();
        
        //CREAR VENTA
        Venta venta = new Venta(basedatos.getMaxVenta()/*nroventa*/, 3/*idprod*/, fecha, 11/*cantidad*/, "");

        //BUSCAR EL PRODUCTO       
        Producto producto = basedatos.DevolverProducto(venta.getIdProducto());                       
        
        //INSERTAR LA VENTA
        basedatos.InsertarVenta(venta);        
        
        //ASIGNADO A LISTENER DE PRODUCTO
        producto.addPropertyChangeListener(venta);
        
        
        Pedido pedido = new Pedido();
        
        //ASIGNAMOS EL PRODUCTO AL PEDIDO
        pedido.setProducto(producto);
        
        //ASIGNADO A LISTENER DE PRODUCTO
        producto.addPropertyChangeListener(pedido);
        
        
        //ACTUALIZO STOCK
        producto.setStockactual(producto.getStockactual() - venta.getCantidad());        
        
        
        //SE CIERRA LA CONEXION
        basedatos.CierreBBDD(con);

    }
    
}
