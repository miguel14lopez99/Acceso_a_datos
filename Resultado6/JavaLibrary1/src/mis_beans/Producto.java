/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis_beans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author chipi
 */
public class Producto implements Serializable {
    
    private String descripcion;
    private int idproducto;
    private int stockactual;
    private int stockminimo;
    private float pvp;    
    
    private BaseDatos basedatos;
    
    private PropertyChangeSupport propertySupport;
    
    public Producto() {
        propertySupport = new PropertyChangeSupport(this);
        
        basedatos = new BaseDatos();
    }
    
    public Producto(int idproducto, String descripcion, int stockactual, int stockminimo, float pvp) {
        propertySupport = new PropertyChangeSupport(this);
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.stockactual = stockactual;
        this.stockminimo = stockminimo;
        this.pvp = pvp;
                
        basedatos = new BaseDatos();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int valorNuevo) {
        int valorAnterior = this.stockactual;
        this.stockactual = valorNuevo;

        if (this.stockactual < getStockminimo()) //hay que realizar pedido
        {
            propertySupport.firePropertyChange("stockactual", valorAnterior, this.stockactual);
            //dejamos el stock anterior
            this.stockactual = valorAnterior;
        } else {
            basedatos.ActualizarStock(this);
        }
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
