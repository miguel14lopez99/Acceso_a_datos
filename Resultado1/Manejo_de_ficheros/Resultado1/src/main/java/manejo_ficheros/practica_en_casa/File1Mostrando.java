/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejo_ficheros.practica_en_casa;
import java.io.File;

/**
 *
 * @author chipi
 */
public class File1Mostrando {
    public static void main(String[ ] args){
        System.out.println("Ficheros en el directorio del proyecto: ") ;
        File fi=new File(".");
        String[] archivos = fi.list();
        for (int i=0;i<archivos.length;i++) {
            System.out.println(archivos[i]);

        }
    }
}

