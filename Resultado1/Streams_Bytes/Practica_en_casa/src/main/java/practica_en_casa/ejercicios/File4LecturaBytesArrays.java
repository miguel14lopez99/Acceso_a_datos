/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa.ejercicios;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author B15-04m
 */
public class File4LecturaBytesArrays {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File file1 = util.seleccionaArchivo();
        
        try {
            FileInputStream ficheroIn = new FileInputStream(file1);
            DataInputStream datosIn = new DataInputStream(ficheroIn);
            
            String nombre = datosIn.readUTF();
            String numero = datosIn.readUTF();
            System.out.println(nombre + " - " + numero);
            
            while(nombre != null && numero != null){
                
                nombre = datosIn.readUTF();
                numero = datosIn.readUTF();
                System.out.println(nombre + " - " + numero);
                
            }
            
            ficheroIn.close();
            datosIn.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
