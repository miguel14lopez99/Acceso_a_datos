package ejercicios.ejercicio1jpa;


import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b15-04m
 */
public class Main {
    
        
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    

    public static void main(String[] args){

        inicializarFactory();

        emfactory.close();
        entitymanager.close();

    }
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("ejercicios_Persistencia_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
        
    }
    
    
    
}
