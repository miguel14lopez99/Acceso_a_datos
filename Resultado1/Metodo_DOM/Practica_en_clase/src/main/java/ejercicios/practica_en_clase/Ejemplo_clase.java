/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-04m
 */
public class Ejemplo_clase {
    
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
            
            document.setXmlVersion("1.0");
            
            Element raiz = document.createElement("empleado");

            document.getDocumentElement().appendChild(raiz);
            
            Element elem = document.createElement("id");
            raiz.appendChild(elem);
            
            Text text = document.createTextNode("1");
            elem.appendChild(text);
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
    
}
