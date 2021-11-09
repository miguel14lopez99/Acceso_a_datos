/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File8ConsultaOracle {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "SELECT E.APELLIDO, E.SALARIO, E.OFICIO, D.DNOMBRE \n" +
                         "FROM EMPLEADOS E INNER JOIN DEPARTAMENTOS D ON E.DEPT_NO = D.DEPT_NO";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            int filas = sentencia.executeUpdate();
            
            ResultSet rs = sentencia.getResultSet();
            
            int suma = 0;
            
            while(rs.next()){
                
                System.out.printf("%10s, %10d, %10s, %10s\n",
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4));
                
                suma += rs.getInt(2);
            }
            
            double media = (double)suma/filas;
            
            System.out.println("Número de empleados: "+filas);
            System.out.printf("Media de los salarios: %.2f",media);

        } catch (SQLException ex) {
            util.MostrarError(ex);

        }
        
    }
    
}
