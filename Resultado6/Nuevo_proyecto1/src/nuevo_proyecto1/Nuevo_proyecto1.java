/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo_proyecto1;

import mis_beans.Pedido;
import mis_beans.Producto;

/**
 *
 * @author chipi
 */
public class Nuevo_proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto producto = new Producto (1, "Raton USB", 10, 3, 16);
        Pedido pedido = new Pedido();
        pedido.setProducto(producto);
        producto.addPropertyChangeListener(pedido);
        producto.setStockactual(2);
    }
    
}
