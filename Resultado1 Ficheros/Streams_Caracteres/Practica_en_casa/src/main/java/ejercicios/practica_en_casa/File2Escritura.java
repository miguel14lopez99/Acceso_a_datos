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
public class File2Escritura {

    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        File file1 = util.seleccionaArchivo();
        
        String cadena = "HOLA";
        char[] caracteres = new char[cadena.length()];
        
        for (int i = 0; i < cadena.length(); i++) {
            caracteres[i] = cadena.charAt(i);
        }
            
        try {
            FileWriter ficheroOut = new FileWriter(file1);
            
            for (int i = 0; i < caracteres.length; i++) {
               ficheroOut.write(caracteres[i]);
               System.out.print(caracteres[i]);
            }           
            
            ficheroOut.close();
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }

}
