/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.pagos_casero;

import com.break4learning.utilidades.UtilidadesGraficas;
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
public class Escritura_Pagos implements Datos_Pagos {

    public static void EscribirMes(File archivo){
        
        Scanner sc = new Scanner(System.in);

        int id;
        int mes;
        final double alquiler = 167;
        double gastoLuz;
        double gastoAgua;
        double gastoGas;

        //int posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "rw");
            
            //escribir el id
            if(ra.length() == 0){
                id = 1;
            } else {
                //recuperar el id del elemento anterior y sumarle uno, y posicionarse al final y escribir el nuevo id
                ra.seek(ra.length() - (long)tamanio);
                id = ra.readInt() + 1;
                ra.skipBytes(tamanio);
            }
            
            ra.writeInt(id);
            
            //introduce y escribe el mes
            System.out.println("Introduce los datos:");                   
            System.out.println("Introduce el mes: ");
            mes = sc.nextInt();
            ra.writeInt(mes);
            
            //escribe el alquiler
            ra.writeDouble(alquiler);
            
            //introduce y escribe los gastos
            System.out.println("Introduce el gasto de Luz: ");
            gastoLuz = (sc.nextDouble()/3);
            ra.writeDouble(gastoLuz);
            System.out.println("Introduce el gasto de Agua: ");
            gastoAgua = (sc.nextDouble()/3);
            ra.writeDouble(gastoAgua);
            System.out.println("Introduce el gasto de Gas: ");
            gastoGas = (sc.nextDouble()/3);
            ra.writeDouble(gastoGas);
            
            ra.close();
   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void ModificarMes(File archivo){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el id del mes: ");
        int id = sc.nextInt();
        int mes;
        final double alquiler = 167;
        double gastoLuz;
        double gastoAgua;
        double gastoGas;
        
        long posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "rw");
            
            ra.seek(posicion);
            ra.writeInt(id);
               
            //introduce y escribe el mes
            System.out.println("Introduce los datos:");                   
            System.out.println("Introduce el mes: ");
            mes = sc.nextInt();
            ra.writeInt(mes);
            
            ra.skipBytes(C_DOUBLE); //se salta el alquiler
            
            //introduce y escribe los gastos
            System.out.println("Introduce el gasto de Luz: ");
            gastoLuz = (sc.nextDouble()/3);
            ra.writeDouble(gastoLuz);
            System.out.println("Introduce el gasto de Agua: ");
            gastoAgua = (sc.nextDouble()/3);
            ra.writeDouble(gastoAgua);
            System.out.println("Introduce el gasto de Gas: ");
            gastoGas = (sc.nextDouble()/3);
            ra.writeDouble(gastoGas);
            
            System.out.println("\nElemento modificado");
            
            ra.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void EliminarMes(File archivo){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el id del mes: ");
        int id = sc.nextInt();
        
        long posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "rw");
            
            ra.seek(posicion);
            ra.writeInt(-1);

            System.out.println("\nElemento eliminado\n");
            
            ra.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
