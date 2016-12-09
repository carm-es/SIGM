---
layout: default
title: Configuración para PostgreSQL 9.0.3
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para PostgreSQL 9.0.3.pdf](pdfs/SGM_2012_10_Configuración_para_PostgreSQL_9.0.3.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para configurar la base de
datos Postgresql 9.0.3 para la correcta ejecución de las aplicaciones proporcionadas
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

Los esquemas de base de datos deben estar creados con **encoding LATIN9**. Se definen
en el documento: [Manual Instalación AL SIGM](Manual-de-instalación-AL-SIGM.html)

### Diccionarios para búsqueda documental

Para soportar la búsqueda documental en idioma español es necesario añadir los
siguientes ficheros a la base de datos:

* `es_es.affix`
* `es_es.dict`
* `es_es.stop`

Estos ficheros se encuentran en el CD de distribución en la siguiente ruta: 
`Plantillas BBDD Multientidad\PostgreSQL\posterior_igual_8.3\diccionarios_busqueda_documental`

Estos ficheros se deberán copiar al directorio  `/usr/share/postgresql/tsearch_data/`

### Ejecución manual de scripts para cada esquema

En la siguiente tabla se indica por cada esquema los scripts a ejecutar indicando la ruta
donde se encuentra cada script en la distribución de AL SIGM (se toma como directorio
base el directorio `Aplicaciones\Plantillas BBDD Multientidad\PostgreSQL`):



|Esquema|Scripts|
|:----|:----|
|sigemAdmin|`sigemAdmin/sigemAdmin.sql`|
|archivoDS_000|`archivo/01.archivo-create-tables-postgres.sql`<br>`archivo/02.archivo-create-indexes-postgres.sql`<br>`archivo/03.archivo-insert-data-postgres.sql`<br>`archivo/04.archivo-insert-text-postgres.sql`<br>`archivo/05.archivo-create-functions-postgres.sql`<br>`archivo/06.archivo-personalization-postgres.sql`<br>`archivo/complementario/archivo-organizacion-bd/01.archivo-organizacion-bd-create-tablespostgres.sql`<br>`archivo/complementario/archivo-organizacion-bd/02.archivo-organizacion-bd-create-indexespostgres.sql`<br>`archivo/complementario/archivo-busqueda-documental/posterior_igual_8.3/01.archivo-createdocumentary-search-postgres.sql`<br>`archivo/complementario/archivo-busqueda-documental/posterior_igual_8.3/02.archivo-insertdocumentary-search-postgres.sql`|
|eTramitacionDS_000|`eTramitacion/01_create_tables.sql`<br>`eTramitacion/02_create_indexes_constraints.sql`<br>`eTramitacion/03_insert_data.sql`<br>`eTramitacion/04_insert_data_tasks.sql`<br>`csv/fwktd-csv-create.sql`<br>`eTramitacion/05_insert_data_csv_fwktd_module.sql`|
|tramitadorDS_000|`tramitador/01-create_tables.sql`<br>`tramitador/02-create_indexes_constraints.sql`<br>`tramitador/03-create_sequences.sql`<br>`tramitador/04-create_views.sql`<br>`tramitador/05-create_procedures.sql`<br>`tramitador/06-datos_iniciales.sql`<br>`tramitador/07-plantillas_iniciales.sql`<br>`tramitador/08-informes_estadisticos.sql`<br>`tramitador/21-prototipos_create_tables.sql`<br>`tramitador/22-prototipos_create_indexes_constraints.sql`<br>`tramitador/23-prototipos_create_sequences.sql`<br>`tramitador/24-prototipos_datos.sql`<br>`tramitador/25-prototipos_plantillas.sql`<br>`tramitador/26-prototipos_actualizacion_permisos.sql`<br>`tramitador/27-prototipos_configuracion_publicador.sql`<br>`tramitador/41-prototipos_v1.9_create_tables.sql`<br>`tramitador/42-prototipos_v1.9_create_indexes_constraints.sql`<br>`tramitador/43-prototipos_v1.9_create_sequences.sql`<br>`tramitador/44-prototipos_v1.9_datos.sql`<br>`tramitador/45-prototipos_v1.9_plantillas.sql`|
|registroDS_000|`registro/01.1_create_tables_registro_sigem_postgres.sql`<br>`registro/01.2_create_tables_invesdoc_registro_sigem_postgres.sql`<br>`registro/01.3_create_views_invesdoc_registro_sigem_postgres.sql`<br>`registro/02.1_create_indexes_constraints_registro_sigem_postgres.sql`<br>`registro/02.2_create_indexes_constraints_invesdoc_registro_sigem_postgres.sql`<br>`registro/03.1_insert_data_registro_sigem_postgres.sql`|
|fwktd-dir3DS|`dir3/fwktd-dir3-create.sql`<br>`dir3/fwktd-dir3-insert.sql`|
|fwktd-sirDS_000|`sir/fwktd-sir-create.sql`<br>`sir/fwktd-sir-insert.sql`<br>`sir/fwktd-dm-bd-create.sql`<br>`sir/fwktd-dm-bd-insert.sql`|
|fwktd-auditDS_000|`audit/fwktd-audit-create.sql`<br>`tramitador/50-tramitador_auditoria_datos.sql`<br>`registro/06-insert_data_registro_auditoria_datos_postgres.sql`|


