/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ampliando;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File1CopiaArchivos {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File file1 = util.seleccionaArchivo();
        File file2 = util.seleccionaArchivo();
        
        try {
            FileInputStream ficheroIn = new FileInputStream(file1);
            DataInputStream datosIn = new DataInputStream(ficheroIn);
            FileOutputStream ficheroOut = new FileOutputStream(file2);
            DataOutputStream datosOut = new DataOutputStream(ficheroOut);
            
            byte b = datosIn.readByte();
            datosOut.writeByte(b);
            
            while (b != -1){
                
                b = datosIn.readByte();
                datosOut.writeByte(b); 
                
            }
            
            ficheroIn.close();
            datosIn.close();
            ficheroOut.close();
            datosOut.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error de E/S");
        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo");
        }
   
    }
    
}
