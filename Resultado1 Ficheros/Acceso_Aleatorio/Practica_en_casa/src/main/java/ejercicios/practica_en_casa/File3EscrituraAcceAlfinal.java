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
public class File3EscrituraAcceAlfinal {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File fichero = util.seleccionaArchivo();
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHARS = 2;
        
        int tamanio = C_INT + 10 * C_CHARS + C_INT + C_DOUBLE;
        
        int id = 23;
        String apellido = "Romeral";
        int depart = 22;
        double salario = 6000;
        
        int posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(fichero, "rw");
            
            ra.seek(posicion);
            
            ra.writeInt(id);
            
            StringBuffer sb  = new StringBuffer(apellido);
            sb.setLength(10);
            ra.writeChars(sb.toString());
            
            ra.writeInt(depart);
            
            ra.writeDouble(salario);
            
            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File3EscrituraAcceAlfinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File3EscrituraAcceAlfinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
