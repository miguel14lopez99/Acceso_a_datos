/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r4_miguel_lopez;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author b15-04m
 */
public class MaquinasOP3 {
    
    public static void modifJPQLT3(EntityManager entitymanager){
        
        entitymanager.getTransaction().begin();
        
            Query query = entitymanager.createQuery("UPDATE Maquinas m SET m.ultRevision = :hoy  WHERE m.ultRevision > :dia");           
            
            String patron = "J";
            Date hoy = new Date(System.currentTimeMillis());
            query.setParameter("hoy", hoy);
            
            try {
                Date dia = new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2021");
                query.setParameter("dia", dia);
            } catch (ParseException ex) {
                Logger.getLogger(MaquinasOP3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            query.executeUpdate();
        
            System.out.println("\nTabla Máquinas Modificada\n");
 

        entitymanager.getTransaction().commit();
        
    }
    
    public static void borradoJPQLT3(EntityManager entitymanager){

        entitymanager.getTransaction().begin();
        
            Query query = entitymanager.createQuery("DELETE FROM Maquinas m WHERE m.ultRevision < :dia");
                 
            try {
                Date dia = new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2021");
                query.setParameter("dia", dia);
            } catch (ParseException ex) {
                Logger.getLogger(MaquinasOP3.class.getName()).log(Level.SEVERE, null, ex);
            }

            query.executeUpdate();
        
            System.out.println("\nTabla Máquinas Modificada\n");
            
        entitymanager.getTransaction().commit();
        
    }
    
}
