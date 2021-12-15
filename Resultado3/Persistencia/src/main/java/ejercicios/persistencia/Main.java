/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.persistencia;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

/**
 *
 * @author b15-04m
 */
public class Main {
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamento;
    
    public static void main(String[] args){

        inicializarFactory();

        eliminarDatos();
              
        entitymanager.close();
        emfactory.close();

    }
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("ejercicios_Persistencia_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
        
    }
    
    public static void LeerUnRegistro(){
        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(10));
        if (departamento != null){
            System.out.println("Dept NAME: "+departamento.getDnombre());
        } else {
            System.out.println("No existe");
        }
        
    }
    
    public static void LeerUnRegistroBloqueado(){
        
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(10), LockModeType.PESSIMISTIC_READ);
        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (departamento != null){
            System.out.println("Dept NAME: "+departamento.getDnombre());
        } else {
            System.out.println("No existe");
        }
        
        entitymanager.getTransaction().commit();
    }
    
    public static void LeerUnRegistroRelacionado(){     
        
        Empleados empleado;
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(10));
        if (departamento != null){
            System.out.println("Dept NAME: "+departamento.getDnombre());
            
            Collection<Empleados> list = departamento.getEmpleadosCollection();
            
            Iterator<Empleados> it = list.iterator();
            
            while(it.hasNext()){
                
                empleado = it.next();
                System.out.println("Emple NAME: "+ empleado.getApellido());
            }
            
        } else {
            System.out.println("No existe");
        }
        
    }
    
    public static void LeerEmpleadoMasDepartamento(){
        
        Empleados empleado;
        
        empleado = entitymanager.find(Empleados.class, BigDecimal.valueOf(7566));
        
        if (empleado != null){
            System.out.println("\nEmple NAME: "+ empleado.getApellido() +" | Depart Nº: "+ empleado.getDeptNo().getDnombre());
        } else {
            System.out.println("No existe");
        }
                
    }
    
    public static void insertarDatos(){
        
        departamento = new Departamentos();
        departamento.setDeptNo(BigDecimal.valueOf(99));
        departamento.setDnombre("CONSTRUCCIÓN");
        departamento.setLoc("VALENCIA");
        
        entitymanager.getTransaction().begin();
        entitymanager.persist(departamento);
        entitymanager.getTransaction().commit();
        
    }
    
    public static void modificarDatos(){
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(99), LockModeType.PESSIMISTIC_READ);       
        
        departamento.setLoc("TOLEDO");
        entitymanager.getTransaction().commit();
        
    }
    
    public static void eliminarDatos(){
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(99), LockModeType.PESSIMISTIC_READ);
                
        entitymanager.remove(departamento);
        entitymanager.getTransaction().commit();
        
    }
    
}
