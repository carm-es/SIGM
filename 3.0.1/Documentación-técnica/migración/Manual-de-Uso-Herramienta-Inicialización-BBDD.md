---
layout: default
title: Manual de usuario de la herramienta de inicialización de base de datos
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Manual de Uso Herramienta Inicialización BBDD.pdf](pdfs/SGM_2012_10_Manual_de_Uso_Herramienta_Inicialización_BBDD.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para ejecutar la herramienta
de inicialización de base de datos que se proporciona con AL SIGM.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|




## Ejecución de la Herramienta
La ejecución de la herramienta de inicialización de base de datos requiere que se tenga
instalado correctamente Apache Maven en su versión 2.2.1 junto a una JDK 1.5, así
como acceso al código fuente de AL SIGM. Para la instalación y configuración de la jdk
y maven se puede consultar el siguiente documento:
[Configuración del entorno de desarrollo](../desarrollo/Configuración-del-entorno-de-desarrollo.html)

Desde el directorio raíz del código fuente se podrá lanzar la ejecución de Maven con
los perfiles necesarios para la inicialización de la base de datos.

Es necesario que los esquemas de base de datos estén creados con los permisos
necesarios y sin ninguna tabla/vista/función/procedimiento que entre en conflicto con
los que se creen mediante los scripts de AL SIGM.


### Esquemas necesarios

Para poder utilizar la herramienta de inicialización de base de datos es necesario que
estén creados previamente los esquemas de base de datos que utilizan las
aplicaciones. Estos esquemas son los siguientes:



|Esquema|Descripción|
|:----|:----|
|Registro Presencial<br>(registro)|Esquema para la aplicación de Registro Presencial.|
|Gestión de Archivo<br>(archivo)|Esquema para la aplicación de Gestión de Archivo.|
|Tramitación Electrónica<br>(etramitacion)|Esquema para la aplicación de Tramitación Electrónica.|
|Administración<br>(sigemadmin)|Esquema para la base de datos común de administración.|
|Gestión de expedientes<br>(tramitacion)|Esquema para la aplicación de Gestión de Expedientes.|
|Componente de Consulta del Directorio Común<br>(dir3)|Esquema para el componente de Consulta del Directorio Común|
|Auditoría<br>(audit)|Esquema para auditoría de las aplicaciones.|
|Sistema de Interconexión de Registros<br>(sir)|Esquema para el componente de Interconexión de Registros.|


### Parámetros de ejecución

Para la correcta ejecución de la aplicación de inicialización de base de datos es
necesario proporcionar varios parámetros en la construcción de Maven. La ejecución se
realizará desde el directorio base de SIGEM3.

#### Parámetros necesarios (Generales)

* `databaseHost`: Host de la base de datos
* `databaseName`: Nombre de la base de datos
* `usernameJdbc`: Usuario de la base de datos
* `passwordJdbc`: Password de la base de datos

#### Parámetros necesarios (Sql Server)

* `fullTextCatalogName`: Nombre del catálogo de búsqueda documental (puede
ser el que se quiera) que se utilizará para la base de datos de la aplicación
de archivo.
* `grantProceduresUserName`: Usuario al que se le dan permisos para ejecutar
los procedimientos y funciones en la base de datos de la aplicación de
archivo.

#### Parámetros necesarios (Oracle)


* `indexTablespace`: Nombre del tablespace donde se van a crear los índices de
la base de datos de la aplicación de archivo.

#### Parámetros opcionales

* `databasePort`: Puerto de la base de datos

#### Perfiles necesarios

* `generate-bd`: Perfil para indicar que se va a realizar una ejecución de la
aplicación de inicialización de base de datos


### Consideraciones adicionales

En Oracle 11g es necesario que un usuario con privilegios suficientes ejecute la
siguiente sentencia:

``` sql
GRANT EXECUTE ON CTXSYS.CTX_DDL TO <USUARIO>;
```

Sustituyendo `<USUARIO>` por el usuario del esquema de Oracle de Archivo.


## Ejemplos de ejecución

A continuación se muestran los comandos de ejecución por base de datos y aplicación.
Deberían adaptarse teniendo en cuenta el host, nombre de base de datos, usuario y
contraseña en cada caso.

|Base de datos y aplicación|Comando de ejemplo|
|:----|:----|
|Postgresql<br>(registro)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=registro_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-registro`|
|Postgresql<br>(archivo)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=archivo_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-archivo`|
|Postgresql<br>(etramitacion)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=eTramitacion_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-etramitacion`|
|Postgresql<br>(sigemadmin)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=sigemAdmin -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-sigemadmin`|
|Postgresql<br>(tramitacion)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=tramitador_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-tramitacion`|
|Postgresql<br>(dir3)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=fwktd-dir3 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-dir3`|
|Postgresql<br>(audit)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=fwktd-audit_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-audit`|
|Postgresql<br>(sir)|`mvn clean initialize -Ddb=postgresql -DdatabaseHost=localhost DdatabaseName=fwktd-sir_000 -DusernameJdbc=postgres DpasswordJdbc=postgres -Pgenerate-bd,init-postgresql-sir`|
|Sql Server<br>(registro)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_REGISTRO_000 DusernameJdbc=ALSIGM_REGISTRO_000 -DpasswordJdbc=ALSIGM_REGISTRO_000 -Pgenerate-bd,init-sqlserver-registro`|
|Sql Server<br>(archivo)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_ARCHIVO_000 DusernameJdbc=ALSIGM_ARCHIVO_000 DpasswordJdbc=ALSIGM_ARCHIVO_000 -DfullTextCatalogName=ARCHIVO DgrantProceduresUserName=ALSIGM_ARCHIVO_000 -Pgenerate-bd,initsqlserver-archivo`|
|Sql Server<br>(etramitacion)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_ETRAMITACION_000 DusernameJdbc=ALSIGM_ETRAMITACION_000 DpasswordJdbc=ALSIGM_ETRAMITACION_000 -Pgenerate-bd,init-sqlserveretramitacion`|
|Sql Server<br>(sigemadmin)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_SIGEMADMIN DusernameJdbc=ALSIGM_SIGEMADMIN DpasswordJdbc=ALSIGM_SIGEMADMIN -Pgenerate-bd,init-sqlserversigemadmin`|
|Sql Server<br>(tramitacion)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_TRAMITADOR_000 DusernameJdbc=ALSIGM_TRAMITADOR_000 DpasswordJdbc=ALSIGM_TRAMITADOR_000 -Pgenerate-bd,init-sqlservertramitacion`|
|Sql Server<br>(dir3)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_FWKTD_DIR3_000 DusernameJdbc=ALSIGM_FWKTD_DIR3_000 DpasswordJdbc=ALSIGM_FWKTD_DIR3_000 -Pgenerate-bd,init-sqlserver-dir3`|
|Sql Server<br>(audit)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_FWKTD_AUDIT_000 DusernameJdbc=ALSIGM_FWKTD_AUDIT_000 DpasswordJdbc=ALSIGM_FWKTD_AUDIT_000 -Pgenerate-bd,init-sqlserveraudit`|
|Sql Server<br>(sir)|`mvn clean initialize -Ddb=sqlserver -DdatabaseHost=localhost DdatabaseName=ALSIGM_FWKTD_SIR_000 DusernameJdbc=ALSIGM_FWKTD_SIR_000 DpasswordJdbc=ALSIGM_FWKTD_SIR_000 -Pgenerate-bd,init-sqlserver-sir`|
|Oracle<br>(registro)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_REGISTRO_000 DusernameJdbc=ALSIGM_TEST_REGISTRO_000 DpasswordJdbc=ALSIGM_TEST_REGISTRO_000 -Pgenerate-bd,init-oracleregistro`|
|Oracle<br>(archivo)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_ARCHIVO_000 DusernameJdbc=ALSIGM_TEST_ARCHIVO_000 DpasswordJdbc=ALSIGM_TEST_ARCHIVO_000 -DindexTablespace=USERS Pgenerate-bd,init-oracle-archivo`|
|Oracle<br>(etramitacion)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_ETRAMITACION_000 DusernameJdbc=ALSIGM_TEST_ETRAMITACION_000 DpasswordJdbc=ALSIGM_TEST_ETRAMITACION_000 -Pgenerate-bd,initoracle-etramitacion`|
|Oracle<br>(sigemadmin)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_SIGEMADMIN -DusernameJdbc=ALSIGM_TEST_SIGEMADMIN DpasswordJdbc=ALSIGM_TEST_SIGEMADMIN -Pgenerate-bd,init-oraclesigemadmin`|
|Oracle<br>(tramitacion)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_TRAMITADOR_000 DusernameJdbc=ALSIGM_TEST_TRAMITADOR_000 DpasswordJdbc=ALSIGM_TEST_TRAMITADOR_000 -Pgenerate-bd,init-oracletramitacion`|
|Oracle<br>(dir3)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_FWKTD_DIR3_000 DusernameJdbc=ALSIGM_TEST_FWKTD_DIR3_000 DpasswordJdbc=ALSIGM_TEST_FWKTD_DIR3_000 -Pgenerate-bd,init-oracledir3`|
|Oracle<br>(audit)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_FWKTD_AUDIT_000 DusernameJdbc=ALSIGM_TEST_FWKTD_AUDIT_000 DpasswordJdbc=ALSIGM_TEST_FWKTD_AUDIT_000 -Pgenerate-bd,init-oracleaudit`|
|Oracle<br>(sir)|`mvn clean initialize -Ddb=oracle -DdatabaseHost=localhost DdatabaseName=ALSIGM_TEST_FWKTD_SIR_000 DusernameJdbc=ALSIGM_TEST_FWKTD_SIR_000 DpasswordJdbc=ALSIGM_TEST_FWKTD_SIR_000 -Pgenerate-bd,init-oracle-sir`|
|DB2<br>(registro)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALRP000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-registro`|
|DB2<br>(archivo)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALAR000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-archivo`|
|DB2<br>(etramitacion)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALET000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-etramitacion`|
|DB2<br>(sigemadmin)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALADM -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-sigemadmin`|
|DB2<br>(tramitacion)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALGE000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-tramitacion`|
|DB2<br>(dir3)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALD3000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-dir3`|
|DB2<br>(audit)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALAU000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-audit`|
|DB2<br>(sir)|`mvn clean initialize -Ddb=db2 -DdatabaseHost=localhost DdatabaseName=ALSR000 -DusernameJdbc=sigm3 -DpasswordJdbc=sigm3 Pgenerate-bd,init-db2-sir`|

