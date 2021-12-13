/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author b15-04m
 */

public class MaquinaID implements Serializable {
    private BigDecimal idMaquina;
    private String nroBastidor;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idMaquina);
        hash = 37 * hash + Objects.hashCode(this.nroBastidor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaquinaID other = (MaquinaID) obj;
        if (!Objects.equals(this.nroBastidor, other.nroBastidor)) {
            return false;
        }
        if (!Objects.equals(this.idMaquina, other.idMaquina)) {
            return false;
        }
        return true;
    }
 
}
