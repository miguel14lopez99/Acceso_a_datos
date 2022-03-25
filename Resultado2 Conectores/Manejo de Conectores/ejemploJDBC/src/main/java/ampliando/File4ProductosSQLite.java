/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ampliando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica_en_clase.File2SelectSQLite;
import sql.utilidades.UtilidadesSQL;

/**
 *
 * @author b15-04m
 */
public class File4ProductosSQLite {
    
    public static void main(String[] args) {
        
        try {
            UtilidadesSQL util = new UtilidadesSQL();
        
            Connection conexion = util.ConexionSQLite();

            String sql = "SELECT p.id,p.descripcion,p.stockAnual,p.stockMinimo,p.PVP FROM productos p\n" +
                            "INNER JOIN ventas v on p.id = v.idProducto\n" +
                            "INNER JOIN clientes c on c.id = v.idCliente;";

            ResultSet result = util.EjecutarSentencia(conexion, sql);
            while(result.next()){       
                System.out.printf("%d, %s, %d, %d, %d %n",
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5));
            }

            result.close();
            util.CerrarConexion(conexion);
        
        } catch (SQLException ex) {
            Logger.getLogger(File4ProductosSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
