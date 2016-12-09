---
layout: default
title: Configuración para SQL Server 2008
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para SQL Server 2008.pdf](pdfs/SGM_2012_10_Configuración_para_SQL_Server_2008.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para configurar la base de
datos SQL Server 2008 para la correcta ejecución de las aplicaciones proporcionadas
con AL SIGM.


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

Los esquemas de base de datos deben estar creados con **encoding Modern_Spanish_CI_AS**. Se definen
en el documento: [Manual Instalación AL SIGM](Manual-de-instalación-AL-SIGM.html)


### Ejecución manual de scripts para cada esquema

En la siguiente tabla se indica por cada esquema los scripts a ejecutar indicando la ruta
donde se encuentra cada script en la distribución de AL SIGM (se toma como directorio
base el directorio `Aplicaciones\Plantillas BBDD Multientidad\SQLServer`):


|Esquema|Scripts|
|:----|:----|
|sigemAdmin|`sigemAdmin/sigemAdmin.sql`|
|archivoDS_000|`archivo/01.archivo-create-tables-sqlserver.sql`<br>`archivo/02.archivo-create-indexes-sqlserver.sql`<br>`archivo/03.archivo-insert-data-sqlserver.sql`<br>`archivo/04.archivo-insert-clob-sqlserver.sql`<br>`archivo/05.archivo-create-functions-sqlserver.sql`<br>`archivo/06.archivo-create-procedures-sqlserver.sql`<br>`archivo/complementario/archivo-organizacion-bd/01.archivo-organizacion-bd-create-tables-sqlserver.sql`<br>`archivo/complementario/archivo-organizacion-bd/02.archivo-organizacion-bd-create-indexessqlserver.sql`<br>`archivo/07.archivo-add-grants-functions-procedures-sqlserver.sql` (Hay que modificar el script para poner el usuario deseado)<br>`archivo/complementario/archivo-busqueda-documental/01.archivo-create-documentarysearch-sqlserver.sql`|
|eTramitacionDS_000|`eTramitacion/01_create_tables.sql`<br>`eTramitacion/02_create_indexes_constraints.sql`<br>`eTramitacion/03_insert_data.sql`<br>`eTramitacion/04_insert_data_tasks.sql`<br>`csv/fwktd-csv-create.sql`<br>`eTramitacion/05_insert_data_csv_fwktd_module.sql`|
|tramitadorDS_000|`tramitador/01-create_tables.sql`<br>`tramitador/02-create_indexes_constraints.sql`<br>`tramitador/03-create_sequences.sql`<br>`tramitador/04-create_views.sql`<br>`tramitador/05-create_procedures.sql`<br>`tramitador/06-datos_iniciales.sql`<br>`tramitador/07-plantillas_iniciales.sql`<br>`tramitador/08-informes_estadisticos.sql`<br>`tramitador/21-prototipos_create_tables.sql`<br>`tramitador/22-prototipos_create_indexes_constraints.sql`<br>`tramitador/23-prototipos_create_sequences.sql`<br>`tramitador/24-prototipos_datos.sql`<br>`tramitador/25-prototipos_plantillas.sql`<br>`tramitador/26-prototipos_actualizacion_permisos.sql`<br>`tramitador/27-prototipos_configuracion_publicador.sql`<br>`tramitador/41-prototipos_v1.9_create_tables.sql`<br>`tramitador/42-prototipos_v1.9_create_indexes_constraints.sql`<br>`tramitador/43-prototipos_v1.9_create_sequences.sql`<br>`tramitador/44-prototipos_v1.9_datos.sql`<br>`tramitador/45-prototipos_v1.9_plantillas.sql`|
|registroDS_000|`registro/01.1_create_tables_registro_sigem_sqlServer.sql`<br>`registro/01.2_create_tables_invesdoc_registro_sigem_sqlServer.sql`<br>`registro/01.3_create_views_invesdoc_registro_sigem_sqlServer.sql`<br>`registro/02.1_create_indexes_constraints_registro_sigem_sqlServer.sql`<br>`registro/02.2_create_indexes_constraints_invesdoc_registro_sigem_sqlServer.sql`<br>`registro/03.1_insert_data_registro_sigem_sqlServer.sql`<br>`registro/03.2_insert_data_invesdoc_registro_sigem_sqlServer.sql`<br>`registro/04_insert_text_registro_sigem_sqlServer.sql`<br>`registro/05-sicres3.sql`<br>`sigemEstructuraOrganizativa/01.1_create_tables_sigem_estructura_organizativa.sql`<br>`sigemEstructuraOrganizativa/02.1_create_indexes_constraints_estructura_organizativa.sql`<br>`create_user_consolidacion.sql`<br>`repositorios_registro_sigem_sqlServer.sql`|
|fwktd-dir3DS|`dir3/fwktd-dir3-create.sql`<br>`dir3/fwktd-dir3-insert.sql`|
|fwktd-sirDS_000|`sir/fwktd-sir-create.sql`<br>`sir/fwktd-sir-insert.sql`<br>`sir/fwktd-dm-bd-create.sql`<br>`sir/fwktd-dm-bd-insert.sql`|
|fwktd-auditDS_000|`audit/fwktd-audit-create.sql`<br>`tramitador/50-tramitador_auditoria_datos.sql`<br>`registro/06-insert_data_registro_auditoria_datos_sqlServer.sql`|


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
|`fwktd-audit\fwktd-audit-api.properties`|**Sustituir:**<br>`fwktd-audit.database=postgresql`<br>**Por:**<br>`fwktd-audit.database=sqlserver`|
|`fwktd-csv\fwktd-csv-api.properties`|**Sustituir:**<br>`fwktd-csv.database=postgresql`<br>**Por:**<br>`fwktd-csv.database=sqlserver`|
|`fwktd-sir\fwktd-sir-api.properties`|**Sustituir:**<br>`fwktd-sir.database=postgresql`<br>**Por:**<br>`fwktd-sir.database=sqlserver`|
|`SIGEM_ArchivoWeb\archivo-cfg.xml`|**Sustituir:**<br>`<DB_Factory_Class>common.db.DBEntityFactoryPostgreSQL</DB_Factory_Class>`<br>**Por:**<br>`<DB_Factory_Class>common.db.DBEntityFactorySqlServer2000</DB_Factory_Class>`|
|`SIGEM_Core\database.properties`|**Sustituir:**<br>`sigem.springDatasource.database=jdbc:postgresql://localhost/sigemAdmin`<br>`sigem.springDatasource.user=postgres`<br>`sigem.springDatasource.password=postgres`<br>`sigem.springDatasource.driver=org.postgresql.Driver`<br>**Por **(con la url y usuario y contraseña correctos):<br>`sigem.springDatasource.database=jdbc:sqlserver://localhost:1433`<br>`sigem.springDatasource.user=sigemAdmin`<br>`sigem.springDatasource.password=sigemAdmin`<br>`sigem.springDatasource.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver`|
|`SIGEM_RegistroPresencial\database.properties`|**Sustituir:**<br>`isicres.database=postgres`<br>**Por:**<br>`isicres.database=sqlserver`|
|`SIGEM_RegistroPresencial\hibernate.cfg.xml`|**Sustituir:**<br>`<property name="dialect">net.sf.hibernate.dialect.PostgreSQLDialect</property>`<br>**Por:**<br>`<property name="dialect">net.sf.hibernate.dialect.OracleDialect</property>`|
|`SIGEM_RegistroPresencial\ISicres-Configuration.xml`|**Sustituir:**<br>`<DAOImplementation>com.ieci.tecdoc.common.entity.dao.PostgreSQLDBEntityDAO</DAOImplementation>`<br>**Por:**<br>`<DAOImplementation>com.ieci.tecdoc.common.entity.dao.SQLServerOracleDBEntityDAO</DAOImplementation>`|


