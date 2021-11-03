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
public class File2ModifOracle {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
            
            Connection conexion = util.ConexionOracle();
            
            String sql = "UPDATE empleados SET salario = (salario+100)"
                    + "WHERE dept_no = 15";
            
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            sql = "SELECT * FROM empleados";
            
            util.MostrarSentencia(conexion, sql);
            
            util.CerrarConexion(conexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(File2ModifOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
