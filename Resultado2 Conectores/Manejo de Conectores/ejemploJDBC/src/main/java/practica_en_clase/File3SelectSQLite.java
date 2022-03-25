/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File3SelectSQLite {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
        
            Connection conexion = util.ConexionSQLite();

            String sql = "SELECT e.apellido, e.salario, d.dnombre \n" +
                            "FROM empleados e, departamentos d\n" +
                            "WHERE e.dept_no = d.dept_no \n" +
                            "	and e.apellido = (SELECT apellido FROM empleados \n" +
                            "					  WHERE salario = (SELECT MAX(salario) FROM empleados))";

            ResultSet result = util.EjecutarSentencia(conexion, sql);
            while(result.next()){       
                System.out.printf("%s, %d, %s %n",
                        result.getString(1),
                        result.getInt(2),
                        result.getString(3));

            }

            result.close();
            util.CerrarConexion(conexion);
        
        } catch (SQLException ex) {
            Logger.getLogger(File3SelectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
