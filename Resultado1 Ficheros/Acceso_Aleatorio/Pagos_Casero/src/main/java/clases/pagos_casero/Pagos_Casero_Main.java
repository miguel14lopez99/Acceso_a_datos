/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.pagos_casero;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author chipi
 */
public class Pagos_Casero_Main {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File archivo = util.seleccionaArchivo();
        
        Lectura_Pagos.LeerTodo(archivo);
        
        menu(archivo);
   
    }
    
    public static void menu(File archivo){
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("- Menu -"
                + "\nElige entre una de estas opciones"
                + "\n1. Mostrar archivo"
                + "\n2. Mostrar un mes"
                + "\n3. Escribir un mes"
                + "\n4. Modificar un mes"
                + "\n5. Eliminar un mes"
                + "\n0. Salir ");
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 5){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    Lectura_Pagos.LeerTodo(archivo);
                    break;
                case 2:
                    Lectura_Pagos.LeerMes(archivo);
                    break;
                case 3:
                    Escritura_Pagos.EscribirMes(archivo);
                    break;
                case 4:
                    Escritura_Pagos.ModificarMes(archivo);
                    break;
                case 5:
                    Escritura_Pagos.EliminarMes(archivo);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
        sc.close();
        
    }
    
}
