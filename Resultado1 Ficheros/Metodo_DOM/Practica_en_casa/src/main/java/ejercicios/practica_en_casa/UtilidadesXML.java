/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_casa;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author b15-04m
 */
public class UtilidadesXML {
    
    
    public static void CrearNodo(String datoEmple, String valor, Element raiz, Document document){

        Element elem = document.createElement(datoEmple); //creamos el hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor al elemento

    }
    
    public static void CrearNodoSinTexto(String datoEmple, String valor, Element raiz, Document document){

        Element elem = document.createElement(datoEmple); //creamos el hijo
        Text text = document.createTextNode(valor); //damos valor
        raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor al elemento

    }
    
    public static Document CrearDoc(String nombre){
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
        Document document = null;
        
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, nombre, null);
            document.setXmlVersion("1.0");
            
            
        } catch(ParserConfigurationException ex){
            Logger.getLogger(UtilidadesXML.class.getName()).log(Level.SEVERE, null, ex);
        }
      return document;
    }
    
    public static void ConstruirXML(Document document, Result salida){
        try{
            Source source = new DOMSource(document);
            
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            System.out.println("Error en la transformaci??n");
        }
    }    
    
    public static void ConstruirXML(Document document){
        try{
            Source source = new DOMSource(document);
            Result salida = new StreamResult(System.out);
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            System.out.println("Error en la transformaci??n");
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
        
        while(opt != 0){ //opci??n de salida
            
            System.out.println("\nIntroduce la opci??n: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 2){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opci??n: ");
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
