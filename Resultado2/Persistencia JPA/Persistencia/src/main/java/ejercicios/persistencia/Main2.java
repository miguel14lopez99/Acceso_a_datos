/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.persistencia;

import java.math.BigDecimal;
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
public class Main2 {
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamento;
    
    public static void main(String[] args){

        inicializarFactory();

        LeerUnRegistroBloqueado();
              
        entitymanager.close();
        emfactory.close();

    }
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("ejercicios_Persistencia_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
        
    }
    
    public static void LeerUnRegistro(){
                 
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
        
        if (departamento != null){
            System.out.println("Dept NAME: "+departamento.getDnombre());
        } else {
            System.out.println("No existe");
        }
        
        entitymanager.getTransaction().commit();
    }
    
}
