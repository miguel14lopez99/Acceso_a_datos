/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author b15-04m
 */
public class Ejemplo_clase_lectura {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();

        try {
            
            File archivo = util.seleccionaArchivo();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(archivo);
            document.getDocumentElement().normalize();
            document.getDocumentElement().getNodeName();
            System.out.printf("Elemento raíz: %s %n",document.getDocumentElement().getNodeName());
            
            NodeList empleados = document.getElementsByTagName("empleado");
            
            for (int i = 0; i <= empleados.getLength()-1; i++) {
                Node emple = empleados.item(i);
                if (emple.getNodeType() == Node.ELEMENT_NODE){
                   Element elemento = (Element) emple; 
                   NodeList nodo = elemento.getElementsByTagName("id").item(0).getChildNodes();
                   Node valornodo = (Node) nodo.item(0);
                   System.out.println(valornodo.getNodeValue());
                }
                
            }
   
        } catch (SAXException ex) {
            Logger.getLogger(Ejemplo_clase_lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejemplo_clase_lectura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ejemplo_clase_lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
