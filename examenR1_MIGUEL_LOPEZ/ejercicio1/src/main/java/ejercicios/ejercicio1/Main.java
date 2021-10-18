/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
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
public class Main {
    
    public static void main(String[] args) {
        

        menuPrincipal();
    }
    
    
    
    public static void metodo1(){

        File directorio = new File(".\\ORIGEN");
        directorio.mkdir();
        directorio = new File(".\\PUBLICADO");
        directorio.mkdir();

    }
    public static void metodo2(){ //comprobar si se ha escrito bien XXXXXXXXXX

        File archivo = new File(".\\ORIGEN\\datos.dat");
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int[] ids = {1,2,3,4};
        String[] productos = {"Raqueta de pádel","Balón de baloncesto","Pelotas de tenis","Botas de fútbol NK"};
        String[] ofertados = {"no","no","no","no"};
        double[] precios = {150,25.50,4,50};
        
        int id;
        String producto;
        String ofertado;
        double precio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "rw");
            
            for (int i = 0; i < ids.length; i++) {
                
                //escribir el id
                ra.writeInt(ids[i]);
                
                //escribir el String producto
                StringBuffer sb1 = new StringBuffer(productos[i]);
                sb1.setLength(20); //max 20 caracteres
                ra.writeChars(sb1.toString());
                //escribir el String ofertado
                StringBuffer sb2 = new StringBuffer(ofertados[i]);
                sb2.setLength(2); // max 2 caracteres
                ra.writeChars(sb2.toString());
                
                //escribir el precio
                ra.writeDouble(precios[i]);
  
            }
            
            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void metodo3(){

        //generar XSL
        
        File file1 = new File(".\\ORIGEN\\estilo.xsl");
        try {
            file1.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] cadenas = {"<?xml version=\"1.0\" encoding='ISO-8859-1'?>",
                            "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">",
                            " <xsl:template match='/'>",
                            "   <html><xsl:apply-templates /></html>",
                            " </xsl:template>",
                            " <xsl:template match='listadeproductos'>",
                            "    <head><title>PRODUCTOS OFERTADOS</title></head>",
                            "    <body> ",
                            "    <h1>LISTA DE PRODUCTOS</h1>",
                            "    <table border='1'>",
                            "    <tr><th>Nombre</th><th>Precio</th></tr>",
                            "      <xsl:apply-templates select='producto' />",
                            "    </table>",
                            "    </body>",
                            " </xsl:template>",
                            " <xsl:template match='producto'>",
                            "   <tr><xsl:apply-templates /></tr>",
                            " </xsl:template>",
                            " <xsl:template match='nombre|precio'>",
                            "   <td><xsl:apply-templates /></td>",
                            " </xsl:template>",
                            "</xsl:stylesheet>"};
        
        try {
            FileWriter ficheroOut = new FileWriter(file1);
            
            for (String cadena : cadenas) {
                ficheroOut.write(cadena);
            }
  
            ficheroOut.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void metodo4(){

        File archivo = new File(".\\ORIGEN\\datos.dat");
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHAR = 2;
        
        Scanner sc = new Scanner(System.in);
        
        int id;
        byte[] cadena1 = new byte[40]; //20 char * C_CHAR
        String producto;
        byte[] cadena2 = new byte[4]; //2 char * C_CHAR
        String ofertado;
        double precio;
        
        
        int tamanio = C_INT + 20*C_CHAR + 2*C_CHAR + C_DOUBLE;
        
        long posicion;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "rw");
            
            do{
                System.out.println("Introduce la id del producto (0 para salir): ");
                id = sc.nextInt();
                posicion = (id-1)*tamanio;

                while (posicion >= archivo.length()){
                    System.out.println("ID: "+ id + " no existe producto...");
                    System.out.println("Introduce la id del producto (0 para salir): ");
                    id = sc.nextInt();
                    posicion = (id-1)*tamanio;
                }

                if (id != 0) {
                    ra.seek(posicion);
                    ra.skipBytes(C_INT + 20*C_CHAR); // se salta el id + el producto

                    //cambia ofertado a "si"
                    StringBuffer sb = new StringBuffer("si");
                    sb.setLength(2);
                    ra.writeChars(sb.toString());

                    System.out.println("\nProducto modificado");
                }
                
            }while (id != 0);
            
            ra.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void metodo5(){

        File archivo = new File(".\\ORIGEN\\datos.dat");
        File archivo2 = new File(".\\ORIGEN\\datos.xml");
        
        Result salida = new StreamResult(archivo2);
        
        try {
            archivo2.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final int C_INT = 4;
        final int C_DOUBLE = 8;
        final int C_CHAR = 2;
        
        Scanner sc = new Scanner(System.in);
        
        int id = 1;
        byte[] cadena1 = new byte[40]; //20 char * C_CHAR
        String producto;
        byte[] cadena2 = new byte[4]; //2 char * C_CHAR
        String ofertado;
        double precio;
        
        
        int tamanio = C_INT + 20*C_CHAR + 2*C_CHAR + C_DOUBLE;
        
        long posicion = (id-1)*tamanio;
        
        try {
            RandomAccessFile ra = new RandomAccessFile(archivo, "r");
            
            Document document = CrearDoc("Productos");
            
            while (posicion < (ra.length() - 1)){
                id = ra.readInt();
                ra.read(cadena1);
                producto = new String(cadena1);
                ra.read(cadena2);
                ofertado = new String(cadena2);
                precio = ra.readDouble();
                
                //// INTRODUCIR DATOS EN XML
                Element elemento = document.createElement("producto");
                document.getDocumentElement().appendChild(elemento);
                CrearNodo("id", String.valueOf(id), elemento, document);
                CrearNodo("nombre", producto, elemento, document);
                CrearNodo("ofertado", ofertado, elemento, document);
                CrearNodo("precio", String.valueOf(precio), elemento, document);          
                
                
                

//                System.out.printf("\nID: %d"
//                            + "\nProducto: %s"
//                            + "\nOfertado: %s"
//                            + "\nPrecio: %s"
//                            + "\n-------------------\n", 
//                            id, producto, ofertado, precio);
                
                id++;
                posicion = (id - 1)* tamanio;
            }
            
            //// CONSTRUIR XML
            ConstruirXML(document, salida);
                
            ra.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void metodo6(){

        String hojaEstilo = ".\\ORIGEN\\estilo.xsl";
        String datosAlumnos = ".\\ORIGEN\\datos.xml";
        File pagHTML = new File(".\\PUBLICADO\\ofertas.html");
        
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
    
    
    
    
    
    public static void menuPrincipal(){
        
        Scanner sc = new Scanner (System.in);
        
        System.out.println("============================"
                + "\n0. Salir"
                + "\n1. Crear estructura de carpetas"
                + "\n2. Alta datos productos"
                + "\n3. Generación de plantilla XLS"
                + "\n4. Seleccionar productos a ofertar"
                + "\n5. Generar archivo XML con productos a ofertar"
                + "\n6. Generación página web con productos a ofertar");
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 6){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    metodo1();
                    break;
                case 2:
                    metodo2();
                    break;
                case 3:
                    metodo3();
                    break;
                case 4:
                    metodo4();
                    break;
                case 5:
                    metodo5();
                    break;
                case 6:
                    metodo6();
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
        
    }
    
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
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
      return document;
    }

    public static void ConstruirXML(Document document, Result salida){
        try{
            Source source = new DOMSource(document);

            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            System.out.println("Error en la transformación");
        }
    }    

    public static void ConstruirXML(Document document){
        try{
            Source source = new DOMSource(document);
            Result salida = new StreamResult(System.out);
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,salida);
        } catch (TransformerException ex) {
            System.out.println("Error en la transformación");
        }
    }
    
}


