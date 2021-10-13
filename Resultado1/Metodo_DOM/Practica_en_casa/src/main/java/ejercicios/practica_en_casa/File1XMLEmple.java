/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import com.break4learning.utilidades.UtilidadesXML;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author b15-04m
 */
public class File1XMLEmple {
    
    public static void main(String[] args) {
        
        int[] ids = {1,2};
        String[] apellidos = {"lopez", "gutierrez"};
        int[] departs = {22,33};
        double[] salarios = {1000, 2000};
        
        try {
            Document document = 
            
            for (int i = 0; i < 2; i++) { //añade 2 empleados
                Element empleado = document.createElement("empleado");
                document.getDocumentElement().appendChild(empleado);
                UtilidadesXML.CrearNodo("id", String.valueOf(ids[i]), empleado, document);
                UtilidadesXML.CrearNodo("apellido", apellidos[i], empleado, document);
                UtilidadesXML.CrearNodo("departamento", String.valueOf(departs[i]), empleado, document);
                UtilidadesXML.CrearNodo("salario", String.valueOf(salarios[i]), empleado, document);
            }  
            
            menu(document);
  
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(File1XMLEmple.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    public static void menu(Document document){
        UtilidadesGraficas util = new UtilidadesGraficas();
        UtilidadesXML utilXML = new UtilidadesXML();
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("- Menu -"
                + "\nElige entre una de estas opciones"
                + "\n1. Mostrar por pantalla"
                + "\n2. Generar archivo"
                + "\n0. Salir ");
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 2){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    utilXML.ConstruirXML(document);
                    break;
                case 2:
                    File archivo = util.seleccionaArchivo();
                    Result salida = new StreamResult(archivo);
                    utilXML.ConstruirXML(document, salida);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
    }
    
}
