/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

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
public class File2LecturaAcceIdentif {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File fichero = util.seleccionaArchivo();
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHARS = 2;
        
        int id = 4;
        
        byte[] cadena = new byte[20];
        String apellido;
        int depart;
        double salario;
        
        int tamanio = C_INT + 10 * C_CHARS + C_INT + C_DOUBLE;
        
        int posicion = (id - 1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(fichero, "r");
            
            if (posicion >= fichero.length())
                System.out.println("ID: "+ id + " no existe empleado...");
            else{
                ra.seek(posicion);
                
                id = ra.readInt();
                ra.read(cadena);
                apellido = new String(cadena);
                depart = ra.readInt();
                salario = ra.readDouble();
                
                System.out.printf("\nID: %d"
                        + "\nApellido: %s"
                        + "\nDepartamento: %d"
                        + "\nSalario: %s"
                        + "\n-------------------\n", 
                        id, apellido, depart, salario);
            }

            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File2LecturaAcceIdentif.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File2LecturaAcceIdentif.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
