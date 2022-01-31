/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecicioexist1;

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
public class EjecicioExist1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XQException {
        XQDataSource server = new ExistXQDataSource();
        server.setProperty("serverName", "localhost");
        server.setProperty("port", "8080");
        server.setProperty("user", "dam2");
        server.setProperty("password", "dam2");
        
        XQConnection con = server.getConnection();
        
//        XQExpression consulta;
//        consulta = con.createExpression();
//        consulta.executeCommand("update insert\n" +
//                                "<empleado salario=\"2340\">\n" +
//                                "    <puesto>Técnico</puesto>\n" +
//                                "    <nombre>Pedro Fraile</nombre>\n" +
//                                "</empleado>\n" +
//                                "into\n" +
//                                "/universidad/departamento[2]");

//        XQExpression consulta;
//        consulta = con.createExpression();
//        consulta.executeCommand("for $em in /universidad/departamento[codigo='MAT1']/empleado\n" +
//                                "let $sal := data($em/@salario)\n" +
//                                "return update value $em/@salario\n" +
//                                "with data($sal)+100");
//        
//        con.close();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el código de departamento: ");
        String dept = sc.next();
        
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        consulta = con.prepareExpression("/universidad/departamento[codigo='"+ dept +"']/empleado");
        
        resultado = consulta.executeQuery();
        
        XQResultItem r_item;
        
        while (resultado.next()) {
            r_item = (XQResultItem) resultado.getItem();            
            String cad = r_item.getItemAsString(null).replace(" xmlns=\"\"","");         
            System.out.println(cad);
        }

    }
    
}
