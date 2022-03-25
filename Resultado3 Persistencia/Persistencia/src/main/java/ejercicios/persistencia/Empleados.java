/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.persistencia;

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
@Table(name = "EMPLEADOS")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByEmpNo", query = "SELECT e FROM Empleados e WHERE e.empNo = :empNo"),
    @NamedQuery(name = "Empleados.findByApellido", query = "SELECT e FROM Empleados e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleados.findByOficio", query = "SELECT e FROM Empleados e WHERE e.oficio = :oficio"),
    @NamedQuery(name = "Empleados.findByDir", query = "SELECT e FROM Empleados e WHERE e.dir = :dir"),
    @NamedQuery(name = "Empleados.findByFechaAlt", query = "SELECT e FROM Empleados e WHERE e.fechaAlt = :fechaAlt"),
    @NamedQuery(name = "Empleados.findBySalario", query = "SELECT e FROM Empleados e WHERE e.salario = :salario"),
    @NamedQuery(name = "Empleados.findByComision", query = "SELECT e FROM Empleados e WHERE e.comision = :comision")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private BigDecimal empNo;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "OFICIO")
    private String oficio;
    @Column(name = "DIR")
    private BigInteger dir;
    @Column(name = "FECHA_ALT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlt;
    @Column(name = "SALARIO")
    private Double salario;
    @Column(name = "COMISION")
    private Double comision;
    @JoinColumn(name = "DEPT_NO", referencedColumnName = "DEPT_NO")
    @ManyToOne(optional = false)
    private Departamentos deptNo;

    public Empleados() {
    }

    public Empleados(BigDecimal empNo) {
        this.empNo = empNo;
    }

    public BigDecimal getEmpNo() {
        return empNo;
    }

    public void setEmpNo(BigDecimal empNo) {
        this.empNo = empNo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public BigInteger getDir() {
        return dir;
    }

    public void setDir(BigInteger dir) {
        this.dir = dir;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Departamentos getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Departamentos deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejercicios.persistencia.Empleados[ empNo=" + empNo + " ]";
    }
    
}
