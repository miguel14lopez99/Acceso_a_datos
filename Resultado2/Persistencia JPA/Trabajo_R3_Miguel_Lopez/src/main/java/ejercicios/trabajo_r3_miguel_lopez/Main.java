/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.util.Scanner;

/**
 *
 * @author b15-04m
 */
public class Main {
    
    public static void main(String[] args) {
        
        //menu
        menuPrincipal();
        
    }
    
    public static void menuPrincipal(){
        
        Scanner sc = new Scanner (System.in);

        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ MENÚ ============"
                + "\n0. Salir"
                + "\n1. Conexión a BBDD"
                + "\n2. Crear estructura de tablas / Reiniciar datos"
                + "\n3. Aplicación");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 3){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    ConexionBBDD();
                    break;
                case 2:
                    
                    CrearTablas();
                    
                    //Reinicio de datos
                    System.out.println("¿Quiéres reiniciar los datos? (S/N)");
                    char sino = sc.next().toUpperCase().charAt(0);
                    while (sino != 'S' && sino != 'N'){
                        System.out.println("Error. Vuelve a introducir los datos (S/N):");
                        sino = sc.next().toUpperCase().charAt(0);
                    }
                    switch (sino) {
                        case 'S':
                            ReiniciarDatos();
                            break;
                    }
                    
                    break;
                case 3:
                    MenuApp();
                    break;
                case 0:
                    System.out.println("Adios");
                    System.exit(0);
                    break;
            }
        }
        
    }
    
    public static void ConexionBBDD(){
        
        
        
    }
    
    public static void CrearTablas(){
        
        
        
    }
    
    public static void ReiniciarDatos(){
        
        
        
    }
    
    public static void MenuApp(){
        
        Scanner sc = new Scanner (System.in);           
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú pricipal"
                + "\n1. Tabla 2 (sin JPQL)"
                + "\n2. Tabla 1 (con JPQL no almacenadas)"
                + "\n3. Tabla 1 (sin JPQL)"
                + "\n4. Tabla 3 (con JPQL)");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 4){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    //metodo1();
                    System.out.println("metodo1");
                    break;
                case 2:
                    //metodo2();
                    System.out.println("metodo2");
                    break;
                case 3:
                    //metodo3();
                    System.out.println("metodo3");
                    break;
                case 4:
                    //metodo4();
                    System.out.println("metodo4");
                    break;
                case 0:
                    menuPrincipal();
                    break;
            }
        }
        
    }
    
}
