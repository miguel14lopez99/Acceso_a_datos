package ejercicios.persistencia;

import ejercicios.persistencia.Departamentos;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-01T18:30:42", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile SingularAttribute<Empleados, String> apellido;
    public static volatile SingularAttribute<Empleados, Double> salario;
    public static volatile SingularAttribute<Empleados, Double> comision;
    public static volatile SingularAttribute<Empleados, BigDecimal> empNo;
    public static volatile SingularAttribute<Empleados, BigInteger> dir;
    public static volatile SingularAttribute<Empleados, Departamentos> deptNo;
    public static volatile SingularAttribute<Empleados, String> oficio;
    public static volatile SingularAttribute<Empleados, Date> fechaAlt;

}