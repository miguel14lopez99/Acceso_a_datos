/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.pagos_casero;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chipi
 */
public class Lectura_Pagos implements Datos_Pagos {

    public static void LeerTodo(File archivo){
        
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio",
                          "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        
        int id;
        //int mes;
        double alquiler;
        double gastoLuz;
        double gastoAgua;
        double gastoGas;
        
        long posicion = 0;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "r"); //solo lectura
            
            while( posicion < (ra.length()-1)){
                id = ra.readInt();
                String mesStr = meses[ra.readInt()-1];
                alquiler = ra.readDouble();
                gastoLuz = ra.readDouble();
                gastoAgua = ra.readDouble();
                gastoGas = ra.readDouble();
                double total = alquiler + gastoLuz + gastoAgua + gastoGas;

                if(id != -1){
                    System.out.printf("\nID: %d"
                        + "\nMes: %s\n"
                        + "\nAlquiler:\t%6.2f €"
                        + "\nLuz:\t\t%6.2f €"
                        + "\nAgua:\t\t%6.2f €"
                        + "\nGas:\t\t%6.2f €"
                        + "\n---------------------------"
                        + "\nTotal:\t\t%6.2f €"
                        + "\n\n///////////////////////////\n", 
                        id, mesStr, alquiler, gastoLuz, gastoAgua, gastoGas, total);
                } else {
                    System.out.printf("\nID: %d"
                        + "\nEste elemento esta eliminado\n"
                        + "\n\n///////////////////////////\n", 
                        id);
                }
                
                id++;
                posicion = (id-1)*tamanio;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Fin del fichero");
        }
    }
    
    public static void LeerMes(File archivo){
        
        Scanner sc = new Scanner(System.in);
        
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio",
                          "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        
        int id;
        //int mes;
        double alquiler;
        double gastoLuz;
        double gastoAgua;
        double gastoGas;
        
        long posicion = 0;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "r"); //solo lectura
            
            System.out.println("Introduce el id del mes: ");
            id = sc.nextInt();
            posicion = (id-1)*tamanio;
            
            if( posicion < ra.length()){
                
                ra.seek(posicion);
                
                id = ra.readInt();
                String mesStr = meses[ra.readInt()-1];
                alquiler = ra.readDouble();
                gastoLuz = ra.readDouble();
                gastoAgua = ra.readDouble();
                gastoGas = ra.readDouble();
                double total = alquiler + gastoLuz + gastoAgua + gastoGas;

                if(id != -1){
                    System.out.printf("\nID: %d"
                        + "\nMes: %s\n"
                        + "\nAlquiler:\t%s €"
                        + "\nLuz:\t\t%s €"
                        + "\nAgua:\t\t%s €"
                        + "\nGas:\t\t%s €"
                        + "\n---------------------------"
                        + "\nTotal:\t\t%s €"
                        + "\n\n///////////////////////////\n", 
                        id, mesStr, alquiler, gastoLuz, gastoAgua, gastoGas, total);
                } else {
                    System.out.printf("\nID: %d"
                        + "\nEste elemento esta eliminado\n"
                        + "\n\n///////////////////////////\n", 
                        id);
                }
                
            } else {
                System.out.println("Ese elemento no existe");
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Fin del fichero");
        }
        
    }
    
}
