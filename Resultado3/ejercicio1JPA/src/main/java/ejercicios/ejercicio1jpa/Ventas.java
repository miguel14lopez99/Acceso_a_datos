/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ejercicio1jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author b15-04m
 */
@Entity
@Table(name = "VENTAS")
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByIdventa", query = "SELECT v FROM Ventas v WHERE v.idventa = :idventa"),
    @NamedQuery(name = "Ventas.findByFechaventa", query = "SELECT v FROM Ventas v WHERE v.fechaventa = :fechaventa"),
    @NamedQuery(name = "Ventas.findByCantidad", query = "SELECT v FROM Ventas v WHERE v.cantidad = :cantidad")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDVENTA")
    private BigDecimal idventa;
    @Basic(optional = false)
    @Column(name = "FECHAVENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaventa;
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Clientes idcliente;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "ID")
    @ManyToOne
    private Productos idproducto;

    public Ventas() {
    }

    public Ventas(BigDecimal idventa) {
        this.idventa = idventa;
    }

    public Ventas(BigDecimal idventa, Date fechaventa) {
        this.idventa = idventa;
        this.fechaventa = fechaventa;
    }

    public BigDecimal getIdventa() {
        return idventa;
    }

    public void setIdventa(BigDecimal idventa) {
        this.idventa = idventa;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Clientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Clientes idcliente) {
        this.idcliente = idcliente;
    }

    public Productos getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Productos idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventa != null ? idventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idventa == null && other.idventa != null) || (this.idventa != null && !this.idventa.equals(other.idventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.ejercicio1jpa.Ventas[ idventa=" + idventa + " ]";
    }
    
}
