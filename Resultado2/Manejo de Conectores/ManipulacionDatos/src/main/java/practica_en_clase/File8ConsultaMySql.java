/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_en_clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static practica_en_clase.File7InsertMySql.apellido;
import static practica_en_clase.File7InsertMySql.comision;
import static practica_en_clase.File7InsertMySql.director;
import static practica_en_clase.File7InsertMySql.fecha_alta;
import static practica_en_clase.File7InsertMySql.numero;
import static practica_en_clase.File7InsertMySql.numero_dept;
import static practica_en_clase.File7InsertMySql.oficio;
import static practica_en_clase.File7InsertMySql.salario;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File8ConsultaMySql {
    
    public static void main(String[] args) {
        
        UtilidadesSQL util = new UtilidadesSQL();
        
        try {

            Connection conexion = util.ConexionOracle();
            
            String sql = "Select apellido, salario, oficio from emple";

            PreparedStatement sentencia = ;
            
        } catch (SQLException ex) {
            util.MostrarError(ex);

        }
        
    }
    
}
