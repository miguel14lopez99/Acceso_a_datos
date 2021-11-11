# Cosas para el examen

## <u>IMPORTAR LAS UTILIDADES</u>

Meter en la carpeta del proyecto >> Build with Dependencies >> Añadir como dependencia

## <u>IMPORTAR LAS OTRAS DEPENDENCIAS</u>

- ORACLE

```xml
<dependencies>
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>21.3.0.0</version>
            <type>jar</type>
        </dependency>
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>classes12</artifactId>
            <version>10.2.0.2.0</version>
            <type>pom</type>
        </dependency>
        <dependency>
            <groupId>SQL</groupId>
            <artifactId>Utilidades</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

- SQLite

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.36.0.3</version>
        </dependency>
        <dependency>
            <groupId>SQL</groupId>
            <artifactId>Utilidades</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

Pegar en Project Files / pom.xml , encima de la etiqueta \<properties>

---

## <u>MUESTRA Y COMPRBACIÓN DE ERRORES PARA CONSULTAS</u>

(SQLException)

- Para mostrar un error simple usa:
  
  ```java
  util.MostrarError(ex);
  ```

- Comprobar que la fila ya está insertada (violacion de PK)
  
  ```java
  if (ex.getMessage().contains("ORA-00001")){
      System.out.println("Esa fila ya ha sido insertada");
  }
  ```

- Comprobar que el campo de otra tabla existe (FK)
  
  ```java
  if (ex.getMessage().contains("ORA-02291")){
      System.out.println("Ese dato no existe en la base de datos");
  }
  ```

- Comprobar que has introducido mas datos de los necesarios
  
  ```java
  if (ex.getMessage().contains("ORA-00913")){
      System.out.println("Has introducido mas valores de los necesarios");
  }
  ```

---

### <u>OTRAS UTILIDADES</u>

OBTENER LA FECHA DEL SISTEMA  (sql.Date)

```java
Date fecha_actual = new Date(System.currentTimeMillis());
```

MOSTRAR FILAS AFECTADAS

```java
int filas = sentencia.executeUpdate(); 
//int filas = llamada.executeUpdate();para procedimientos y funciones
System.out.print("\n"+filas +" filas afectadas.\n");
```
