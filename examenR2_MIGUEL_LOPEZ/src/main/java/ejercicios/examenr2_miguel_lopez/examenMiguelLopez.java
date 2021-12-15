/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.examenr2_miguel_lopez;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b15-04m
 */
public class examenMiguelLopez {
    
    private static UtilidadesSQL util;
    private static Connection conexion;
    private static String nombre;
    private static int anio;
    
    public static void main(String[] args) {
        
        util = new UtilidadesSQL();
        
        menuPrincipal();
        
        
        util.CerrarConexion(conexion);
        
    }
    
    //cambiar conexion
    public static void conectaBBDD(){
        
        Scanner sc = new Scanner(System.in);
        
//        System.out.println("Introduce tu usuario: ");
//        String usuario = sc.next();
//        System.out.println("Introduce tu contraseña: ");
//        String pass = sc.next();
//        System.out.println("Introduce la IP: ");
//        String ip = sc.next();
//        System.out.println("Introduce el puerto: ");
//        String puerto = sc.next();
//        System.out.println("Introduce el nombre de la BD: ");
//        String bd = sc.next();
        
        // DEFAULT LUEGO BORRAR
        String usuario = "DAM2";
        String pass = "dam2";
        String ip = "@localhost";
        String puerto = "1521";
        String bd = "PDB18C";
        
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:"+ip+":"+puerto+"/"+bd;
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", usuario);
            propiedades.setProperty("password", pass);
            
            Class.forName(driver);
            
            conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            
            
        } catch ( SQLException ex) {
            util.MostrarError(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("El driver no funciona");
        }
    }
    
    public static void mostrarTodosDatos(){
        
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Introduce la fecha de inicio: ");
            String fechaInicio = sc.next();
            System.out.println("Introduce la fecha de fin: ");
            String fechaFin = sc.next();
            
            //se podria comprobar el formato de las fechas
            
            String sql = "SELECT * FROM PELICULA WHERE FECHA_EMISION BETWEEN ? AND ?";
            System.out.println(sql);
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, fechaInicio);
            sentencia.setString(2, fechaFin);
            
            int filas = sentencia.executeUpdate();
            
            ResultSet rs = sentencia.getResultSet();
            
            int suma = 0;
            
            while(rs.next()){
                
                System.out.printf("%10s, %10d, %10s, %10s, %10d, %10s, %10s\n",
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
                
                suma += rs.getInt(5);
                
            }
            
            System.out.println("La suma de las duraciones de las peliculas selecionadas es:");
            System.out.println(suma);
            
            sc.close();
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
    public static void insertarFila(){
        
        try {
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Introduce el nombre: ");
            String nombr = sc.next();
            System.out.println("Introduce el año: ");
            int ani = sc.nextInt();
            System.out.println("Introduce el director: ");
            String director = sc.next();
            System.out.println("Introduce nacionalidad: ");
            String nacionalidad = sc.next();
            System.out.println("Introduce la duración: ");
            int duracion = sc.nextInt();
            System.out.println("Introduce el idioma: ");
            String idioma = sc.next();
            System.out.println("Introduce la fecha de emision: ");
            String fecha = sc.next();
            
            String sql = "INSERT INTO PELICULA(NOMBRE_PELICULA, AÑO, DIRECTOR, NACIONALIDAD, DURACION, IDIOMA, FECHA_EMISION) "
                    + "VALUES(?,?,?,?,?,?,?)";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, nombr);
            sentencia.setInt(2, ani);
            sentencia.setString(3, director);
            sentencia.setString(4, nacionalidad);
            sentencia.setInt(5, duracion);
            sentencia.setString(6, idioma);
            sentencia.setString(7, fecha);
            
            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");                     
            
            sc.close();
            
        } catch (SQLException ex) {
                        
            //Comprueba que el numero de empleado no exista en la tabla empleados
            if (ex.getMessage().contains("ORA-00001")){
                System.out.println("Esa fila ya ha sido insertada");
            }
            //Comprueba que el departamento existe en la tabla departamentos
            else if (ex.getMessage().contains("ORA-02291")){
                System.out.println("Ese dato no existe en la base de datos");
            }
            //Comprueba que has introducido mas datos de los necesarios
            else if (ex.getMessage().contains("ORA-00913")){
                System.out.println("Has introducido mas valores de los necesarios");
            }
            else
            {
                util.MostrarError(ex);
            }
            
        }
   
    }
    
    //esto funciona mal
    public static void solicitarPK(){
        
        Scanner sca = new Scanner(System.in);
        
        System.out.println("Introduce el nombre: ");
        String nombre = sca.next();
        System.out.println("Introduce el año: ");
        int anio = sca.nextInt();
        
        System.out.println(nombre+" "+anio);
        
        sca.close();
        
    }
    
    public static void eliminarFila(){
        
        try {
            String sql = "DELETE FROM PELICULA WHERE NOMBRE_PELICULA = ? AND AÑO = ?";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, nombre);
            sentencia.setInt(2, anio);
            
            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n"); 
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
    public static void mostrarFila(){
        
        try {
            String sql = "SELECT * FROM PELICULA WHERE NOMBRE_PELICULA = ? AND AÑO = ?";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, nombre);
            sentencia.setInt(2, anio);
            
            
            int filas = 0;
            sentencia.executeUpdate();

            ResultSet rs = sentencia.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();           
            
            while(rs.next()){
                
                System.out.printf("%10s, %10d, %10s, %10s, %10d, %10s, %10s\n",
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)); 
                
                filas = rs.getRow();
            }
    
            System.out.print("\n"+filas +" filas afectadas.\n");
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
    public static void modificaFila(){

        try {
            Scanner sca = new Scanner(System.in);
            
            System.out.println("Introduce nacionalidad: ");
            String nacionalidad = sca.next();
            System.out.println("Introduce la duración: ");
            int duracion = sca.nextInt();
            System.out.println("Introduce el idioma: ");
            String idioma = sca.next();
            
            String sql = "UPDATE PELICULA SET nacionalidad = ?, duracion = ?, idioma = ? WHERE NOMBRE_PELICULA = ? AND AÑO = ?";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, nacionalidad);
            sentencia.setInt(2, duracion);
            sentencia.setString(3, idioma);
            sentencia.setString(4, nombre);
            sentencia.setInt(5, anio);
            
            int filas = sentencia.executeUpdate();
            System.out.print("\n"+filas +" filas afectadas.\n");       
            
            sca.close();
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
    public static void llamarFuncion(){
        
        Scanner scanner = new Scanner(System.in);
        
        int duracion = scanner.nextInt();
        int ani = scanner.nextInt();        
        
        try {
            String sql = "{call CAMBIAR_DURACION(?,?)}";
            
            CallableStatement llamada = conexion.prepareCall(sql);
            
            llamada.registerOutParameter(2,Types.VARCHAR);
            
            llamada.setInt(1,duracion);
            llamada.setInt(2,ani);
            
            llamada.executeUpdate();
            
        } catch (SQLException ex) {
            util.MostrarError(ex);
        }
        
    }
    
    public static void menuPrincipal(){
        
        Scanner sc = new Scanner (System.in);

        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============================"
                + "\n0. Salir"
                + "\n1. Conectar a la BBDD"
                + "\n2. Mostrar todas las películas"
                + "\n3. Alta película"
                + "\n4. Código de película"
                + "\n5. Eliminar película"
                + "\n6. Mostrar datos película"
                + "\n7. Modificar datos película"
                + "\n8. Añadir tiempo por anuncios");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 6){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    conectaBBDD();
                    break;
                case 2:
                    mostrarTodosDatos();
                    break;
                case 3:
                    insertarFila();
                    break;
                case 4:
                    solicitarPK();
                    break;
                case 5:
                    eliminarFila();
                    break;
                case 6:
                    mostrarFila();
                    break;
                case 7:
                    modificaFila();
                    break;
                case 8:
                    llamarFuncion();
                    break;
                case 0:
                    System.out.println("Adios");
                    break;
            }
        }
        
    }
}
