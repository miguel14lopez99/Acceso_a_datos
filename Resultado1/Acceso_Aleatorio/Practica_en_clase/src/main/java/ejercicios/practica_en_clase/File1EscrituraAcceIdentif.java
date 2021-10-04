/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

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
        
        int id = 1;
        
        File ficheroPrueba = new File("ejemploprueba.dat");
        try {
            RandomAccessFile fichero =  new RandomAccessFile(ficheroPrueba,"rw");
            
            StringBuffer buffer = null;
            String apellido
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error de E/S");
        }
        
    }
    
}
