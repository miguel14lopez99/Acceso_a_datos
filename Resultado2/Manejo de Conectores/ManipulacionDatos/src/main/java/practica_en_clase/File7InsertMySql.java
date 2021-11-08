/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File7InsertMySql {
    
    static int numero = 15;
    static String apellido = "dfg";
    static String oficio = "dfg";
    static int director = 1;
    static Date fecha_alta = Date.valueOf("20/03/2021");
    static double salario = 2;
    static double comision = 3;
    static int numero_dept = 4;
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "INSERT INTO EMPLE VALUES (?,?,?,?,?,?,?,?) ";

            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(1, numero);
            sentencia.setString(2, apellido);
            sentencia.setString(3, oficio);
            sentencia.setInt(4, director);
            sentencia.setDate(5, fecha_alta);
            sentencia.setDouble(6, salario);
            sentencia.setDouble(7, comision);
            sentencia.setInt(8, numero_dept);

            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");
            
            
            //MOSTRAR RESULTADO
            
            sql = "SELECT * FROM empleados";
            
            util.MostrarSentencia(conexion, sql);
            
            util.CerrarConexion(conexion);
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
            
            if (ex.getErrorCode() == 1){
                System.out.println("Esa fila ya ha sido insertada");
            }
            if (ex.getErrorCode() == 2291){
                System.out.println("El departameto no existe");
            }
            
            
        }
        
    }
    
    public static boolean comprobarDatos(){
        
        //Comprueba si el salario es mayor > 0
        if (salario <= 0){
            System.out.println("El salario tiene que ser mayor que 0");
            return false;
        }
        
        String sql = "SELECT EMP_NO FROM EMPLE WHERE EMP_NO = " + director;
                
        ResultSet rs = util.EjecutarSentencia(conexion, sql);

        if (!rs.next()){
            System.out.println("El director no existe");
        }
        
    }
    
}
