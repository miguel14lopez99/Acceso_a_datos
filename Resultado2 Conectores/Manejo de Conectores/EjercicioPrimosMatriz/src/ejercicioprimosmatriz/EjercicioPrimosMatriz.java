/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioprimosmatriz;

import java.util.Scanner;

/**
 *
 * @author chipi
 */
public class EjercicioPrimosMatriz {

    public static void main(String[] args) {
        
        int[][] matriz;
        int [] vector;
        int dim;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce las dimensiones: ");
        dim = sc.nextInt();
        
        matriz = new int[dim][dim];
        vector = new int[dim];
        
        int cont = 0;
        for (int i = 0; cont < dim; i++) {
            if(esPrimo(i)){
                vector[cont] = i;
                cont++;
            }                               
        }
        
        for (int i : vector) {
            System.out.print(i+" ");
        }
        
        System.out.println("");
        System.out.println("");
        
        rellenarPrimosMatriz(matriz, vector);
        
        pintarMatriz(matriz);
        
    }
    
    public static int[][] rellenarPrimosMatriz(int[][] matriz, int[] vector){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                
                matriz[i][j] = vector[vector.length - (Math.abs(i-j)+1)];

                
//                if(i == j){
//                    matriz[i][j] = vector[vector.length-1];
//                }

            }
        }
        
        return matriz;
    }
    
    public static void pintarMatriz(int[][] matriz){
        //pintar la matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        while ((primo) && (contador!=numero)){
            if (numero % contador == 0)
                primo = false;
            contador++;
        }
        return primo;
    }
    
}
