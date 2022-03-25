package ejercicios.persistencia;

import ejercicios.persistencia.Empleados;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-01T18:30:42", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Departamentos.class)
public class Departamentos_ { 

    public static volatile SingularAttribute<Departamentos, String> loc;
    public static volatile CollectionAttribute<Departamentos, Empleados> empleadosCollection;
    public static volatile SingularAttribute<Departamentos, BigDecimal> deptNo;
    public static volatile SingularAttribute<Departamentos, String> dnombre;

}