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
public class Venta implements Serializable, PropertyChangeListener {
    
    public Venta() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("stockactual")){ // solo responde a la llamada de la propiedad stockactual
            //indicar en la base de datos que la venta no se puede hacer por falta de unidades
        } 
    }
    
}
