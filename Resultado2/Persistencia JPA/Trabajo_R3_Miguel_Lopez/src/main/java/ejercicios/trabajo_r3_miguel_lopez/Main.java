/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author b15-04m
 */
public class Main {
    
    private static Connection conexion;
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    private static Campanias campania;
    private static Agricultores agricultor;
    private static Trabajos trabajo;
    
    public static void main(String[] args) {
        
       inicializarFactory();
        
        //menu
        menuPrincipal();
        
    }
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("ejercicios_Trabajo_R3_Miguel_Lopez_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
        
    }
    
    public static void menuPrincipal(){
        
        Scanner sc = new Scanner (System.in);

        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ MENÚ ============"
                + "\n0. Salir"
                + "\n1. Conexión a BBDD"
                + "\n2. Crear estructura de tablas / Reiniciar datos"
                + "\n3. Reiniciar Datos"
                + "\n4. Aplicación");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 4){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    ConexionBBDD();
                    break;
                case 2:                                                      
                    CrearTablas();
                    break;
                case 3:
                    ReiniciarDatos();
                    break;
                case 4:
                    MenuApp();
                    break;
                case 0:
                    System.out.println("Adios");
                
                    try { //cerrar bbdd                        
                        conexion.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    System.exit(0);
                    break;

            }
        }
        
    }
    
    public static void ConexionBBDD(){
        
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

        } catch ( SQLException ex ) {
            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +ex.getMessage());
            System.out.println ("SQL Estado: " +ex.getSQLState());
            System.out.println ("Código de error: " +ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            System.out.println("El driver no funciona");
        }
        
        System.out.println("\nConectado\n");
        
    }
    
    public static void CrearTablas(){
        String sql;
        
        try {

            Statement sentencia = conexion.createStatement();
            
            //Crear tabla Campañas                     
            sql = "CREATE TABLE CAMPANIAS(\n" +
                  "    ID_CAMP      NUMBER,\n" +
                  "    NOMBRE_CAMP  VARCHAR2(20),\n" +
                  "    DESCRIPCION  VARCHAR2(50),\n" +
                  "    CONSTRAINT PK_CAMPANIAS PRIMARY KEY (ID_CAMP)\n)";
            sentencia.execute(sql);
            
            //Crear tabla Agricultores            
            sql = "CREATE TABLE AGRICULTORES(\n" +
                  "    ID_AGRI      NUMBER,\n" +
                  "    NOMBRE_AGRI  VARCHAR2(20),\n" +
                  "    TELEFONO     NUMBER(9),\n" +
                  "    ID_CAMP      NUMBER,\n" +
                  "    CONSTRAINT PK_AGRICULTORES PRIMARY KEY (ID_AGRI)\n)";
            sentencia.execute(sql);
            
            //Crear tabla Trabajos           
            sql = "CREATE TABLE MAQUINAS(\n" +
                  "    ID_MAQUINA   NUMBER,\n" +
                  "    NRO_BASTIDOR VARCHAR2(17),\n" +
                  "    ULT_REVISION DATE,\n" +
                  "    ID_AGRI      NUMBER,\n" +
                  "    CONSTRAINT PK_MAQUINAS PRIMARY KEY (ID_MAQUINA)\n)";
            sentencia.execute(sql);
            
            //Crear foreign keys
            sql = "ALTER TABLE AGRICULTORES ADD CONSTRAINT FK_AGRI_CAMP FOREIGN KEY (ID_CAMP) REFERENCES CAMPANIAS(ID_CAMP)";
            sentencia.execute(sql);
            sql = "ALTER TABLE MAQUINAS ADD CONSTRAINT FK_MAQUINA_AGRI FOREIGN KEY (ID_AGRI) REFERENCES AGRICULTORES(ID_AGRI)";
            sentencia.execute(sql);
            
            System.out.println("\nTablas Creadas\n");
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void ReiniciarDatos(){
        
        String sql;
        
        try {
            
            String[] nombCamp = {"ALMENDRA", "ACEITUNA", "VENDIMIA", "PISTACHO", "TRATAMIENTO", "SEMBRADO"};
            String[] descCamp = {"La almedra se recoge con paraguas", "La aceituna se recoge con paraguas o pinza", 
                                 "La uva se recoge con vendimiadora", "El pistacho se recoge con paraguas", 
                                 "Los campos se tratan con una sulfatadora", "Los campos se siembran con una sembradora"};
            
            String[] nombAgri = {"Miguel", "Pepe", "Paco", "Elena", "Javier", "Benito", "Manolo", "Fran", "Jorge", "Daniel", "Pablo", "Luis"};
            int[] telefonos = {666333999, 111222333, 444555666, 777888999, 111555999, 333555777, 444222666, 666888444, 222555888, 444555666, 111666555, 333444555};
            int[] campanias = {1,1,2,2,3,3,4,4,5,5,6,6};
            
            String[] nombTrab = {"Miguel", "Pepe", "Paco", "Elena", "Javier", "Benito", "Manolo", "Fran", "Jorge", "Daniel", "Pablo", "Luis"};
            String[] maquina = {"Miguel", "Pepe", "Paco", "Elena", "Javier", "Benito", "Manolo", "Fran", "Jorge", "Daniel", "Pablo", "Luis"};
            int[] agricultor = {1,1,2,2,3,3,4,4,5,5,6,6};
            
            Statement sentencia =  conexion.createStatement();
            
            //Borrado de registros
            
            sql = "DELETE FROM CAMPANIAS";
            sentencia.execute(sql);
            
            sql = "DELETE FROM AGRICULTORES";
            sentencia.execute(sql);
            
            sql = "DELETE FROM TRABAJOS";
            sentencia.execute(sql);
            
            sentencia.close();
            
            //Inserción de registros
            //CAMPANIAS
            for (int i = 0; i < nombCamp.length; i++) {
                campania = new Campanias();
                campania.setIdCamp(BigDecimal.valueOf(i+1));
                campania.setNombreCamp(nombCamp[i]);
                campania.setDescripcion(descCamp[i]);
                
                entitymanager.getTransaction().begin();
                entitymanager.persist(campania);
                entitymanager.getTransaction().commit();
            }
            
            //AGRICULTORES
            for (int i = 0; i < nombCamp.length; i++) {
                agricultor = new Agricultores();
                agricultor.setIdAgri(BigDecimal.valueOf(i+1));
                agricultor.setNombreAgri(nombAgri[i]);
                agricultor.setTelefono(telefonos[i]);
                
                //buscar campania correspondiente
                entitymanager.getTransaction().begin();
                
                campania = entitymanager.find(Campanias.class, BigDecimal.valueOf(campanias[i]));
                agricultor.setIdCamp(campania);
                
                
                entitymanager.persist(agricultor);
                entitymanager.getTransaction().commit();
            }
            
            //TRABAJOS
            
                        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void MenuApp(){
        
        Scanner sc = new Scanner (System.in);           
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú pricipal"
                + "\n1. Tabla 2 (sin JPQL)"
                + "\n2. Tabla 1 (con JPQL no almacenadas)"
                + "\n3. Tabla 1 (sin JPQL)"
                + "\n4. Tabla 3 (con JPQL)");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 4){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    //metodo1();
                    System.out.println("metodo1");
                    break;
                case 2:
                    //metodo2();
                    System.out.println("metodo2");
                    break;
                case 3:
                    //metodo3();
                    System.out.println("metodo3");
                    break;
                case 4:
                    //metodo4();
                    System.out.println("metodo4");
                    break;
                case 0:
                    menuPrincipal();
                    break;
            }
        }
        
    }
    
}
