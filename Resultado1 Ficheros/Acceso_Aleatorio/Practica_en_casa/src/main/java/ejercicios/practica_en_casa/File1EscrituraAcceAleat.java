/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chipi
 */
public class File1EscrituraAcceAleat {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File ficheroPrueba = util.seleccionaArchivo();
        
        int[] id = {1,2,3,4,5}; //4 bytes
        String[] apellidos = {"Lopez", "Gutiérrez", "García", "Perez", "Menchero"}; // 10*2 = 20 bytes
        int[] departamentos = {45,54,27,72,11}; // 4 bytes
        double[] salarios = {3000, 2000, 1500, 1600, 5000}; // 8 bytes
        
        try {
            RandomAccessFile ra = new RandomAccessFile(ficheroPrueba, "rw");
            
            
            for (int i = 0; i < id.length; i++) {
                
                //escribir el id
                ra.writeInt(id[i]);
                
                //escribir el String apellido
                StringBuffer sb = new StringBuffer(apellidos[i]);
                sb.setLength(10);
                ra.writeChars(sb.toString());
                
                //escribir el depart
                ra.writeInt(departamentos[i]);
                
                //escribir el salario
                ra.writeDouble(salarios[i]);
                
            }
            
            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File1EscrituraAcceAleat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File1EscrituraAcceAleat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
