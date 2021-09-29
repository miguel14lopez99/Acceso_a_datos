/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa.ejercicios;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B15-04m
 * Escribir un dato de cada uno de los tipos primitivos en un archivo.
 */
public class File1EscrituraBytesPrim {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File file1 = util.seleccionaArchivo();
        
        
        try {
            FileOutputStream ficheroOut = new FileOutputStream(file1);
            DataOutputStream datosOut = new DataOutputStream(ficheroOut);
            
            boolean bool = true;
            datosOut.writeBoolean(bool);
            int b = 1;
            datosOut.writeByte(b);
            int s = 2;
            datosOut.writeShort(s);
            int i = 3;
            datosOut.writeInt(i);
            long l = 4;
            datosOut.writeLong(l);
            float f = 5;
            datosOut.writeFloat(f);
            double d = 6;
            datosOut.writeDouble(d);
            char c = 'A';
            datosOut.writeChar(c);
            
            ficheroOut.close();
            datosOut.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}

