/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author b15-04m
 */
@Entity
@Table(name = "TRABAJOS")
@NamedQueries({
    @NamedQuery(name = "Trabajos.findAll", query = "SELECT t FROM Trabajos t"),
    @NamedQuery(name = "Trabajos.findByIdTrab", query = "SELECT t FROM Trabajos t WHERE t.idTrab = :idTrab"),
    @NamedQuery(name = "Trabajos.findByNombreTrab", query = "SELECT t FROM Trabajos t WHERE t.nombreTrab = :nombreTrab"),
    @NamedQuery(name = "Trabajos.findByMaquina", query = "SELECT t FROM Trabajos t WHERE t.maquina = :maquina")})
public class Trabajos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TRAB")
    private BigDecimal idTrab;
    @Column(name = "NOMBRE_TRAB")
    private String nombreTrab;
    @Column(name = "MAQUINA")
    private String maquina;
    @JoinColumn(name = "ID_AGRI", referencedColumnName = "ID_AGRI")
    @ManyToOne
    private Agricultores idAgri;

    public Trabajos() {
    }

    public Trabajos(BigDecimal idTrab) {
        this.idTrab = idTrab;
    }

    public BigDecimal getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(BigDecimal idTrab) {
        this.idTrab = idTrab;
    }

    public String getNombreTrab() {
        return nombreTrab;
    }

    public void setNombreTrab(String nombreTrab) {
        this.nombreTrab = nombreTrab;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public Agricultores getIdAgri() {
        return idAgri;
    }

    public void setIdAgri(Agricultores idAgri) {
        this.idAgri = idAgri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrab != null ? idTrab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajos)) {
            return false;
        }
        Trabajos other = (Trabajos) object;
        if ((this.idTrab == null && other.idTrab != null) || (this.idTrab != null && !this.idTrab.equals(other.idTrab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.trabajo_r3_miguel_lopez.Trabajos[ idTrab=" + idTrab + " ]";
    }
    
}
