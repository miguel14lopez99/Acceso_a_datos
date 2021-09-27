/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ampliando;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File5Desencripta {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        File file1 = util.seleccionaArchivo();
        File file2 = util.seleccionaArchivo();
        
        try {
            FileReader ficheroIn = new FileReader(file1);
            FileWriter ficheroOut = new FileWriter(file2);
            
            int i = ficheroIn.read();
            ficheroOut.write(i-3);
            
            while(i != -1){
                i = ficheroIn.read();
                ficheroOut.write(i-3);
            }
            
            ficheroIn.close();
            ficheroOut.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
