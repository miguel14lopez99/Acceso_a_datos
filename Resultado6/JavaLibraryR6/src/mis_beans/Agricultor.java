/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis_beans;

import java.beans.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author chipi
 */

@Entity
@Table(name = "AGRICULTORES")
@NamedQueries({
    @NamedQuery(name = "Agricultor.findAll", query = "SELECT a FROM Agricultor a"),
    @NamedQuery(name = "Agricultor.findByIdAgri", query = "SELECT a FROM Agricultor a WHERE a.idAgri = :idAgri"),
    @NamedQuery(name = "Agricultor.findByNombreAgri", query = "SELECT a FROM Agricultor a WHERE a.nombreAgri = :nombreAgri"),
    @NamedQuery(name = "Agricultor.findByFechaDisponible", query = "SELECT a FROM Agricultor a WHERE a.fechaDisponible = :fechaDisponible")})
public class Agricultor implements Serializable, VetoableChangeListener {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AGRI")
    private int idAgri;
    @Column(name = "NOMBRE_AGRI")
    private String nombreAgri;
    @Column(name = "FECHA_DISP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDisponible;
    @JoinColumn(name = "ID_AGRI", referencedColumnName = "ID_AGRI")
    @OneToMany(mappedBy = "idAgri")
    private List<Trabajo> trabajosCollection;
    
    public Agricultor() {
        trabajosCollection = new ArrayList<Trabajo>();
    }

    public Agricultor(int idAgri, String nombreAgri, Date fechaDisponible, Trabajo idTrab) {
        this.idAgri = idAgri;
        this.nombreAgri = nombreAgri;
        this.fechaDisponible = fechaDisponible;
        this.trabajosCollection = new ArrayList<Trabajo>();
    }

    public int getIdAgri() {
        return idAgri;
    }

    public void setIdAgri(int idAgri) {
        this.idAgri = idAgri;
    }

    public String getNombreAgri() {
        return nombreAgri;
    }

    public void setNombreAgri(String nombreAgri) {
        this.nombreAgri = nombreAgri;
    }

    public Date getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(String fechaDisponible) {
        try {
            this.fechaDisponible = new SimpleDateFormat("dd-MM-yyyy").parse(fechaDisponible);
        } catch (ParseException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Trabajo> getTrabajosCollection() {
        return trabajosCollection;
    }

    public void setTrabajosCollection(List<Trabajo> trabajosCollection) {
        this.trabajosCollection = trabajosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Agricultor other = (Agricultor) obj;
        if (this.idAgri != other.idAgri) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agricultor{" + "idAgri=" + idAgri + ", nombreAgri=" + nombreAgri + '}';
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        //si la fecha disponible del agricultor es mayor a la que me da trabajadores se veta el cambio 
        //if(evt.getPropertyName().equals("fechaAsignadaVeto")){
        if(!trabajosCollection.isEmpty()){
            Trabajo ultimoTrabajo = trabajosCollection.get(trabajosCollection.size()-1);
            
            BaseDatos.AsignarTrabajo(this, ultimoTrabajo);
            if (ultimoTrabajo.getFechaTrabajo().before(this.fechaDisponible)) {
                throw new PropertyVetoException ("El agricultor no está disponible en la fecha del trabajo", evt);
            }
        }                 
        //} 
    }
    
}
