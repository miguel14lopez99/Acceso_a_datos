/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica_en_casa.File2ModifOracle;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File5ModifMySql {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            int dept = 15;
            int aumento = 100;
            
            String sql = "UPDATE empleados SET salario = (salario+ ? )"
                    + "WHERE dept_no = ? ";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setInt(1, aumento);
            sentencia.setInt(2, dept);
            
            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            sql = "SELECT * FROM empleados";
            
            util.MostrarSentencia(conexion, sql);
            
            util.CerrarConexion(conexion);
            
        } catch (SQLException ex) {
            util.MostrarError(ex);

            
        }
        
    }
    
}
