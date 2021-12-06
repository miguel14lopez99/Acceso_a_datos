/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.trabajo_r3_miguel_lopez;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static Maquinas maquina;
    private final static Scanner sc = new Scanner (System.in);
    
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
                
                    if(conexion != null){
                        try { //cerrar bbdd                        
                            conexion.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    sc.close();
                    
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
        
        //comprobar que las tablas ya están creadas
        
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
            
            //Crear tabla Maquinas           
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
            
            String[] nBastidor = {"JT2BG22K3Y0485107", "1GNKVGED5CJ196120", "3FAFP13P41R199033", "5FNRL38489B407103", "JH4KA3250JC001616", "JH4KA7680RC011845", 
                                  "SCBBR53W36C034889", "WAUMGAFL1DA105812", "JH4KA7670RC000738", "JH4DC4460SS000830", "JA4MW31R12J026290", "WAUMGAFL1DA105812"};
            String[] revisiones = {"25-01-2021", "16-04-2021", "27-04-2021", "07-05-2021", "15-06-2021", "16-06-2021", 
                                   "23-07-2021", "27-07-2021", "28-07-2021", "08-09-2021", "25-11-2021", "22-12-2021"};
            int[] agricultores = {1,2,3,4,5,6,7,8,9,10,11,12};
            
            Statement sentencia =  conexion.createStatement();
            
            //Borrado de registros
            
            sql = "DELETE FROM MAQUINAS";
            sentencia.execute(sql);
            
            sql = "DELETE FROM AGRICULTORES";
            sentencia.execute(sql);
            
            sql = "DELETE FROM CAMPANIAS";
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
            for (int i = 0; i < nombAgri.length; i++) {
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
            
            //MAQUINAS
            for (int i = 0; i < nBastidor.length; i++) {
                maquina = new Maquinas();
                maquina.setIdMaquina(BigDecimal.valueOf(i+1));
                maquina.setNroBastidor(nBastidor[i]);
                try {
                    maquina.setUltRevision(new SimpleDateFormat("dd-MM-yyyy").parse(revisiones[i]));
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //buscar agricultor correspondiente
                entitymanager.getTransaction().begin();
                
                agricultor = entitymanager.find(Agricultores.class, BigDecimal.valueOf(agricultores[i]));
                maquina.setIdAgri(agricultor);
                                
                entitymanager.persist(maquina);
                entitymanager.getTransaction().commit();
            }
            
            System.out.println("\nDatos Reiniciados\n");
                        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void MenuApp(){
                      
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú pricipal"
                + "\n1. Tabla 2 (sin JPQL)"
                + "\n2. Tabla 1 (con JPQL no almacenadas)"
                + "\n3. Tabla 1 (sin JPQL) y Tabla 3 (con JPQL)");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 3){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    menu1();
                    break;
                case 2:
                    menu2();
                    break;
                case 3:
                    menu3();
                    break;
                case 0:
                    menuPrincipal();
                    break;
            }
        }
        
    }
    
    public static void menu1(){              
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú de la app"
                + "\n1. Inserción de registro"
                + "\n2. Modificación de registro"
                + "\n3. Borrado registro"
                + "\n4. Mostrar los datos de un registro");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 4){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    AgricultoresOP2.insJPQLT2(entitymanager);
                    break;
                case 2:
                    AgricultoresOP2.modifJPQLT2(entitymanager);
                    break;
                case 3:
                    AgricultoresOP2.borradoJPTLT2(entitymanager);
                    break;
                case 4:
                    AgricultoresOP2.consultaJPTLT2(entitymanager);
                    break;
                case 0:
                    MenuApp();
                    break;
            }
        }
        
    }
    
    public static void menu2(){              
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú de la app"
                + "\n1. Inserción de registro"
                + "\n2. Modificación de registro"
                + "\n3. Borrado registro"
                + "\n4. Mostrar los datos de un registro");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 4){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    CampaniasOP1.insJPQLT1(entitymanager);
                    break;
                case 2:
                    CampaniasOP1.modifJPQLT1(entitymanager);
                    break;
                case 3:
                    CampaniasOP1.borradoJPTLT1(entitymanager);
                    break;
                case 4:
                    CampaniasOP1.consultaJPQLT1(entitymanager);
                    break;
                case 0:
                    MenuApp();
                    break;
            }
        }
        
    }
    
    public static void menu3(){              
        
        int opt = -1;
        
        while(opt != 0){ //opción de salida
            
            System.out.println("============ APP ============"
                + "\n0. Volver al menú de la app"
                + "\n1. Consulta sin JPQL Tabla 1"
                + "\n2. Modificación con JPQL Tabla 3"
                + "\n3. Borrado con JPQL Tabla 3");
            
            System.out.println("\nIntroduce la opción: ");
            opt = sc.nextInt();
        
            if (opt < 0 || opt > 3){ // rango de las opciones
                System.out.println("Error vuelve a introducir la opción: ");
                opt = sc.nextInt();
            }
            
            switch (opt) {
                case 1:
                    CampaniasOP1.consultaSinJPQLT1(entitymanager);
                    break;
                case 2:
                    CampaniasOP1.modifJPQLT1(entitymanager);
                    break;
                case 3:
                    CampaniasOP1.borradoJPTLT1(entitymanager);
                    break;                
                case 0:
                    MenuApp();
                    break;
            }
        }
        
    }
    
}
