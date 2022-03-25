/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.break4learning.utilidades;

import java.util.Scanner;

/**
 *
 * @author break4learning
 */
public class UtilidadesTexto {
    public static String seleccionaDirectorio(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la ruta del directorio a analizar:");
        String ruta = sc.nextLine();
        return (ruta);
    } 
    public static String seleccionaArchivo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la ruta del directorio a analizar:");
        String archivo = sc.nextLine();
        return (archivo);
    } 
}
