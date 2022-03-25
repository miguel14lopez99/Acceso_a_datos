/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioexist0;

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
public class EjercicioExist0 {

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
        
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        consulta = con.prepareExpression("/EMPLEADOS/fila_emple[DEPT_NO=10]");
        
        resultado = consulta.executeQuery();
        
        XQResultItem r_item;
        
        while (resultado.next()) {
            r_item = (XQResultItem) resultado.getItem();
            System.out.println("Elemento: " + r_item.getItemAsString(null));
        }
        
        XQExpression consulta2;
        consulta2 = con.createExpression();
        consulta2.executeCommand("update rename /EMPLEADOS/fila_emple as 'fila_emple'");
        
        con.close();
    }
    
}
