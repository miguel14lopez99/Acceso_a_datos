package ejercicios.trabajo_r3_miguel_lopez;

import ejercicios.trabajo_r3_miguel_lopez.Campanias;
import ejercicios.trabajo_r3_miguel_lopez.Maquinas;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-05T14:44:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Agricultores.class)
public class Agricultores_ { 

    public static volatile SingularAttribute<Agricultores, Campanias> idCamp;
    public static volatile CollectionAttribute<Agricultores, Maquinas> maquinasCollection;
    public static volatile SingularAttribute<Agricultores, String> nombreAgri;
    public static volatile SingularAttribute<Agricultores, BigDecimal> idAgri;
    public static volatile SingularAttribute<Agricultores, Integer> telefono;

}