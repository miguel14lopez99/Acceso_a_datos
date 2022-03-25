/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mis_beans;

import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author chipi
 */
public class BaseDatos {
    
    private static EntityManagerFactory emfactory;
    private static EntityManager entitymanager;
    private static Trabajo trabajo;
    private static Agricultor agricultor;
    private static Maquina maquina;
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("./db/resultado6db.odb;");
        entitymanager = emfactory.createEntityManager();
        System.out.println("Conectado\n");
        
    }
    
    public static void ReiniciarDatos() throws PropertyVetoException{

        String[] nombreTrab = {"Manolo", "Fran", "Jorge", "Daniel", "Pablo", "Luis"};
        String[] fechasTrabajos = {"25-01-2021", "16-04-2021", "27-04-2021", "07-05-2021", "15-06-2021", "16-06-2021"};
        int[] agricultores = {1,2,3,4,5,6};
        int[] maquinas = {1,2,3,4,5,6};
        
        
        String[] nombAgri = {"Miguel", "Pepe", "Paco", "Elena", "Javier", "Benito"};
        String[] fechasDisponibles = {"25-01-2021", "16-04-2022", "27-04-2021", "07-05-2021", "15-06-2021", "16-06-2021"};
        //int[] trabajos = {1,2,3,4,5,6};

        
        String[] nMaquina = {"JT2BG22K3Y0485107", "1GNKVGED5CJ196120", "3FAFP13P41R199033", "5FNRL38489B407103", "JH4KA3250JC001616", "JH4KA7680RC011845"};
        String[] ultimasRevisiones = {"25-01-2022", "16-04-2021", "27-04-2021", "07-05-2021", "15-06-2021", "16-06-2021"};

        
        //Borrado de registros 
        entitymanager.getTransaction().begin();
        entitymanager.createQuery("DELETE FROM Maquina").executeUpdate();  
        entitymanager.createQuery("DELETE FROM Trabajo").executeUpdate();  
        entitymanager.createQuery("DELETE FROM Agricultor").executeUpdate();  
        entitymanager.getTransaction().commit();

        //Inserción de registros
        //AGRICULTORES
        for (int i = 0; i < nombAgri.length; i++) {
            agricultor = new Agricultor();
            agricultor.setIdAgri(i+1);
            agricultor.setNombreAgri(nombAgri[i]);
            agricultor.setFechaDisponible(fechasDisponibles[i]);      

            //buscar trabajo correspondiente
            //trabajo = entitymanager.find(Trabajo.class, BigDecimal.valueOf(trabajos[i]));
            
            List<Trabajo> lista = new ArrayList<Trabajo>();
            agricultor.setTrabajosCollection(lista);

            entitymanager.getTransaction().begin();
            entitymanager.persist(agricultor);
            entitymanager.getTransaction().commit();
        }
        
        //MAQUINAS
        for (int i = 0; i < nMaquina.length; i++) {
            maquina = new Maquina();
            maquina.setIdMaquina(i+1);
            maquina.setNombreMaquina(nMaquina[i]);           
            maquina.setUltimaRevision(ultimasRevisiones[i]);      

            entitymanager.getTransaction().begin();
            entitymanager.persist(maquina);
            entitymanager.getTransaction().commit();
        }
        
        //TRABAJOS
        for (int i = 0; i < nombreTrab.length; i++) {
            trabajo = new Trabajo();
            trabajo.setIdTrab(i+1);
            trabajo.setNombreTrabajador(nombreTrab[i]);
            trabajo.setFechaTrabajo(fechasTrabajos[i]);      

            entitymanager.getTransaction().begin();
            
            //buscar el agricultor correspondiente
            agricultor = entitymanager.find(Agricultor.class, agricultores[i]);
            trabajo.setIdAgri(agricultor);
            
            //buscar la máquina correspondiente
            maquina = entitymanager.find(Maquina.class, maquinas[i]);
            trabajo.setIdMaquina(maquina);
            
            entitymanager.persist(trabajo);
            entitymanager.getTransaction().commit();
        }

        System.out.println("Datos Reiniciados\n");                       
        
    }
    
    public static Maquina RecuperarMaquina(int id){
        Maquina aux = entitymanager.find(Maquina.class, id);
        
        return aux;
    }
    
    public static Agricultor RecuperarAgricultor(int id){
        Agricultor aux = entitymanager.find(Agricultor.class, id);
        
        return aux;
    }
    
    public static void AsignarTrabajo(Agricultor agri, Trabajo trab){
        entitymanager.getTransaction().begin();

        //insertar el trabajo en la BBDD
        entitymanager.persist(trab);
        
        //añadir el trabajo en la lista de trabajos del agricultor
        agricultor.getTrabajosCollection().add(trab);
        entitymanager.persist(agri);
        
        entitymanager.getTransaction().commit();
    }
    
    public static void VetarTrabajo(Trabajo trab){
        entitymanager.getTransaction().begin();
        
        entitymanager.remove(trab);
        
        entitymanager.getTransaction().commit();
    }
    
}
