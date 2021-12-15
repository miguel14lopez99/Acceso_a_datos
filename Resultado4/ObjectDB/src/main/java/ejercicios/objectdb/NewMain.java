/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.objectdb;

import javax.persistence.*;
import java.util.*;

/**
 *
 * @author b15-04m
 */
public class NewMain {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("./db/points.odb");
        EntityManager em = emf.createEntityManager();
        
//        em.getTransaction().begin();
//        for (int i = 0; i < 1000; i++) {
//            Point p = new Point(i, i);
//            em.persist(p);
//        }
//        em.getTransaction().commit();

        TypedQuery<Point> query = 
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results){
            System.out.println(p);
        }

        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: "+ q1.getSingleResult());
        
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: "+ q2.getSingleResult());
        
        //una clave primaria con 2 campos
        //montar la base de datos en un container de docker
        //container de ubuntu
        
        Point p = em.find(Point.class, 3);
        System.out.println(p);
        
        em.close();
        emf.close();
    }
    
}
