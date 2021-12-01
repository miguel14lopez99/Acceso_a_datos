/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author b15-04m
 */
@Entity
@Table(name = "CAMPANIAS")
@NamedQueries({
    @NamedQuery(name = "Campanias.findAll", query = "SELECT c FROM Campanias c"),
    @NamedQuery(name = "Campanias.findByIdCamp", query = "SELECT c FROM Campanias c WHERE c.idCamp = :idCamp"),
    @NamedQuery(name = "Campanias.findByNombreCamp", query = "SELECT c FROM Campanias c WHERE c.nombreCamp = :nombreCamp"),
    @NamedQuery(name = "Campanias.findByDescripcion", query = "SELECT c FROM Campanias c WHERE c.descripcion = :descripcion")})
public class Campanias implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CAMP")
    private BigDecimal idCamp;
    @Column(name = "NOMBRE_CAMP")
    private String nombreCamp;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idCamp")
    private Collection<Agricultores> agricultoresCollection;

    public Campanias() {
    }

    public Campanias(BigDecimal idCamp) {
        this.idCamp = idCamp;
    }

    public BigDecimal getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(BigDecimal idCamp) {
        this.idCamp = idCamp;
    }

    public String getNombreCamp() {
        return nombreCamp;
    }

    public void setNombreCamp(String nombreCamp) {
        this.nombreCamp = nombreCamp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Agricultores> getAgricultoresCollection() {
        return agricultoresCollection;
    }

    public void setAgricultoresCollection(Collection<Agricultores> agricultoresCollection) {
        this.agricultoresCollection = agricultoresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamp != null ? idCamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campanias)) {
            return false;
        }
        Campanias other = (Campanias) object;
        if ((this.idCamp == null && other.idCamp != null) || (this.idCamp != null && !this.idCamp.equals(other.idCamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.trabajo_r3_miguel_lopez.Campanias[ idCamp=" + idCamp + " ]";
    }
    
}
