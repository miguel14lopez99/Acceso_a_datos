/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa.ejercicios;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author B15-04m
 */
public class File3EscrituraBytesArrays {
    
    public static void main(String[] args) {
        
        String[] nombres =      {"Alfonso",     "Alfredo",      "María"};
        String[] telefonos =    {"666969669",   "656666565",    "626226265"};
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File file1 = util.seleccionaArchivo();
        
        try {
            FileOutputStream ficheroOut = new FileOutputStream(file1);
            DataOutputStream datosOut = new DataOutputStream(ficheroOut);
            
            for (int i = 0; i < telefonos.length; i++) {
                
                datosOut.writeUTF(nombres[i]);
                datosOut.writeUTF(telefonos[i]);
                
            }
            
            ficheroOut.close();
            datosOut.close();
            
        }catch (IOException e) {
            System.out.println("Error de E/S");
        }
        
    }
            
}
