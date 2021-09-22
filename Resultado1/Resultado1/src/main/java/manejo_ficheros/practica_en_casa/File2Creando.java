/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author chipi
 */
public class File2Creando {
    
    public static void main(String[] args) {
        
        try {
            File directorio = new File("carpeta");
            directorio.mkdir();
            File file1 = new File(directorio,"file1");
            file1.createNewFile();
            File file2 = new File(directorio,"file2");
            file2.createNewFile();
        } catch (IOException e) {
            System.out.println("Se ha producido un error");
        }
        
    }
    
    
}
