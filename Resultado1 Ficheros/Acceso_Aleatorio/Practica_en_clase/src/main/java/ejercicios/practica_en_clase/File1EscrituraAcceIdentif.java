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
 * @author b15-04m
 * 
 * Crea un proyecto que almacene la información de un registro según el 
 * identificador que se le suministre.
 */
public class File1EscrituraAcceIdentif {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UtilidadesGraficas util =  new UtilidadesGraficas();
        
        File fichero = util.seleccionaArchivo();
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHARS = 2;
        
        int id = 4;
        String nuevoApellido = "Carrasco";
        int nuevoDepart = 33;
        double nuevoSalario = 900;
        
        int tamanio = C_INT + 10 * C_CHARS + C_INT + C_DOUBLE;
        
        int posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra =  new RandomAccessFile(fichero,"rw");
            
            ra.seek(posicion);
            
            ra.skipBytes(C_INT); // skip id
            
            StringBuffer sb = new StringBuffer(nuevoApellido);
            sb.setLength(10);
            ra.writeChars(sb.toString());
            
            ra.writeInt(nuevoDepart);
            
            ra.writeDouble(nuevoSalario);
            
            ra.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
