package ejercicios.ejemploclase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.break4learning.utilidades.UtilidadesGraficas;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


/**
 *
 * @author b15-04m
 */
public class ejemploSAX {
    
    public static void main(String[] args) {
        
        UtilidadesGraficas util = new UtilidadesGraficas();
        
        try {
            
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader procesadorXML = parser.getXMLReader();
            
            GestionContenido gestor = new GestionContenido();
            procesadorXML.setContentHandler(gestor);
            InputSource fileXML = new InputSource("alumnos.xml");
            procesadorXML.parse(fileXML);
            
            
            
        } catch (SAXException | IOException ex) {
            Logger.getLogger(ejemploSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ejemploSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
