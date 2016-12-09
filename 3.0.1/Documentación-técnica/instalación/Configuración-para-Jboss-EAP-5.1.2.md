---
layout: default
title: Configuración para JBoss EAP 5.1.2
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para Jboss EAP 5.1.2.pdf](pdfs/SGM_2012_10_Configuración_para_Jboss_EAP_5.1.2.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para configurar el servidor de
aplicaciones Jboss 5.1.2 EAP para la correcta ejecución de las aplicaciones
proporcionadas con AL SIGM. Es necesario revisar en primer lugar el documento
general de instalación de AL SIGM y configurar las particularidades de Jboss siguiendo
éste documento.


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
el caso de Jboss 5.1.2 EAP, la librería del driver deberá ser copiada en el siguiente
directorio: `$JBOSS_HOME/server/default/lib`

### Configuración externalizada

Para que las aplicaciones de AL SIGM puedan acceder a la configuración externalizada
de AL SIGM es necesario copiar el jar `sigem_configLocation.jar` al siguiente directorio:
`$JBOSS_HOME/server/default/lib`

### Definición de datasources

En Jboss EAP 5.1.2 los datasources se definen mediante un fichero que deberá tener el
sufijo "`–ds.xml`" y deberá copiarse al siguiente directorio:
`$JBOSS_HOME/server/default/deploy`

Para Oracle 11g por ejemplo, se podría crear un fichero con nombre `oracle-ds.xml` con
el siguiente contenido:

``` xml
<?xml version="1.0" encoding="UTF-8"?>

<datasources>
 
  <local-tx-datasource>
    <jndi-name>sigemAdmin</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_SIGEMADMIN</user-name>
    <password>SIGM_3_SIGEMADMIN</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>fwktd-dir3DS</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_FWKTD_DIR3_000</user-name>
    <password>SIGM_3_FWKTD_DIR3_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>registroDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_REGISTRO_000</user-name>
    <password>SIGM_3_REGISTRO_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>tramitadorDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_TRAMITADOR_000</user-name>
    <password>SIGM_3_TRAMITADOR_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>eTramitacionDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_ETRAMITACION_000</user-name>
    <password>SIGM_3_ETRAMITACION_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>archivoDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_ARCHIVO_000</user-name>
    <password>SIGM_3_ARCHIVO_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>tercerosDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_REGISTRO_000</user-name>
    <password>SIGM_3_REGISTRO_000</password>
 
    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>fwktd-sirDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_FWKTD_SIR_000</user-name>
    <password>SIGM_3_FWKTD_SIR_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>

  <local-tx-datasource>
    <jndi-name>fwktd-auditDS_000</jndi-name>
    <connection-url>jdbc:oracle:thin:@servidor:puerto:ORCL</connection-url>

    <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
    <user-name>SIGM_3_FWKTD_AUDIT_000</user-name>
    <password>SIGM_3_FWKTD_AUDIT_000</password>

    <valid-connection-checker-classname>org.jboss.resource.adapter.jdbc.vendor.OracleValidConnectionChecker</validconnection-checker-class-name>

    <metadata>
      <type-mapping>Oracle9i</type-mapping>
    </metadata>
  </local-tx-datasource>
</datasources>
```

Para definir datasources para otras bases de datos se puede consultar la guía de
Administración y Configuración de Jboss 5:
[https://access.redhat.com/knowledge/docs/enUS/JBoss_Enterprise_Application_Platform/5/html/Administration_And_Configuration_Guide/apas03.html](https://access.redhat.com/knowledge/docs/enUS/JBoss_Enterprise_Application_Platform/5/html/Administration_And_Configuration_Guide/apas03.html)


### Activación de SSL en el servidor de aplicaciones

AL SIGM necesita que esté activo SSL en el servidor de aplicaciones. Por defecto los
puertos utilizados son los siguientes:


|Puerto|Descripción|
|:----|:----|
|8080|Puerto HTTP|
|8443|Puerto HTTP seguro con autenticación de cliente|
|4443|Puerto HTTP seguro sin autenticación de cliente|


Para configurar los puertos se debe editar el fichero 
`$JBOSS_HOME/server/default/deploy/jbossweb.sar/server.xml`
y añadir los conectores SSL: 


``` xml
<!-- A HTTP/1.1 Connector on port 8080 -->
<Connector protocol="HTTP/1.1" port="8080" address="${jboss.bind.address}"
           connectionTimeout="20000" redirectPort="8443" />

<!-- 
  Add this option to the connector to avoid problems with
  .NET clients that don't implement HTTP/1.1 correctly
  restrictedUserAgents="^.*MS Web Services Client Protocol 1.1.4322.*$"
-->

<!-- A AJP 1.3 Connector on port 8009 -->
<Connector protocol="AJP/1.3" port="8009" address="${jboss.bind.address}"
           redirectPort="8443" />

<!-- SSL/TLS Connector configuration using the admin devl guide keystore -->
<Connector protocol="HTTP/1.1" SSLEnabled="true"
           port="4443" address="${jboss.bind.address}"
           scheme="https" secure="true" clientAuth="false"
           keystoreFile="/home/sigem/SIGEM/certificados/certificadoServidor.pfx"
           keystorePass="sigem" keystoreType="PKCS12" sslProtocol = "TLS" />

<Connector protocol="HTTP/1.1" SSLEnabled="true"
           port="8443" address="${jboss.bind.address}"
           scheme="https" secure="true" clientAuth="true"
           keystoreFile="/home/sigem/SIGEM/certificados/certificadoServidor.pfx"
           keystorePass="sigem" keystoreType="PKCS12" sslProtocol = "TLS" />

```

### Deshabilitar *CachedConnectionValve*

En servidores de producción se recomienda deshabilitar el `CachedConeccionValve`. Para
ello hay que realizar los siguientes pasos:



* Encontrar el elemento `Valve` para `CachedConnectionManager` en `jbossweb-tomcat55.sar/server.xml` y **comentarlo**:

```xml
<Valve className="org.jboss.web.tomcat.tc5.jca.CachedConnectionValve"
       cachedConnectionManagerObjectName="jboss.jca:service=CachedConnectionManager"
       transactionManagerObjectName="jboss:service=TransactionManager" />
```
* Localizar la dependencia de `CachedConnectionManager` en `jbosswebtomcat55.sar/META-INF/jboss-beans.xml` y **comentarla**:

```xml
<depends>jboss.jca:service=CachedConnectionManager</depends>
```


### Solución de error `CSRF Security Error`

Este error se produce por un problema en la implementación de DWR en Jboss. Para
solucionarlo es necesario modificar la tag `Context` en el fichero `context.xml` de
configuración del servidor () y añadir `useHttpOnly="false"`. El resultado final debe
ser el siguiente:

``` xml
<Context cookies="true" crossContext="true" useHttpOnly="false">
```

### Configuración adicional de Jboss

Jboss proporciona librerías propias que prevalecen sobre las librerías de las
aplicaciones que se despliegan, para evitar conflictos se debe modificar el fichero
`$JBOSS_HOME/server/default/deployers/jbossweb.deployer/META-INF/war-deployersjboss-beans.xml`
 y asegurarse que la propiedad `filteredPackages` tiene el siguiente valor:

``` xml
<property name="filteredPackages">javax.servlet,org.slf4j,org.slf4j.impl,org.slf4j.spi</property>
```


### Despliegue de aplicaciones

Los ficheros war correspondientes a las aplicaciones de AL SIGM se deben copiar en el
directorio `$JBOSS_HOME/server/default/deploy` una vez realizados todos los pasos de
configuración anteriores. Es recomendable reiniciar Jboss antes de proceder al
despliegue.


## Anexos

### Anexo1: Modificaciones de configuración

Por defecto con AL SIGM se proporciona una configuración para ejecución en Apache
Tomcat 7.0.16. Para la correcta ejecución de las aplicaciones de AL SIGM dentro de
Jboss es necesario modificar los siguientes ficheros de la
configuración de configuración externalizada:

|Fichero | Modificación |
|:----|:----|
|`fwktd-audit/fwktd-audit-api.properties`<br>`fwktd-csv/fwktd-csv-api.properties`<br>`fwktd-dir3/fwktd-dir3-api.properties`<br>`fwktd-dm/fwktd-dm-config.xml`<br>`fwktd-sir/fwktd-sir-api.properties`<br>`SIGEM_ArchivoWeb/archivo-cfg.xml`<br>`SIGEM_Core/database.properties`<br>`SIGEM_Core/SIGEM_spring.properties`<br>`SIGEM_RegistroPresencial/hibernate.cfg.xml`<br>`SIGEM_RegistroPresencial/ISicres-Configuration.xml`<br>`SIGEM_RegistroPresencial/database.properties`<br>`SIGEM_Tramitacion/ispac.properties`<br>`SIGEM_Tramitacion/IDocStorageCfg.xml`|**Sustituir** ocurrencias de<br>`java:comp/env/jdbc/`<br>**por**<br>`java:`|

