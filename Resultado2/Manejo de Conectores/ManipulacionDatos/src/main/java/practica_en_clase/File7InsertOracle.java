/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File7InsertOracle {
    
    static int numero = 20;
    static String apellido = "";
    static String oficio = "Soldador";
    static int director = 1;
    static Date fecha_alta = new Date(System.currentTimeMillis());
    static double salario = 2000;
    static double comision = 300;
    static int numero_dept = 4;
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "INSERT INTO empleados VALUES (?,?,?,?,?,?,?,?) ";

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
            //Comprueba que el numero de empleado no exista en la tabla empleados
            if (ex.getErrorCode() == 1){
                System.out.println("Esa fila ya ha sido insertada");
            }
            //Comprueba que el departamento existe en la tabla departamentos
            if (ex.getErrorCode() == 2291){
                System.out.println("El departameto no existe");
            }
            
            
        }
        
    }
    
    public static boolean comprobarDatos(){

        UtilidadesSQL util = new UtilidadesSQL();
        
        Connection conexion = util.ConexionOracle();
        
        //Comprueba si el salario es mayor > 0
        if (salario <= 0){
            System.out.println("El salario tiene que ser mayor que 0");
            return false;
        }
        //Comprueba que existe el director
        if (!util.devuelveDatos(conexion, "SELECT emp_no FROM emple where emp_no = "+ director)){
            System.out.println("El director no existe");
            return false;
        }
        //Comprueba que el apellido y oficio no sean nulos
        if (apellido.equals("") || oficio.equals("")){ 
            System.out.println("El apellido y el oficio tienen que contener datos");
            return false;
        }
        
        util.CerrarConexion(conexion);
        
        return true;
    }
    
}
