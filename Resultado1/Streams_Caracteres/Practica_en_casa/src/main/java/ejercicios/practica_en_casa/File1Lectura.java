/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File1Lectura {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        File file1 = util.seleccionaArchivo();
        
        try {
            FileReader ficheroIn = new FileReader(file1);
            
            int i = 0;
            i = ficheroIn.read();
            
            while(i != -1){
                System.out.print((char) i);
                i = ficheroIn.read();
            }
            
            ficheroIn.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
