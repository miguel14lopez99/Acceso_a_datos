package ejercicios.trabajo_r3_miguel_lopez;

import ejercicios.trabajo_r3_miguel_lopez.Agricultores;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-05T14:44:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Campanias.class)
public class Campanias_ { 

    public static volatile SingularAttribute<Campanias, String> descripcion;
    public static volatile SingularAttribute<Campanias, BigDecimal> idCamp;
    public static volatile CollectionAttribute<Campanias, Agricultores> agricultoresCollection;
    public static volatile SingularAttribute<Campanias, String> nombreCamp;

}