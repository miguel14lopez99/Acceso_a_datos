/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis_beans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author chipi
 */
public class Pedido implements Serializable, PropertyChangeListener {
    
    private int numeropedido;
    private Producto producto;
    private Date fecha;
    private int cantidad;
    
    public Pedido() {
    }

    public Pedido(int numeropedido, Producto producto, Date fecha, int cantidad) {
        this.numeropedido = numeropedido;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getNumeropedido() {
        return numeropedido;
    }

    public void setNumeropedido(int numeropedido) {
        this.numeropedido = numeropedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("stockactual")){ // solo responde a la llamada de la propiedad stockactual
            System.out.printf("Stock anterior: %d%n", evt.getOldValue());
            System.out.printf("Nuevo Stock: %d%n", evt.getNewValue());        
            System.out.printf("REALIZAR PEDIDO EN PRODUCTO: %s%n", producto.getDescripcion());
            this.setCantidad(producto.getStockactual() + 20);
        }        
    }
    
}
