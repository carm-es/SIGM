---
layout: default
title: Configuración para Oracle 11g
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para Oracle 11g.pdf](pdfs/SGM_2012_10_Configuración_para_Oracle_11g.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para configurar la base de
datos Oracle 11g para la correcta ejecución de las aplicaciones proporcionadas con AL
SIGM.



### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Configuración de bases de datos

### Creación de esquemas

Los esquemas de base de datos deben estar creados con **encoding UTF16**. Se definen
en el documento: [Manual Instalación AL SIGM](Manual-de-instalación-AL-SIGM.html)


### Consideraciones adicionales

Para permitir la ejecución de los scripts de búsqueda documental de archivo es
necesario que un usuario con suficientes privilegios ejecute la siguiente sentencia:

``` sql
GRANT EXECUTE ON CTXSYS.CTX_DDL TO <USUARIO>;
```

Sustituyendo `<USUARIO>` por el usuario del esquema a utilizar para Archivo.

Por otra parte si se utiliza SQLPlus para ejecutar los scripts es necesario ejecutar una
sentencia para evitar errores en los scripts de archivo:

``` sql
SET DEFINE OFF;
```


### Ejecución manual de scripts para cada esquema

En la siguiente tabla se indica por cada esquema los scripts a ejecutar indicando la ruta
donde se encuentra cada script en la distribución de AL SIGM (se toma como directorio
base el directorio `Aplicaciones\Plantillas BBDD Multientidad\Oracle`):


|Esquema|Scripts|
|:----|:----|
|sigemAdmin|`sigemAdmin/sigemAdmin.sql`|
|archivoDS_000|`archivo/01.archivo-create-tables-oracle.sql`<br>`archivo/02.archivo-create-indexes-oracle.sql` (necesita un parámetro con el nombre del tablespace utilizado para crear índices)<br>`archivo/03.archivo-insert-data-oracle.sql`<br>`archivo/04.archivo-insert-clob-oracle.sql`<br>`archivo/05.archivo-create-functions-oracle.sql`<br>`archivo/06.archivo-create-procedures-oracle.sql`<br>`archivo/07.archivo-personalization-oracle.sql`<br>`archivo/complementario/archivo-organizacion-bd/01.archivo-organizacion-bd-create-tablesoracle.sql`<br>`archivo/complementario/archivo-organizacion-bd/02.archivo-organizacion-bd-create-indexesoracle.sql` (necesita un parámetro con el nombre del tablespace utilizado para crear índices)<br>`archivo/complementario/archivo-busqueda-documental/01.ARCHIVOFTSTH.sql`<br>`archivo/complementario/archivo-busqueda-documental/02.ARCHIVOFTSTB.sql`<br>`archivo/complementario/archivo-busqueda-documental/03.ARCHIVOINTERMEDIA.sql`<br>`archivo/complementario/archivo-busqueda-documental/04.ARCHIVOJOBINTERMEDIA.sql`<br>`archivo/complementario/archivo-busqueda-documental/05.ARCHIVOOPT.sql`|
|eTramitacionDS_000|`eTramitacion/01_create_tables.sql`<br>`eTramitacion/02_create_indexes_constraints.sql`<br>`eTramitacion/03_insert_data.sql`<br>`eTramitacion/04_insert_data_tasks.sql`<br>`csv/fwktd-csv-create.sql`<br>`eTramitacion/05_insert_data_csv_fwktd_module.sql`|
|tramitadorDS_000|`tramitador/01-create_sequences.sql`<br>`tramitador/02-create_tables.sql`<br>`tramitador/02b-create_indexes_constraints.sql`<br>`tramitador/03-create_views.sql`<br>`tramitador/03b-create_procedures.sql`<br>`tramitador/04-datos_iniciales.sql`<br>`tramitador/06.1-datos_prototipos-create_sequences.sql`<br>`tramitador/06.1-datos_prototipos-create_tables.sql`<br>`tramitador/06.1-datos_prototipos-create_constraints.sql`<br>`tramitador/06.1-datos_prototipos.sql`<br>`tramitador/06.2-datos_prototipos_v1.9-create_sequences.sql`<br>`tramitador/06.2-datos_prototipos_v1.9-create_tables.sql`<br>`tramitador/06.2-datos_prototipos_v1.9-create_indexes_constraints.sql`<br>`tramitador/06.2-datos_prototipos_v1.9.sql`<br>`tramitador/06.3-informes_estadisticos.sql`<br>`tramitador/07-actualizacion_permisos.sql`<br>`tramitador/08-configuracion_publicador.sql`<br>`tramitador/04b-datos_iniciales_clob.sql`<br>`tramitador/04b-datos_iniciales_plantillas_clob.sql`<br>`tramitador/06.1b-datos_prototipos_clob.sql`<br>`tramitador/06.1b-datos_prototipos_plantillas_clob.sql`<br>`tramitador/06.2b-datos_prototipos_v1.9_clob.sql`<br>`tramitador/06.2b-datos_prototipos_v1.9_plantillas_clob.sql`<br>`tramitador/06.3-informes_estadisticos_clob.sql`|
|registroDS_000|`registro/01.1_create_tables_registro_sigem_oracle.sql`<br>`registro/01.2_create_tables_invesdoc_registro_sigem_oracle.sql`<br>`registro/01.3_create_views_invesdoc_registro_sigem_oracle.sql`<br>`registro/02.1_create_indexes_constraints_registro_sigem_oracle.sql`<br>`registro/02.2_create_indexes_constraints_invesdoc_registro_sigem_oracle.sql`<br>`registro/03.1_insert_data_registro_sigem_oracle.sql`<br>`registro/03.2_insert_data_invesdoc_registro_sigem_oracle.sql`<br>`registro/04.1_insert_clob_invesdoc_registro_sigem_oracle.sql`<br>`registro/05-sicres3.sql`<br>`sigemEstructuraOrganizativa/01.1_create_tables_sigem_estructura_organizativa.sql`<br>`sigemEstructuraOrganizativa/02.1_create_indexes_constraints_estructura_organizativa.sql`<br>`create_user_consolidacion.sql`<br>`repositorios_registro_sigem_oracle.sql`|
|fwktd-dir3DS|`dir3/fwktd-dir3-create.sql`<br>`dir3/fwktd-dir3-insert.sql`|
|fwktd-sirDS_000|`sir/fwktd-sir-create.sql`<br>`sir/fwktd-sir-insert.sql`<br>`sir/fwktd-dm-bd-create.sql`<br>`sir/fwktd-dm-bd-insert.sql`|
|fwktd-auditDS_000|`audit/fwktd-audit-create.sql`<br>`tramitador/50-tramitador_auditoria_datos.sql`<br>`registro/06-insert_data_registro_auditoria_datos_oracle.sql`|


## Configuración externalizada

La configuración externalizada de AL SIGM se proporciona por defecto para PostgreSQL
9.0.3. Para que las aplicaciones funcionen correctamente es necesario cambiar de
forma manual algunos ficheros de configuración, a no ser que se genere un zip de
configuración con la herramienta de generación de configuración básica como se indica
en el documento:
[Manual de Uso Herramienta Configuraciones Básicas](../desarrollo/Manual-de-Uso-Herramienta-Configuraciones-Básicas.html)

Si no se utiliza dicha herramienta los cambios se deberán realizar manualmente en el
directorio donde se encuentre la configuración externalizada. Los ficheros a cambiar
son los siguientes:


|Fichero|Cambio|
|:----|:----|
|`fwktd-audit\fwktd-audit-api.properties`|**Sustituir:**<br>`fwktd-audit.database=postgresql`<br>**Por:**<br>`fwktd-audit.database=oracle`|
|`fwktd-csv\fwktd-csv-api.properties`|**Sustituir:**<br>`fwktd-csv.database=postgresql`<br>**Por:**<br>`fwktd-csv.database=oracle`|
|`fwktd-sir\fwktd-sir-api.properties`|**Sustituir:**<br>`fwktd-sir.database=postgresql`<br>**Por:**<br>`fwktd-sir.database=oracle`|
|`SIGEM_ArchivoWeb\archivo-cfg.xml`|**Sustituir:**<br>`<DB_Factory_Class>common.db.DBEntityFactoryPostgreSQL</DB_Factory_Class>`<br>**Por:**<br>`<DB_Factory_Class>common.db.DBEntityFactoryOracle9i</DB_Factory_Class>`|
|`SIGEM_Core\database.properties`|**Sustituir:**<br>`sigem.springDatasource.database=jdbc:postgresql://localhost/sigemAdmin`<br>`sigem.springDatasource.user=postgres`<br>`sigem.springDatasource.password=postgres`<br>`sigem.springDatasource.driver=org.postgresql.Driver`<br>**Por:** (con la url y usuario y contraseña correctos):<br>`sigem.springDatasource.database=jdbc:oracle:thin:@localhost:1521:GDOC`<br>`sigem.springDatasource.user=SIGEMADMIN`<br>`sigem.springDatasource.password=SIGEMADMIN`<br>`sigem.springDatasource.driver=oracle.jdbc.OracleDriver`|
|`SIGEM_RegistroPresencial\database.properties`|**Sustituir:**<br>`isicres.database=postgres`<br>**Por:**<br>`isicres.database=oracle`|
|`SIGEM_RegistroPresencial\hibernate.cfg.xml`|**Sustituir:**<br>`<property name="dialect">net.sf.hibernate.dialect.PostgreSQLDialect</property>`<br>**Por:**<br>`<property name="dialect">net.sf.hibernate.dialect.OracleDialect</property>`|
|`SIGEM_RegistroPresencial\ISicres-Configuration.xml`|**Sustituir:**<br>`<DAOImplementation>com.ieci.tecdoc.common.entity.dao.PostgreSQLDBEntityDAO</DAOImplementation>`<br>**Por:**<br>`<DAOImplementation>com.ieci.tecdoc.common.entity.dao.OracleDBEntityDAO</DAOImplementation>`|


