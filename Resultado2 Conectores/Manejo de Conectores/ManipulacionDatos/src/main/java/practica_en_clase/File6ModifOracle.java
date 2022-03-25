/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File6ModifOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            int dept = 15;
            int disminucion = 12;

            String sql = "UPDATE empleados SET salario = (salario - (salario * ? /100) )"
                + "WHERE dept_no = ? ";

            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, disminucion);
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
