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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author b15-04m
 */
@Entity
@Table(name = "AGRICULTORES")
@NamedQueries({
    @NamedQuery(name = "Agricultores.findAll", query = "SELECT a FROM Agricultores a"),
    @NamedQuery(name = "Agricultores.findByIdAgri", query = "SELECT a FROM Agricultores a WHERE a.idAgri = :idAgri"),
    @NamedQuery(name = "Agricultores.findByNombreAgri", query = "SELECT a FROM Agricultores a WHERE a.nombreAgri = :nombreAgri"),
    @NamedQuery(name = "Agricultores.findByTelefono", query = "SELECT a FROM Agricultores a WHERE a.telefono = :telefono")})
public class Agricultores implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AGRI")
    private BigDecimal idAgri;
    @Column(name = "NOMBRE_AGRI")
    private String nombreAgri;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @JoinColumn(name = "ID_CAMP", referencedColumnName = "ID_CAMP")
    @ManyToOne
    private Campanias idCamp;
    @OneToMany(mappedBy = "idAgri")
    private Collection<Trabajos> trabajosCollection;

    public Agricultores() {
    }

    public Agricultores(BigDecimal idAgri) {
        this.idAgri = idAgri;
    }

    public BigDecimal getIdAgri() {
        return idAgri;
    }

    public void setIdAgri(BigDecimal idAgri) {
        this.idAgri = idAgri;
    }

    public String getNombreAgri() {
        return nombreAgri;
    }

    public void setNombreAgri(String nombreAgri) {
        this.nombreAgri = nombreAgri;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Campanias getIdCamp() {
        return idCamp;
    }

    public void setIdCamp(Campanias idCamp) {
        this.idCamp = idCamp;
    }

    public Collection<Trabajos> getTrabajosCollection() {
        return trabajosCollection;
    }

    public void setTrabajosCollection(Collection<Trabajos> trabajosCollection) {
        this.trabajosCollection = trabajosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgri != null ? idAgri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agricultores)) {
            return false;
        }
        Agricultores other = (Agricultores) object;
        if ((this.idAgri == null && other.idAgri != null) || (this.idAgri != null && !this.idAgri.equals(other.idAgri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.trabajo_r3_miguel_lopez.Agricultores[ idAgri=" + idAgri + " ]";
    }
    
}
