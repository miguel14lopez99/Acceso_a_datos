/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File1InsertOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            int dep=16;
            String dnombre="CONTABILIDAD";
            String loc="CIUDAD REAL";
            
            String sql = "INSERT INTO departamentos VALUES("+ dep +",'"+ dnombre +"','"+ loc +"')";
            
            Statement sentencia = conexion.createStatement();
            int filas = sentencia.executeUpdate(sql);
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            sql = "SELECT * FROM departamentos";
            
            util.MostrarSentencia(conexion, sql);
            
            util.CerrarConexion(conexion);
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
}
