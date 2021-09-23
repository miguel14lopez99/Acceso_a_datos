/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File3Escritura {
    
    //ESTO NO FUNCIONA
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        File file1 = util.seleccionaArchivo();
        
        String cadena = "BUENAS";
        
        try {
            FileWriter ficheroOut = new FileWriter(file1);

            ficheroOut.write(cadena);
            System.out.println(cadena);
        
            ficheroOut.close();
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
