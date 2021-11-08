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
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File4InsertMySql {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            int dep=25;
            String dnombre="NÓMINAS";
            String loc="VALDEPEÑAS";
            
            String sql = "INSERT INTO departamentos VALUES(?,?,?)";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setInt(1, dep);
            sentencia.setString(2, dnombre);
            sentencia.setString(3, loc);
            
            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");

            sql = "SELECT * FROM departamentos";
            
            util.MostrarSentencia(conexion, sql);
            
            util.CerrarConexion(conexion);
        } catch (SQLException ex) {
            //util.MostrarError(ex);
            
            if (ex.getMessage().contains("ORA-00001")){
                System.out.println("Esa fila ya ha sido insertada");
            }
        }
        
    }
    
}
