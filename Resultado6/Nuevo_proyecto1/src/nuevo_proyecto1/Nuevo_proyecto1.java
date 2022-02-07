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

/**
 *
 * @author chipi
 */
public class Nuevo_proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2022");
        
        Producto producto = new Producto (1, "Raton USB", 10, 3, 16);
        
        Venta venta = new Venta(1, 1, fecha, 1, "");
        producto.addPropertyChangeListener(venta);           
        
        Pedido pedido = new Pedido();
        pedido.setProducto(producto);
        producto.addPropertyChangeListener(pedido);

        producto.setStockactual(producto.getStockactual() - venta.getCantidad());
    }
    
}
