/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author b15-04m
 */
public class CampaniasOP1 {
    
    private final static Scanner sc = new Scanner(System.in); 
    
    private static Campanias campania;
    private static Agricultores agricultor;
    private static Maquinas maquina;
    
    public static void insJPQLT1(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id para la campaña: ");
        int id = sc.nextInt();
        campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(id));
        
        sc.nextLine(); //para que no haya error
        
        if(campania == null){
            campania = new Campanias();
            campania.setIdCamp(BigDecimal.valueOf(id));
            System.out.println("Introduce el nombre: ");
            campania.setNombreCamp(sc.nextLine());
            System.out.println("Introduce una descripción: ");
            campania.setDescripcion(sc.nextLine());
            
            entitymanager.persist(campania);
            System.out.println("\nCampaña Insertada\n");
            
        } else {
            System.out.println("La id introducida ya está asociada a una camapaña");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void modifJPQLT1(EntityManager entitymanager){
        
        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id de campaña: ");
        int id = sc.nextInt();
        campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(id), LockModeType.PESSIMISTIC_READ);
        
        sc.nextLine(); //para que no haya error
        
        if(campania != null){
            
            Query query = entitymanager.createQuery("UPDATE Campanias c SET c.nombreCamp = :nombre , c.descripcion = :descrip WHERE c.idCamp = :id");
            
            System.out.println("Introduce el nuevo nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce la nueva descripción: ");
            String descrip = sc.nextLine();
            
            query.setParameter("nombre", nombre);
            query.setParameter("descrip", descrip);      
            query.setParameter("id", BigDecimal.valueOf(id));

            query.executeUpdate();
        
            System.out.println("\nCampaña Modificada\n");
 
        } else {
            System.out.println("La id introducida no está asociada a ningúna campaña");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void borradoJPTLT1(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id de campaña: ");
        int id = sc.nextInt();
        campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(id), LockModeType.PESSIMISTIC_READ);        
        
        if(campania != null){
            
            Query query = entitymanager.createQuery("DELETE FROM Campanias c WHERE c.idCamp = :id");
                            
            query.setParameter("id", BigDecimal.valueOf(id));

            query.executeUpdate();
        
            System.out.println("\nCampaña Borrada\n");
 
        } else {
            System.out.println("La id introducida no está asociada a ningúna campaña");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void consultaJPQLT1(EntityManager entitymanager){
        
        System.out.println("Introduce un id de campaña: ");
        int id = sc.nextInt();
        campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(id));
        
        if (campania != null){
            
            TypedQuery<Object[]> query = entitymanager.createQuery("Select c.idCamp,c.nombreCamp,c.descripcion from Campanias c Where c.idCamp = :id ", Object[].class);

            query.setParameter("id", BigDecimal.valueOf(id));
            
            List<Object[]> list = query.getResultList();

            for(Object[] e:list) {
                System.out.println("Camp ID :"+e[0]);
                System.out.println("Camp NOMBRE :"+e[1]);
                System.out.println("Camp DESCRIP :"+e[2]);               
                System.out.println("");
            }
            
            TypedQuery<Object[]> query2 = entitymanager.createQuery("Select a.idAgri,a.nombreAgri,a.telefono from Agricultores a Where a.idCamp = :id ", Object[].class);
    
            query2.setParameter("id", campania);
            
            List<Object[]> list2 = query2.getResultList();
            
            for(Object[] e:list2) {               
                System.out.println("\tAgri ID :"+e[0]);
                System.out.println("\tAgri NOMBRE :"+e[1]);
                System.out.println("\tAgri TELEFONO :"+e[2]);               
                System.out.println("");
            }
            
        } else {
            System.out.println("La id introducida no está asociada a ningúna campaña");
        }
        
    }
    
    public static void consultaSinJPQLT1(EntityManager entitymanager){
        
        System.out.println("Introduce un id de campaña: ");
        int id = sc.nextInt();
        campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(id));
        
        if (campania != null){
            System.out.println("");
            System.out.println("Camp ID: "+campania.getIdCamp());
            System.out.println("Camp NOMBRE: "+campania.getNombreCamp());
            System.out.println("Camp DESCRIP: "+campania.getDescripcion());
            System.out.println("");
            
            Collection<Agricultores> list = campania.getAgricultoresCollection();
            
            Iterator<Agricultores> it = list.iterator();
            
            while(it.hasNext()){
                
                agricultor = it.next();
                System.out.println("\tAgri ID: "+agricultor.getIdAgri());
                System.out.println("\tAgri NOMBRE: "+agricultor.getNombreAgri());
                System.out.println("\tAgri TELEFONO: "+agricultor.getTelefono());
                System.out.println("");
            }
        } else {
            System.out.println("La id introducida no está asociada a ningúna campaña");
        }
        
    }
    
}
