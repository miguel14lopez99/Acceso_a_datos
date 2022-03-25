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
    @NamedQuery(name = "Trabajo.findAll", query = "SELECT t FROM Trabajo t"),
    @NamedQuery(name = "Trabajo.findByIdTrab", query = "SELECT t FROM Trabajo t WHERE t.idTrab = :idTrab"),
    @NamedQuery(name = "Trabajo.findByNombreTrabajador", query = "SELECT t FROM Trabajo t WHERE t.nombreTrabajador = :nombreTrabajador"),
    @NamedQuery(name = "Trabajo.findByFechaTrabajo", query = "SELECT t FROM Trabajo t WHERE t.fechaTrabajo = :fechaTrabajo")})
public class Trabajo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TRAB")
    private int idTrab;
    @Column(name = "NOMBRE_TRABAJADOR")
    private String nombreTrabajador;
    @Column(name = "FECHA_TRABAJO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTrabajo;
    @ManyToOne
    @JoinColumn(name = "ID_AGRI", referencedColumnName = "ID_AGRI") 
    @Column(name = "ID_AGRI")
    private Agricultor idAgri;
    @OneToOne
    @JoinColumn(name = "ID_MAQUINA", referencedColumnName = "ID_MAQUINA")
    @Column(name = "ID_MAQUINA")
    private Maquina idMaquina;
    
    private transient PropertyChangeSupport propertySupport;
    private transient VetoableChangeSupport vetoableSupport;
    
    public Trabajo() {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }
    
    public Trabajo(int idTrab, String nombreTrabajador, Date fechaTrabajo, Agricultor idAgri, Maquina idMaquina){
        this.idTrab = idTrab;
        this.nombreTrabajador = nombreTrabajador;
        this.fechaTrabajo = fechaTrabajo;
        this.idMaquina = idMaquina;
        
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }

    public int getIdTrab() {
        return idTrab;
    }

    public void setIdTrab(int idTrab) {
        this.idTrab = idTrab;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public Date getFechaTrabajo() {
        return fechaTrabajo;
    }

    public void setFechaTrabajo(String nuevafechaTrabajo) throws PropertyVetoException {       
        Date fechaAnterior = this.fechaTrabajo;
        
        try{                        
            this.fechaTrabajo = new SimpleDateFormat("dd-MM-yyyy").parse(nuevafechaTrabajo);

            vetoableSupport.fireVetoableChange("fechaAsignadaVeto", fechaAnterior, this.fechaTrabajo);
            
        } catch (PropertyVetoException pve){
            //volver a dejar la fecha a null
            this.fechaTrabajo = fechaAnterior;
            throw new PropertyVetoException(pve.getMessage(),pve.getPropertyChangeEvent());
            
        } catch (ParseException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Agricultor getIdAgri() {
        return idAgri;
    }

    public void setIdAgri(Agricultor idAgri) {
        this.idAgri = idAgri;
    }

    public Maquina getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquina idMaquina) {
        this.idMaquina = idMaquina;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public void addVetoableChangeListener (VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(listener);
    }
    
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Trabajo other = (Trabajo) obj;
        if (this.idTrab != other.idTrab) {
            return false;
        }
        return true;
    }
    
    
    
}
