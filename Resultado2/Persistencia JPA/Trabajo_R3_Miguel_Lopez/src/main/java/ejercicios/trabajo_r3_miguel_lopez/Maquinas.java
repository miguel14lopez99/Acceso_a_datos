/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(MaquinaID.class)
@Table(name = "MAQUINAS")
@NamedQueries({
    @NamedQuery(name = "Maquinas.findAll", query = "SELECT m FROM Maquinas m"),
    @NamedQuery(name = "Maquinas.findByIdMaquina", query = "SELECT m FROM Maquinas m WHERE m.idMaquina = :idMaquina"),
    @NamedQuery(name = "Maquinas.findByNroBastidor", query = "SELECT m FROM Maquinas m WHERE m.nroBastidor = :nroBastidor"),
    @NamedQuery(name = "Maquinas.findByUltRevision", query = "SELECT m FROM Maquinas m WHERE m.ultRevision = :ultRevision")})
public class Maquinas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MAQUINA")
    private BigDecimal idMaquina;
    @Id
    @Column(name = "NRO_BASTIDOR")
    private String nroBastidor;
    @Column(name = "ULT_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultRevision;
    @JoinColumn(name = "ID_AGRI", referencedColumnName = "ID_AGRI")
    @ManyToOne
    private Agricultores idAgri;

    public Maquinas() {
    }

    public Maquinas(BigDecimal idMaquina, String nroBastidor) {
        this.idMaquina = idMaquina;
        this.nroBastidor = nroBastidor;
    }

    public BigDecimal getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(BigDecimal idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNroBastidor() {
        return nroBastidor;
    }

    public void setNroBastidor(String nroBastidor) {
        this.nroBastidor = nroBastidor;
    }

    public Date getUltRevision() {
        return ultRevision;
    }

    public void setUltRevision(Date ultRevision) {
        this.ultRevision = ultRevision;
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
        hash += (idMaquina != null ? idMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquinas)) {
            return false;
        }
        Maquinas other = (Maquinas) object;
        if ((this.idMaquina == null && other.idMaquina != null) || (this.idMaquina != null && !this.idMaquina.equals(other.idMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.trabajo_r3_miguel_lopez.Maquinas[ idMaquina=" + idMaquina + " ]";
    }
    
}
