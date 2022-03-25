/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import com.break4learning.utilidades.UtilidadesTexto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chipi
 */

public class File3Lectura {
    
    public static void main(String[] args) {
        
        UtilidadesTexto util = new UtilidadesTexto();
        
        File file1 = new File(util.seleccionaDirectorio()+"\\"+util.seleccionaArchivo());
        
        try {
            FileReader ficheroIn = new FileReader(file1);
            BufferedReader bufferficheroIn = new BufferedReader( ficheroIn);
             
            String linea = bufferficheroIn.readLine();
            
            while(linea != null){
                System.out.println(linea);
                linea = bufferficheroIn.readLine();
            }
        
            ficheroIn.close();
            bufferficheroIn.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
