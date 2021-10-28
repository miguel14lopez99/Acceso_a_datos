/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author chipi
 */
public class File1SelectOracle {
    
    public static void main(String[] args) {
        
//        try {
            UtilidadesSQL util = new UtilidadesSQL();
            
            Connection conexion = util.ConexionOracle();

            String sql = "SELECT * FROM departamentos";

//            ResultSet result = util.EjecutarSentencia(conexion, sql);
//            
//            while(result.next()){       
//                System.out.printf("%d, %s, %s %n",
//                        result.getInt(1),
//                        result.getString(2),
//                        result.getString(3));
//            }
            
            util.MostrarSentencia(conexion, sql);

//            result.close();
            util.CerrarConexion(conexion);
        
//        } catch (SQLException ex) {
//            Logger.getLogger(File1SelectOracle.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
}
