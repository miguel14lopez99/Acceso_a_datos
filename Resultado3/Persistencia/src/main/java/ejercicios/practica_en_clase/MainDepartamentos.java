/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios.practica_en_clase;

import ejercicios.persistencia.Departamentos;
import ejercicios.persistencia.Empleados;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author b15-04m
 */
public class MainDepartamentos {
    
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static ejercicios.persistencia.Departamentos departamento;
    static ejercicios.persistencia.Empleados empleado;
    
    public static void main(String[] args) {

        inicializarFactory();
        
        consultaConCriteriaQueryVariosCampos();

    }
    
    public static void inicializarFactory(){
        
        emfactory = Persistence.createEntityManagerFactory("ejercicios_Persistencia_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
        
    }
    
    public static void mostrarDatosDepartamento(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce un departamento: ");
        int dept = sc.nextInt();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept));
        if (departamento != null){
            System.out.println("Dept EMP_NO: "+departamento.getDeptNo());
            System.out.println("Dept NAME: "+departamento.getDnombre());
            System.out.println("Dept LOC: "+departamento.getLoc());
            
            Collection<Empleados> list = departamento.getEmpleadosCollection();
            
            Iterator<Empleados> it = list.iterator();
            
            while(it.hasNext()){
                
                empleado = it.next();
                System.out.println("\nEmple NAME: "+ empleado.getApellido());
            }
        } else {
            System.out.println("No existe");
        }
        
    }
    
    public static void eliminarDepartamento(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce un departamento que desea eliminar: ");
        int dept = sc.nextInt();
        
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept), LockModeType.PESSIMISTIC_READ);
                
        entitymanager.remove(departamento);
        entitymanager.getTransaction().commit();
        
    }
    
    public static void modificarEmpleado(){
        
        Scanner sc = new Scanner(System.in);
        
        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un numero de empleado: ");
        int emp_no = sc.nextInt();
        
        empleado = entitymanager.find(Empleados.class, BigDecimal.valueOf(emp_no), LockModeType.PESSIMISTIC_READ);
        
        System.out.println("Introduce el nuevo departamento: ");
        int dept = sc.nextInt();       
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept));
        
        System.out.println("Introduce el nuevo salario: ");
        double salario = sc.nextDouble();

        if (empleado != null && departamento != null){

            empleado.setDeptNo(departamento);
            empleado.setSalario(salario);

            entitymanager.getTransaction().commit();
        } else {
            
            System.out.println("No existe");
            entitymanager.getTransaction().commit();
        }
         
    }
    
    public static void eliminarEmpleado(){
        
        Scanner sc = new Scanner(System.in);
        
        entitymanager.getTransaction().begin();
        
        System.out.println("Introduce un numero de empleado a eliminar: ");
        int emp_no = sc.nextInt();
        
        empleado = entitymanager.find(Empleados.class, BigDecimal.valueOf(emp_no), LockModeType.PESSIMISTIC_READ);
        
        if(empleado != null){
            
            entitymanager.remove(empleado);
            entitymanager.getTransaction().commit();
        } else {
            
            System.out.println("No existe");
            entitymanager.getTransaction().commit();
        }
        
    }
    
    public static void insertarEmpleado(){
        
        try {
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Introduce el número de empleado: ");
            int emp_no = sc.nextInt();
            
            System.out.println("Introduce el apellido: ");
            String apellido = sc.next();
            
            System.out.println("Introduce el oficio: ");
            String oficio = sc.next();
            
            System.out.println("Introduce el director: ");
            int dir = sc.nextInt();
            
            System.out.println("Introduce la fecha(dd/MM/yyyy): ");
            String date = sc.next();
            Date fechaAlta = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            
            System.out.println("Introduce el salario: ");
            double salario = sc.nextDouble();
            
            System.out.println("Introduce la comisión: ");
            double comision = sc.nextDouble();
            
            System.out.println("Introduce el departamento: ");
            int dept = sc.nextInt();
            
            departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept));
            
            if( departamento != null ){
                empleado = new Empleados();
                empleado.setEmpNo(BigDecimal.valueOf(emp_no));
                empleado.setApellido(apellido);
                empleado.setOficio(oficio);
                empleado.setDir(BigInteger.valueOf(dir));
                empleado.setFechaAlt(fechaAlta);
                empleado.setSalario(salario);
                empleado.setComision(comision);
                empleado.setDeptNo(departamento);
                
                entitymanager.getTransaction().begin();
                entitymanager.persist(empleado);
                entitymanager.getTransaction().commit();
                
            } else {
                System.out.println("El departamento no existe");
            }

        } catch (ParseException ex) {
            Logger.getLogger(MainDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void subirSalario(){
        
        entitymanager.getTransaction().begin();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce un departamento: ");
        int dept = sc.nextInt();
        
        System.out.println("Introduce un aumento: ");
        double aumento = sc.nextDouble();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept), LockModeType.PESSIMISTIC_READ);
        if (departamento != null){
            System.out.println("Dept EMP_NO: "+departamento.getDeptNo());
            System.out.println("Dept NAME: "+departamento.getDnombre());
            System.out.println("Dept LOC: "+departamento.getLoc());
            
            Collection<Empleados> list = departamento.getEmpleadosCollection();
            
            Iterator<Empleados> it = list.iterator();
            
            while(it.hasNext()){
                
                empleado = it.next();
                System.out.println("Emple NAME: "+empleado.getApellido());
                System.out.println("salario antes: "+empleado.getSalario());
                empleado.setSalario(empleado.getSalario()+aumento);
                System.out.println("salario después: "+empleado.getSalario());
 
            }
            
            entitymanager.getTransaction().commit();
            
        } else {
            System.out.println("No existe");
            entitymanager.getTransaction().commit();

        }
        
    }
    
    public static void consultaAlmacenada(){
        
        Query query = entitymanager.createNamedQuery("Departamentos.findAll");

        List<Departamentos> list = query.getResultList();
        
        for (Departamentos departamento : list) {
            System.out.println("Dept NAME: "+departamento.getDnombre());
        }
        
    }
    
    public static void consultaConParametro(){
        
        Query query = entitymanager.createNamedQuery("Departamentos.findByDeptNo");
        
        query.setParameter("dept_no", 1);
        List<Departamentos> list = query.getResultList();
        
        for (Departamentos departamento : list) {
            System.out.println("Dept NAME: "+departamento.getDnombre());
        }
        
    }
    
    public static void consultaSimple(){
        
        Query query = entitymanager.createQuery("Select UPPER(d.dnombre) from Departamentos d");
        
        List<String> list = query.getResultList();
        
        for(String e : list){
            System.out.println("Dept NAME: "+e);
        }
        
        
    }
    
    public static void consultaVariosCampos(){
        TypedQuery<Object[]> query = entitymanager.createQuery("Select d.dnombre,d.loc from Departamentos d", Object[].class);

        List<Object[]> list = query.getResultList();

        for(Object[] e:list) {
           System.out.println("Dept NAME :"+e[0]);
           System.out.println("Dept LOC :"+e[1]);
        }
    }

    public static void subirSalarioJPQL(){
        
        entitymanager.getTransaction().begin();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nuevo salario: ");
        Double aumento = sc.nextDouble();
        
        System.out.println("Introduce el departamento: ");
        int dept = sc.nextInt();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept), LockModeType.PESSIMISTIC_READ);
        
        //Mostrar antes del update
        
        TypedQuery<Object[]> selectquery = entitymanager.createQuery("Select e.apellido,e.salario from Empleados e WHERE e.deptNo = :deptNoV ", Object[].class);

        selectquery.setParameter("deptNoV", departamento);
        
        List<Object[]> list = selectquery.getResultList();

        for(Object[] e:list) {
           System.out.println("Emple APELLIDO :"+e[0]);
           System.out.println("Emple SALARIO :"+e[1]);
        }
        
        //realizar el update
        
        Query query = entitymanager.createQuery("UPDATE Empleados e SET e.salario = (e.salario + :aumento) WHERE e.deptNo = :deptNoV");
        query.setParameter("aumento", aumento);
        query.setParameter("deptNoV", departamento);
        
        int updateCount = query.executeUpdate();
        
        entitymanager.getTransaction().commit();
        
        System.out.println(updateCount+" filas modificadas.");
        
        //Mostrar apellido y salario
        
        selectquery = entitymanager.createQuery("Select e.apellido,e.salario from Empleados e WHERE e.deptNo = :deptNoV", Object[].class);

        selectquery.setParameter("deptNoV", departamento);
        
        list = selectquery.getResultList();

        for(Object[] e:list) {
           System.out.println("Emple APELLIDO :"+e[0]);
           System.out.println("Emple SALARIO :"+e[1]);
        }
        
    }
    
    public static void eliminarDepartamentoJPQL(){
        
        entitymanager.getTransaction().begin();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el departamento: ");
        int dept = sc.nextInt();
        
        
        Query query = entitymanager.createQuery("DELETE FROM Departamentos d WHERE d.deptNo = :deptNoV");
        query.setParameter("deptNoV", BigDecimal.valueOf(dept));
        
        int updateCount = query.executeUpdate();
        
        entitymanager.getTransaction().commit();
        
        System.out.println(updateCount+" filas modificadas.");
        
    }
    
    public static void modificarEmpleadoJPQL(){
        
        entitymanager.getTransaction().begin();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el empleado: ");
        int emple = sc.nextInt();
        
        System.out.println("Introduce el salario: ");
        double salario = sc.nextDouble();
        
        System.out.println("Introduce el departamento: ");
        int dept = sc.nextInt();
        
        departamento = entitymanager.find(Departamentos.class, BigDecimal.valueOf(dept));
        
        Query query = entitymanager.createQuery("UPDATE Empleados e SET e.salario = :salario , e.deptNo = :deptNoV WHERE e.empNo = :empNoV");
         
        query.setParameter("salario", salario);
        query.setParameter("deptNoV", departamento);
        
        query.setParameter("empNoV", BigDecimal.valueOf(emple));
        
        int updateCount = query.executeUpdate();
        
        entitymanager.getTransaction().commit();
        
        System.out.println(updateCount+" filas modificadas.");
        
    }
    
    public static void eliminarEmpleadoJPQL(){
        
        entitymanager.getTransaction().begin();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Introduce el empleado: ");
        int emple = sc.nextInt();
        
        Query query = entitymanager.createQuery("DELETE FROM Empleados e WHERE e.empNo = :empNoV");
        query.setParameter("empNoV", BigDecimal.valueOf(emple));
        
        int updateCount = query.executeUpdate();
        
        entitymanager.getTransaction().commit();
        
        System.out.println(updateCount+" filas modificadas.");
        
    }
    
    
    public static void consultaConCriteriaQueryVariosCampos(){
        
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
        
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        
        Root<Departamentos> d = query.from(Departamentos.class);
        query.select(cb.array(d.get("dnombre"), d.get("loc")));
        
        List<Object[]> list = entitymanager.createQuery(query).getResultList();
        
        for (Object[] e : list){
            System.out.println("Dept NAME: "+e[0]);
            System.out.println("Dept NAME: "+e[1]);
        }
        
    }
    
}

