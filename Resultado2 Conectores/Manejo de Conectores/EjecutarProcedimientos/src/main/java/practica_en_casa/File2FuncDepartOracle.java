/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author chipi
 */
public class File2FuncDepartOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        String dep = "15";
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "{?=call departamento (?)}";
            
            CallableStatement llamada = conexion.prepareCall(sql);
            
            llamada.registerOutParameter(1,Types.VARCHAR);
            
            llamada.setInt(2,Integer.parseInt(dep));
            
            llamada.executeUpdate();
            
            String salida1 = llamada.getString(1);
            System.out.println("Salida 1 = "+salida1);
            
            
        } catch (SQLException e) {
            util.MostrarError(e);
        }
        
    }
    
}
