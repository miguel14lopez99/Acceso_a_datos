/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File2SelectSQLite {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el número de departamento: ");
        int dept_no = sc.nextInt();
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
        
            Connection conexion = util.ConexionSQLite();

            String sql = "SELECT apellido,oficio,salario FROM empleados " +
                            "WHERE dept_no = " + dept_no;

            ResultSet result = util.EjecutarSentencia(conexion, sql);
            while(result.next()){       
                System.out.printf("%s, %s, %d %n",
                        result.getString(1),
                        result.getString(2),
                        result.getInt(3));

            }

            result.close();
            util.CerrarConexion(conexion);
        
        } catch (SQLException ex) {
            Logger.getLogger(File2SelectSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
