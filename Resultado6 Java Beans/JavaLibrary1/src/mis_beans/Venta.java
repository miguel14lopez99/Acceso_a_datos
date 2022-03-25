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
public class Venta implements Serializable, PropertyChangeListener {
    
    private int numeroVenta;
    private int idProducto;
    private Date fecha;
    private int cantidad;
    private String observaciones;
    
    private BaseDatos basedatos;
    
    public Venta() {
        this.basedatos = new BaseDatos();
    }

    public Venta(int numeroVenta, int idProducto, Date fecha, int cantidad, String observaciones) {
        this.numeroVenta = numeroVenta;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        
        this.basedatos = new BaseDatos();
    }

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("stockactual")){ // solo responde a la llamada de la propiedad stockactual
            //indicar en la base de datos que la venta no se puede hacer por falta de unidades
            this.observaciones = "La venta no se puede hacer por falta de unidades.";
            basedatos.ActualizarObservaciones(this);
            System.out.println(observaciones);
        } 
    }
    
}
