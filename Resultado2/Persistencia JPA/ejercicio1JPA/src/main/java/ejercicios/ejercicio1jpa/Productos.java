/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ejercicio1jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "PRODUCTOS")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findById", query = "SELECT p FROM Productos p WHERE p.id = :id"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByStockanual", query = "SELECT p FROM Productos p WHERE p.stockanual = :stockanual"),
    @NamedQuery(name = "Productos.findByStockminimo", query = "SELECT p FROM Productos p WHERE p.stockminimo = :stockminimo"),
    @NamedQuery(name = "Productos.findByPvp", query = "SELECT p FROM Productos p WHERE p.pvp = :pvp")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "STOCKANUAL")
    private BigInteger stockanual;
    @Column(name = "STOCKMINIMO")
    private BigInteger stockminimo;
    @Column(name = "PVP")
    private BigInteger pvp;
    @OneToMany(mappedBy = "idproducto")
    private Collection<Ventas> ventasCollection;

    public Productos() {
    }

    public Productos(BigDecimal id) {
        this.id = id;
    }

    public Productos(BigDecimal id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getStockanual() {
        return stockanual;
    }

    public void setStockanual(BigInteger stockanual) {
        this.stockanual = stockanual;
    }

    public BigInteger getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(BigInteger stockminimo) {
        this.stockminimo = stockminimo;
    }

    public BigInteger getPvp() {
        return pvp;
    }

    public void setPvp(BigInteger pvp) {
        this.pvp = pvp;
    }

    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.ejercicio1jpa.Productos[ id=" + id + " ]";
    }
    
}
