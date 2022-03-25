package ejercicios.trabajo_r3_miguel_lopez;

import ejercicios.trabajo_r3_miguel_lopez.Agricultores;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-05T14:44:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Maquinas.class)
public class Maquinas_ { 

    public static volatile SingularAttribute<Maquinas, BigDecimal> idMaquina;
    public static volatile SingularAttribute<Maquinas, Agricultores> idAgri;
    public static volatile SingularAttribute<Maquinas, String> nroBastidor;
    public static volatile SingularAttribute<Maquinas, Date> ultRevision;

}