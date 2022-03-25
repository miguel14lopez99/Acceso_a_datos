/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import com.break4learning.utilidades.UtilidadesGraficas;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-04m
 */
public class Ejemplo_clase {
    
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            
            document.setXmlVersion("1.0");
            
            Element raiz = document.createElement("empleado");

            document.getDocumentElement().appendChild(raiz);
            
            Element elem = document.createElement("id");
            raiz.appendChild(elem);
            
            menu(document);
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ejemplo_clase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    static void CrearNodo(String datoEmple, String valor, Element raiz, Document document){

        Element elem = document.createElement(datoEmple); //creamos el hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor al elemento

    }
    
    static void ConstruirXML(Document document, Result salida){
        try{
            Source source = new DOMSource(document);
            
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            Logger.getLogger(Ejemplo_clase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    static void ConstruirXML(Document document){
        try{
            Source source = new DOMSource(document);
            Result salida = new StreamResult(System.out);
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            Logger.getLogger(Ejemplo_clase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void menu(Document document){
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("- Menu -"
                + "\nElige entre una de estas opciones"
                + "\n1. Mostrar por pantalla"
                + "\n2. Generar archivo"
                + "\n0. Salir ");
        
        int opt = -1;
        
        while(opt != 0){
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 2){
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
