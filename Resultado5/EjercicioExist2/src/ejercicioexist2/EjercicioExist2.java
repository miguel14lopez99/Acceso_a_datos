/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioexist2;

import java.util.Scanner;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author chipi
 */
public class EjercicioExist2 {

    /**
     * @param args the command line arguments
     */
    
    private static XQConnection con;
    
    public static void main(String[] args) throws XQException {
        
        XQDataSource server = new ExistXQDataSource();
        server.setProperty("serverName", "localhost");
        server.setProperty("port", "8080");
        server.setProperty("user", "dam2");
        server.setProperty("password", "dam2");
        
        con = server.getConnection();
        
        //insertar departamento
        //Insertdep();
        //eliminar departamento
        //Borradep();
        //modificar departamento
        Modificadep();
        
        con.close();
        
    }
    
    public static void Insertdep() throws XQException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el nº: ");
        int num = sc.nextInt();
        sc.nextLine(); //LIMPIA EL SCANNER
        
        //COMPROBAR SI EL DEP YA EXISTE
        Boolean existeDep = false;
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        consulta = con.prepareExpression("/departamentos/DEP_ROW[DEPT_NO = "+ num +"]");
        
        resultado = consulta.executeQuery();
        
        XQResultItem r_item;
        
        if (resultado.next())
            existeDep = true;
        
        if(!existeDep){
            System.out.println("Introduce el nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce la localización: ");
            String loc = sc.nextLine();
            
            XQExpression consulta2;
            consulta2 = con.createExpression();
            consulta2.executeCommand("update insert\n" +
                                    "<DEP_ROW>\n" +
                                    "  <DEPT_NO>"+ num +"</DEPT_NO>\n" +
                                    "  <DNOMBRE>"+ nombre +"</DNOMBRE>\n" +
                                    "  <LOC>"+ loc +"</LOC>\n" +
                                    "</DEP_ROW>\n" +
                                    "into\n" +
                                    "/departamentos");
        } else {
            System.out.println("El departamento ya existe");
        }
             
    }
    
    public static void Borradep() throws XQException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el nº: ");
        int num = sc.nextInt();
        sc.nextLine(); //LIMPIA EL SCANNER
        
        //COMPROBAR SI EL DEP YA EXISTE
        Boolean existeDep = false;
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        consulta = con.prepareExpression("/departamentos/DEP_ROW[DEPT_NO = "+ num +"]");
        
        resultado = consulta.executeQuery();
        
        XQResultItem r_item;
        
        if (resultado.next())
            existeDep = true;
        
        if(existeDep){
            XQExpression consulta2;
            consulta2 = con.createExpression();
            consulta2.executeCommand("update delete /departamentos/DEP_ROW[DEPT_NO = "+ num +"]");
        } else {
            System.out.println("El departamento no existe");
        }                
    }
    
    public static void Modificadep() throws XQException{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el nº: ");
        int num = sc.nextInt();
        sc.nextLine(); //LIMPIA EL SCANNER
        
        //COMPROBAR SI EL DEP YA EXISTE
        Boolean existeDep = false;
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        consulta = con.prepareExpression("/departamentos/DEP_ROW[DEPT_NO = "+ num +"]");
        
        resultado = consulta.executeQuery();
        
        XQResultItem r_item;
        
        if (resultado.next())
            existeDep = true;
        
        if(existeDep){
            System.out.println("Introduce el nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce la localización: ");
            String loc = sc.nextLine();
            
            XQExpression consulta2;
            consulta2 = con.createExpression();
            consulta2.executeCommand("update value /departamentos/DEP_ROW[DEPT_NO = 10]/DNOMBRE\n" +
                                    "with '"+ nombre +"'");
            
            XQExpression consulta3;
            consulta3 = con.createExpression();
            consulta3.executeCommand("update value /departamentos/DEP_ROW[DEPT_NO = 10]/LOC\n" +
                                    "with '"+ loc +"'");
            
        } else {
            System.out.println("El departamento no existe");
        }
    }
    
    
    
}
