
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author b15-04m
 */
public class LlamadaProcedimiento {
   
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        String dep = "15";
        
        try {
                        
            Connection conexion = util.ConexionOracle();
            
            String sql = "{call NOMBRE_DEPART(?,?)}";
            
            CallableStatement llamada = conexion.prepareCall(sql);
            
            llamada.registerOutParameter(2,Types.VARCHAR);
            
            llamada.setInt(1,Integer.parseInt(dep));
            
            llamada.executeUpdate();
            
            String salida1 = llamada.getString(2);
            System.out.println("Salida = "+salida1);
            
        } catch (SQLException e) {
            util.MostrarError(e);
        }
        
    }
}
