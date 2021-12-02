/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import static ejercicios.trabajo_r3_miguel_lopez.Main.entitymanager;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

/**
 *
 * @author b15-04m
 */
public class AgricultoresOP2 {
    
    private final static Scanner sc = new Scanner(System.in);
    
    private static Campanias campania;
    private static Agricultores agricultor;
    private static Maquinas maquina;
    
    public static void insJPQLT2(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id para el agricultor: ");
        int id = sc.nextInt();
        agricultor = entitymanager.find(Agricultores.class, BigDecimal.valueOf(id));
        
        if(agricultor == null){
            agricultor = new Agricultores();
            agricultor.setIdAgri(BigDecimal.valueOf(id));
            System.out.println("Introduce el nombre: ");
            agricultor.setNombreAgri(sc.next());
            System.out.println("Introduce el telefono: ");
            agricultor.setTelefono(sc.nextInt());

            //buscar campania correspondiente
            System.out.println("Introduce el id la campaña: ");
            campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(sc.nextInt()));
            agricultor.setIdCamp(campania);
            
            if (campania != null){
                entitymanager.persist(agricultor);
                System.out.println("\nAgricultor Insertado\n");
            } else {
                System.out.println("La id introducido no está asociado a ninguna campaña");
            }
            
            
        } else {
            System.out.println("La id introducida ya está asociada a un agricultor");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void modifJPQLT2(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id del agricultor: ");
        int id = sc.nextInt();
        agricultor = entitymanager.find(Agricultores.class, BigDecimal.valueOf(id), LockModeType.PESSIMISTIC_READ);
        
        if(agricultor != null){
            System.out.println("Introduce el nuevo nombre: ");
            agricultor.setNombreAgri(sc.next());
            System.out.println("Introduce el nuevo telefono: ");
            agricultor.setTelefono(sc.nextInt());

            //buscar campania correspondiente
            System.out.println("Introduce el id la campaña: ");
            campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(sc.nextInt()));
            agricultor.setIdCamp(campania);
            
            if (campania != null){
                entitymanager.persist(agricultor);
                System.out.println("\nAgricultor Modificado\n");
            } else {
                System.out.println("La id introducido no está asociado a ninguna campaña");
            }
            
            
        } else {
            System.out.println("La id introducida no está asociada a ningún agricultor");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void borradoJPTLT2(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un id del agricultor a eliminar: ");
        int id = sc.nextInt();
        agricultor = entitymanager.find(Agricultores.class, BigDecimal.valueOf(id), LockModeType.PESSIMISTIC_READ);
        
        if(agricultor != null){
            entitymanager.remove(agricultor);
            System.out.println("\nAgricultor Borrado\n");
        } else {
            System.out.println("La id introducida no está asociada a ningún agricultor");
        }

        entitymanager.getTransaction().commit();
        
    }
    
    public static void consultaJPTLT2(){
        
        System.out.println("Introduce un id de agricultor: ");
        int id = sc.nextInt();
        agricultor = entitymanager.find(Agricultores.class, BigDecimal.valueOf(id));
        
        if (agricultor != null){
            System.out.println("");
            System.out.println("Agri ID: "+agricultor.getIdAgri());
            System.out.println("Agri NOMBRE: "+agricultor.getNombreAgri());
            System.out.println("Agri TELEFONO: "+agricultor.getTelefono());
            System.out.println("");
            
            Collection<Maquinas> list = agricultor.getMaquinasCollection();
            
            Iterator<Maquinas> it = list.iterator();
            
            while(it.hasNext()){
                
                maquina = it.next();
                System.out.println("\tMaquina ID: "+ maquina.getIdMaquina());
                System.out.println("\tMaquina N. BASTIDOR: "+ maquina.getNroBastidor());
                SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println("\tMaquina ULT. REVISIÓN: "+ fecha.format(maquina.getUltRevision()));
                System.out.println("");
            }
        } else {
            System.out.println("La id introducida no está asociada a ningún agricultor");
        }
        
    }
    
}
