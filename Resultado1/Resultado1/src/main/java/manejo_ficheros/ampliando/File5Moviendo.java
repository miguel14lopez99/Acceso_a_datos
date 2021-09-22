/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros.ampliando;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author chipi
 */
public class File5Moviendo {
    
    public static void main(String[] args) {
        
        Path origenPath = FileSystems.getDefault().getPath("inserta origen");
        Path destinoPath = FileSystems.getDefault().getPath("inserta destino");
    
        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        }
        
    }
            
    
}
