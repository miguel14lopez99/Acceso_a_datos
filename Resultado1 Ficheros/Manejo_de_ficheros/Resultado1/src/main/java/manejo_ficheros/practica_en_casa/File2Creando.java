/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros.practica_en_casa;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File2Creando {
    
    public static void main(String[] args) {
        
        File f;
        String directorio = ".\\carpeta";
        f = new File(directorio);
        f.mkdir();
        
        try {
            f = new File(directorio,"file1");
            f.createNewFile();
            f = new File(directorio,"file2");
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        
    }
    
    
}
