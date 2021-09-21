/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.File;

/**
 *
 * @author chipi
 */
public class File3Eliminando {
    
    //no funciona y no se porque
    
    public static void main(String[] args) {
    
        File directorio = new File(".\\carpeta");
        
        if (!directorio.exists()) {
        System.out.println("El directorio no existe.");
    } else {
        directorio.delete();
        System.out.println("El directorio fue eliminado.");
    }

    }
    
}
