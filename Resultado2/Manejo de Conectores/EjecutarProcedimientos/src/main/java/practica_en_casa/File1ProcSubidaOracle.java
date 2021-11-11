/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author chipi
 */
public class File1ProcSubidaOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        int dept = 15;
        int subida = 100;
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "{call SUBIDA_SALARIO(?,?)}";
            
            CallableStatement llamada = conexion.prepareCall(sql);
            
            llamada.setInt(1, dept);
            llamada.setInt(2, subida);
            
            int filas = llamada.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            util.CerrarConexion(conexion);
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
}
