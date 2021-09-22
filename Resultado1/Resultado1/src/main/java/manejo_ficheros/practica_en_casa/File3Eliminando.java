/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros;

import java.io.File;

/**
 *
 * @author chipi
 */
public class File3Eliminando {
    
    //no funciona y no se porque
    
    public static void main(String[] args) {
    
        File directorio = new File(".\\carpeta");
        
        if (directorio.exists() && directorio.isDirectory()){
            if(directorio.listFiles().length == 0){
                directorio.delete();
            } else {
                File archivos[] = directorio.listFiles();
                int numFiles = directorio.listFiles().length;
                for (int i = 0; i < numFiles; i++) {
                    archivos[i].delete();
                }
                directorio.delete();
            }
        }

    }
    
}
