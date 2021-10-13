/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ejemploclase;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author b15-04m
 */
public class GestionContenido extends DefaultHandler {
    
    public GestionContenido() {

        super();

    }
    public void startDocument() {

        System.out.println("Comienzo documento");

    }
    public void endDocument() {

        System.out.println("Final documento");

    }
    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {

        System.out.printf("\tComienzo elemento: %s %n", nombre);

    }
    public void endElement(String uri, String nombre, String nombreC) {
        System.out.printf("\tFin elemento: %s %n", nombre);

    }
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {

        String car = new String(ch, inicio, longitud);
        car = car.replaceAll("[\t\n]","");
        System.out.printf("\tCaracteres: %s %n", car);

    }
    
}
