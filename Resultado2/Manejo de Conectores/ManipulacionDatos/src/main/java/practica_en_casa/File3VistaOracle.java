/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File3VistaOracle {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
            
            Connection conexion = util.ConexionOracle();
            
            String sql = "Create or replace view vistaDepart as \n"
                    + "(Select dept_no, dnombre, "
                    + "(Select count(*) from empleados e where e.dept_no = d.dept_no) as trabajadores , "
                    + "(SELECT AVG(salario) FROM empleados e where dept_no = d.dept_no) as media\n from departamentos d)";
            
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            sql = "SELECT * FROM vistaDepart";
            
            util.MostrarSentencia(conexion, sql);
            
            
            sentencia.close();
            util.CerrarConexion(conexion);
            
        } catch (SQLException e) {
            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +e.getMessage());
            System.out.println ("SQL Estado: " +e.getSQLState());
            System.out.println ("Código de error: " +e.getErrorCode());
        }
        
    }
    
}
