---
layout: default
title: Configuración para Tomcat 7.0.16
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para Tomcat 7.0.16.pdf](pdfs/SGM_2012_10_Configuración_para_Tomcat_7.0.16.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para configurar el servidor de
aplicaciones Tomcat 7.0.16 para la correcta ejecución de las aplicaciones
proporcionadas con AL SIGM. Es necesario revisar en primer lugar el documento
general de instalación de AL SIGM y configurar las particularidades de Tomcat
siguiendo éste documento.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|


## Configuración del servidor de aplicaciones

### Driver JDBC

En el servidor de aplicaciones habrá que instalar el driver JDBC de la base de datos. En
el caso de Tomcat 7.0.16, la librería del driver deberá ser copiada en el directorio lib
del Tomcat

### Configuración externalizada

Para que las aplicaciones de AL SIGM puedan acceder a la configuración externalizada
de AL SIGM es necesario copiar el jar `sigem_configLocation.jar` al directorio `lib` de
Tomcat.

### Definición de datasources

En Tomcat 7.0.16 los datasources se definen en el fichero `conf/server.xml` en la
sección `GlobalNamingResources`.
Para Postgres por ejemplo, deberíamos meter en esa sección las siguientes líneas:


``` xml
<!-- Comunes -->
<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          name="jdbc/sigemAdmin"
          driverClassName="org.postgresql.Driver"
          type="javax.sql.DataSource"
          url="jdbc:postgresql://localhost/sigemAdmin" 
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          name="jdbc/fwktd-dir3DS"
          driverClassName="org.postgresql.Driver"
          type="javax.sql.DataSource"
          url="jdbc:postgresql://localhost/fwktd-dir3"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          name="jdbc/sisnot"
          driverClassName="org.postgresql.Driver"
          type="javax.sql.DataSource"
          url="jdbc:postgresql://localhost/sisnot"
          username="postgres" password="postgres"/>

<!-- Entidad 000 -->
<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/registroDS_000"
          url="jdbc:postgresql://localhost/registro_000" 
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/tramitadorDS_000"
          url="jdbc:postgresql://localhost/tramitador_000"
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/eTramitacionDS_000"
          url="jdbc:postgresql://localhost/eTramitacion_000" 
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/archivoDS_000"
          url="jdbc:postgresql://localhost/archivo_000"
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/tercerosDS_000"
          url="jdbc:postgresql://localhost/registro_000"
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/fwktd-sirDS_000" 
          url="jdbc:postgresql://localhost:5432/fwktd-sir_000"
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

<Resource auth="Container" logAbandoned="true" 
          maxIdle="10" maxActive="20" maxWait="-1" 
          removeAbandoned="true" removeAbandonedTimeout="5"
          driverClassName="org.postgresql.Driver"
          name="jdbc/fwktd-auditDS_000"
          url="jdbc:postgresql://localhost:5432/fwktd-audit_000"
          type="javax.sql.DataSource"
          username="postgres" password="postgres"/>

```

Para definir datasources para otras bases de datos se puede consultar documentación de Tomcat:
[http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html](http://tomcat.apache.org/tomcat-7.0-doc/jndi-datasource-examples-howto.html)

Una vez creados los datasources habría que asignarlos a los contextos de las aplicaciones. Para ello en la sección `Valve` deberemos asociar cada contexto de  aplicación con los *datasources* globales previamente definidos y siguiendo la correspondencia *contexto-datasources* indicada en el documento 
[Manual de Instalación de AL SIGM](Manual-de-instalación-AL-SIGM.html).
A continuación se puede ver un ejemplo de ésta correspondencia:

``` xml
<Context docBase="SIGEM_AdministracionUsuariosWeb" path="/SIGEM_AdministracionUsuariosWeb"
         reloadable="true">
   <ResourceLink global="jdbc/eTramitacionDS_000" name="jdbc/eTramitacionDS_000" type="javax.sql.DataSource"/>
   <ResourceLink global="jdbc/fwktd-auditDS_000"  name="jdbc/fwktd-auditDS_000"  type="javax.sql.DataSource"/>
</Context>
```


### Activación de SSL en el servidor de aplicaciones

AL SIGM necesita que esté activo SSL en el servidor de aplicaciones. Por defecto los
puertos utilizados son los siguientes:


|Puerto|Descripción|
|:----|:----|
|8080|Puerto HTTP|
|8443|Puerto HTTP seguro con autenticación de cliente|
|4443|Puerto HTTP seguro sin autenticación de cliente|

Para configurar los puertos se debe editar el fichero `conf/server.xml` y añadir los dos
conectores SSL:


``` xml
<!-- PUERTO SEGURO CON AUTENTICACION DE CLIENTE-->
<Connector SSLEnabled="true" clientAuth="true"
           keystoreFile="/home/sigem/SIGEM/certificados/certificadoServidor.pfx"
           keystorePass="sigem" keystoreType="PKCS12" maxThreads="150" port="8443"
           protocol="HTTP/1.1" scheme="https" secure="true" sslProtocol="TLS"/>

<!-- PUERTO SEGURO SIN AUTENTICACION DE CLIENTE-->
<Connector SSLEnabled="true" clientAuth="false"
           keystoreFile="/home/sigem/SIGEM/certificados/certificadoServidor.pfx"
           keystorePass="sigem" keystoreType="PKCS12" maxThreads="150" port="4443"
           protocol="HTTP/1.1" scheme="https" secure="true" sslProtocol="TLS"/>
```


### Solución de error `CSRF Security Error`

Este error se produce por un problema en la implementación de DWR en Tomcat 7. Para
solucionarlo es necesario modificar la tag `Context` en el fichero `context.xml` de
configuración de Tomcat y añadir `useHttpOnly="false"`. El resultado final debe
ser el siguiente:

``` xml
<Context useHttpOnly="false">
```


### Despliegue de aplicaciones

Los ficheros war correspondientes a las aplicaciones de AL SIGM se deben copiar en el
directorio `webapps` una vez realizados todos los pasos de configuración anteriores. Una
vez copiados se puede arrancar el Tomcat.

