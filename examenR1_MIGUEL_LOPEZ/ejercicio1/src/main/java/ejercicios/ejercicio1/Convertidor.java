/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ejemploclase;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

/**
 *
 * @author b15-04m
 */
public class Convertidor {
    
    public static void main(String[] args) {
        
        String hojaEstilo = "alumnosPlantilla.xsl";
        String datosAlumnos = "alumnos.xml";
        File pagHTML = new File("mipagina.html");
        
        try{
        
            FileOutputStream os = new FileOutputStream(pagHTML);
            Source estilos = new StreamSource(hojaEstilo);
            Source datos = new StreamSource(datosAlumnos);

            Result result = new StreamResult(os);

            Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos,result);
            
            os.close();
            
        }catch(IOException | TransformerException e){
            System.out.println("Error: "+e);
        }
 
    }
    
}
