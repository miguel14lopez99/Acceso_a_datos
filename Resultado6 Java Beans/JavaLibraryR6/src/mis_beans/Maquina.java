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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author chipi
 */

@Entity
@Table(name = "MAQUINAS")
@NamedQueries({
    @NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m"),
    @NamedQuery(name = "Maquina.findByIdMaquina", query = "SELECT m FROM Maquina m WHERE m.idMaquina = :idMaquina"),
    @NamedQuery(name = "Maquina.findByNombreMaquina", query = "SELECT m FROM Maquina m WHERE m.nombreMaquina = :nombreMaquina")})
public class Maquina implements Serializable, VetoableChangeListener {
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MAQUINA")
    private int idMaquina;
    @Column(name = "NOMBRE_MAQUINA")
    private String nombreMaquina;
    @Column(name = "ULTIMA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaRevision;
    @Column(name = "REV_ACTUALIZADA")
    private Boolean revisionActualizada;
//    @OneToOne
//    @JoinColumn(name = "ID_TRAB", referencedColumnName = "ID_TRAB")
//    @Column(name = "ID_TRAB")
//    private Trabajo idTrab;
    
    public Maquina() {
        
    }

    public Maquina(int idMaquina, String nombreMaquina, String ultimaRevision) {
        this.idMaquina = idMaquina;
        this.nombreMaquina = nombreMaquina;
        try {
            this.ultimaRevision = new SimpleDateFormat().parse(ultimaRevision);
        } catch (ParseException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.revisionActualizada = revisionOK();
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public Date getUltimaRevision() {
        return ultimaRevision;
    }

    public void setUltimaRevision(String ultimaRevision) {
        try {
            this.ultimaRevision = new SimpleDateFormat("dd-MM-yyyy").parse(ultimaRevision);
        } catch (ParseException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.revisionActualizada = revisionOK();
    }

    public Boolean getRevisionActualizada() {
        return revisionActualizada;
    }

//    private void setRevisionActualizada(Boolean revisionActualizada) {
//        this.revisionActualizada = revisionActualizada;
//    }

    private Boolean revisionOK() {
        boolean revisionOK = false;
        
        Date fechaValida = null;
        try {
            fechaValida = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-"+Calendar.getInstance().get(Calendar.YEAR));
        } catch (ParseException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(this.ultimaRevision.after(fechaValida)){
            revisionOK = true;
        }
        
        return revisionOK;
    }
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {       
        if(!this.revisionOK() /*&& evt.getPropertyName().equals("fechaAsignadaVeto")*/){
            throw new PropertyVetoException ("La revisión de la máquina no está actualizada", evt);   
        }
        
    }

}
