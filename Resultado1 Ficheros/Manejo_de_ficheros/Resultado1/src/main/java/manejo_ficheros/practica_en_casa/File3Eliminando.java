/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;

/**
 *
 * @author chipi
 */
public class File3Eliminando {
    
    public static void main(String[] args) {
    
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        
        String ruta = util.seleccionaDirectorio();
        
        File directorio = new File(ruta);
        
        File archivos[] = directorio.listFiles();
        System.out.println("nº archivos: "+directorio.listFiles().length);
        
        for (File archivo : archivos) {
            archivo.delete();
        }
        directorio.delete();
    }
    
}
