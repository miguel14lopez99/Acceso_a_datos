/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyector6;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mis_beans.Agricultor;
import mis_beans.BaseDatos;
import mis_beans.Maquina;
import mis_beans.Trabajo;
/**
 *
 * @author chipi
 */
public class ProyectoR6 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        BaseDatos.inicializarFactory();
        try {       
            BaseDatos.ReiniciarDatos();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ProyectoR6.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //creatmos el trabajo
        Trabajo trabajo = new Trabajo();
        trabajo.setIdTrab(7);
        trabajo.setNombreTrabajador("Bea");
        //recuperar máquina de la base de datos
        Maquina maquina = BaseDatos.RecuperarMaquina(1); //la 2 no ha hecho la revisión
        trabajo.setIdMaquina(maquina);
        
        //recuperar el agricultor de la base de datos
        Agricultor agricultor = BaseDatos.RecuperarAgricultor(2); //el 2 no está disponible
        System.out.println();
        trabajo.setIdAgri(agricultor);
        agricultor.getTrabajosCollection().add(trabajo);
        
        //asignar los vetoable change
        trabajo.addVetoableChangeListener(agricultor);
        trabajo.addVetoableChangeListener(maquina);
        
        //Cuando setea la fecha del trabajo, se avisa tanto al agricultor como 
        //a la máquina para saber si se tiene que buscar otra fecha
        try {
            trabajo.setFechaTrabajo("19-03-2022");
            
            System.out.println("El trabajo ha sido asignado");
        } catch (PropertyVetoException ex) {
            System.out.println(ex.getMessage());
            //se elimina el trabajo
            BaseDatos.VetarTrabajo(trabajo);
        }

    }
    
}
