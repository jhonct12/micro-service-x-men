# Micro-service-x-men
Test MELI

# Descripcion.

* El siguiente codigo tiene como finalidad realizar el examen de MercadoLibre: https://github.com/jhonct12/micro-service-x-men/blob/main/Examen%20Mercadolibre%20-%20Mutantes%20(1).pdf

# Solucion propuesta.

 Para realiza el examen se hace uso de las siguientes herramientas, arquitectura y lenguaje.

- El examen esta realizado en lenguaje Java.
- Se hace uso de Spring boot y se combina a la arquietctura Hexagonal para microservicios.
- Para el almacenamiento de los datos se propone solucion en H2 y Mysql.
- Junit5 para realizacion de test
- Heroku para el despliegue de la aplicacion.
- PostMan para pruebas.

# Base de datos.

> Debido a las limitaciones de las cuentas gratis en Heroku, se habilita solo la de H2 en memoria, pero las conexiones a Mysql estan funcionales.

Como se menciono anteriormente para la realizacion del examen se hace uso de H2 ó de Mysql para almacenamiento de los datos.

- # Diagrama del modelo propuesto

  ![Modelo Bade de Datos](https://github.com/jhonct12/micro-service-x-men/blob/main/DB.PNG?raw=true)

-  # Consideraciones especiales.
- El examen tiene configurado dos bases de datos una H2 en memoria y otra MySql, como se muestran a continuacion.

- # Configuracion por defecto H2

- > spring.h2.console.enabled=true
- > spring.h2.console.path=/h2
- > spring.datasource.url=jdbc:h2:mem:~/xmen
- > spring.datasource.username=sa
- > spring.datasource.password=
- > spring.datasource.driver-class-name=org.h2.Driver
- > spring.sql.init.platform=h2

`-- Si se activa esta configuracion reemplazar la ip, puerto y nombre de la base de datos`
- # Configuracion MYSQL
- > spring.datasource.url=jdbc:mysql://localhost:3308/example
- > spring.datasource.username=root
- > spring.datasource.password=1234
- > spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
- > spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
- > spring.jpa.generate-ddl=true
- > logging.level.org.hibernate.SQL=debug

# Verificacion examen  desplegado en servidor
Como se menciono el examen esta desplegado en Heroku, y los enlaces expuestos son los siguientes:

`-- Tener encuenta que por limitaciones de heroku puede haber latencia y bajo rendiemiento, en ambientes que no son de prueba como aws, esto podria estar balancedo para que cresca o se reduzca la capacidad con el fin de dar abasto a el trafico que se reporte `

> Metodo Post https://melitest2022.herokuapp.com/mutant/
Este metodo recibe el siguiente objecto JSON
 {
    "dna": ["TTGTGTGTGT","CGACACACAG","TGTTGTGTGT","CACGACACAG","TGTGTGTGTT","CACACGCACG", "TGTGTGTTGT", "ACACACAGCG", "CACACACATT", "TGTGTGTGTC"]
}

> Metodo Get https://melitest2022.herokuapp.com/stats/
Este metodo permite coonsultar la cantidad de mutantes, humanos y el ratio, devuelve el siguiente objeto
{
    "count_mutant_dna": 0,
    "count_human_dna": 0,
    "ratio": 0.0
}

Adicionalmente se agregar la Url de la docuementacion, y como se menciono la base de datos esta en H2, por ende se creo otro controllador para consultar los registros y eliminar todos los registros.

> Metodo Get https://melitest2022.herokuapp.com/v2/api-docs
Este metodo devuelve la documentacion de la api.

> Metodo Get https://melitest2022.herokuapp.com/person/
Este metodo devuelve todos los registros de DNA validados

> Metodo Delete https://melitest2022.herokuapp.com/person/
Este metodo elimina toda la informacion de DNA verificados

- # Ejecucion del proyecto

1. Clonar el proyecto
2. Abrir el editor deseado, Eclipse, STS o el de preferencia.
3. Importar el proyecyo como maven.

Una vez importado el proyecto seleccionar si se desea correr la aplicacion o los test unitarios.

Adicionalmente se puede clonar el proyecto y correrlo directamente desde la consola de cmd o git.

> Consola git: ./mvnw spring-boot:run

> Consola CMD: mvnw spring-boot:run
