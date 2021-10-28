/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_casa;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File1SelectSQLite {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
        
            Connection conexion = util.ConexionSQLite();

            String sql = "SELECT * FROM departamentos";

            ResultSet result = util.EjecutarSentencia(conexion, sql);
            
            while(result.next()){       
                System.out.printf("%d, %s, %s %n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));

            }

            result.close();
            util.CerrarConexion(conexion);
        
        } catch (SQLException ex) {
            Logger.getLogger(File1SelectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
