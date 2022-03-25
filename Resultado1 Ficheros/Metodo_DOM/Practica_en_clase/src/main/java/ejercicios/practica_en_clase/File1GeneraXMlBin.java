/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import com.break4learning.utilidades.UtilidadesGraficas;
import static ejercicios.practica_en_clase.UtilidadesXML.ConstruirXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
public class File1GeneraXMlBin {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        File fichero = util.seleccionaArchivo();
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHARS = 2;
        
        int id = 1;
        byte[] cadena = new byte[20];
        String apellido;
        int depart;
        double salario;
        
        int tamanio = C_INT + 10 * C_CHARS + C_INT + C_DOUBLE;
        
        //int posicion = (id - 1)*tamanio; // = 0
        int posicion = 0;
        
        try {
            RandomAccessFile ra =  new RandomAccessFile(fichero, "r");
            
            while (posicion < (ra.length() - 1)){
                id = ra.readInt();
                ra.read(cadena);
                apellido = new String(cadena);
                depart = ra.readInt();
                salario = ra.readDouble();
                
                System.out.printf("\nID: %d"
                        + "\nApellido: %s"
                        + "\nDepartamento: %d"
                        + "\nSalario: %s"
                        + "\n-------------------\n", 
                        id, apellido, depart, salario);
                
                id++; // siguiente
                
                posicion = (id -1)*tamanio;
            }
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");
            
            for (int i = 0; i < 2; i++) { //añade 2 empleados
                Element empleado = document.createElement("empleado");
            document.getDocumentElement().appendChild(empleado);
            }  
            
            menu(document);
            
            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File1GeneraXMlBin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(File1GeneraXMlBin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(File1GeneraXMlBin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    public static void menu(Document document){
        UtilidadesGraficas util = new UtilidadesGraficas();
        
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
                    ConstruirXML(document);
                    break;
                case 2:
                    File archivo = util.seleccionaArchivo();
                    Result salida = new StreamResult(archivo);
                    ConstruirXML(document, salida);
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
    }
    
}
