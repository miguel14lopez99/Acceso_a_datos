/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.Connection;
import java.sql.ResultSet;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File1InsertOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        Connection conexion = util.ConexionOracle();
        
        int dep=15;
        String dnombre="INFORMÁTICA";
        String loc="MADRID";
        
        String sql = "INSERT INTO departamentos VALUES("+ dep +",'"+ dnombre +"','"+ loc +"')";
        
        util.EjecutarSentencia(conexion, sql);       
        
        sql = "SELECT * FROM departamentos";
        
        util.MostrarSentencia(conexion, sql);
        
        util.CerrarConexion(conexion);
        
    }
    
}
