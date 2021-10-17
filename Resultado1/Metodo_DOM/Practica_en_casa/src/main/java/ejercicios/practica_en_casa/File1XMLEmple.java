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
        
        Document document = UtilidadesXML.CrearDoc("Empleados");
        for (int i = 0; i < 2; i++) { //añade 2 empleados
            Element empleado = document.createElement("empleado");
            document.getDocumentElement().appendChild(empleado);//al que le metes cosas
            UtilidadesXML.CrearNodo("id", String.valueOf(ids[i]), empleado, document);
            UtilidadesXML.CrearNodo("apellido", apellidos[i], empleado, document);
            UtilidadesXML.CrearNodo("departamento", String.valueOf(departs[i]), empleado, document);
            UtilidadesXML.CrearNodo("salario", String.valueOf(salarios[i]), empleado, document);
        }
        UtilidadesXML.menu(document);
   
    }
    
    
    
}
