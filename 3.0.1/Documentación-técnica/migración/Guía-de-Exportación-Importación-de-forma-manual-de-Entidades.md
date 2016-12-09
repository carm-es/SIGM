---
layout: default
title: Guía de Exportación-Importación manual de entidades
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Guías y ayudas/SGM_2012_10_Guía de Exportación-Importación de forma manual de Entidades.pdf](pdfs/SGM_2012_10_Guia_de_Exportacion-Importacion_de_forma_manual_de_Entidades.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto del presente documento es el de detallar las tareas que deben llevarse a cabo
para realizar la exportación e importación manual de entidades en AL SIGM, aún
cuando no se utiliza para ello la aplicación de Administración de Entidades.

El proceso de clonación de entidades se realizará siguiendo los siguientes pasos:

1. **Exportación de una entidad**

	1. Exportación de las bases de datos de la entidad. Esta tarea se realiza por parte del administrador de base de datos. Las bases de datos a exportar son:

		* `archivo_<id_entidad>`: BD de Archivo.
		* `eTramitacion_<id_entidad>`: BD de Tramitación Telemática.
		* `registro_<id_entidad>`: BD de Registro Presencial.
		* `tramitador_<id_entidad>`: BD de Gestión de Expedientes.
		* `fwktd-audit_<id_entidad>`: BD de Auditoría.
		* `fwktd-sir_<id_entidad>`: BD del Sistema de Intercambio Registral.

	2. Exportación del repositorio documental de la entidad.

2. **Importación de una entidad**

	1. Importación de las bases de datos de la nueva entidad. Esta tarea se realiza por el administrador de base de datos. Las bases de datos a importar son las mismas que en el proceso de exportación.
	2. Importación del repositorio documental de la entidad. 

		* Importación del contenido del repositorio documental.
		* Configuración del repositorio documental en la base de datos de registro.

	3. Creación de la entidad en base de datos.
	4. Configuración de la entidad en el servidor de aplicaciones:

		* Orígenes de datos.
		* Particularizaciones por entidad.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



##  Exportación de una entidad

### Exportación de las bases de datos

Esta tarea se realizará por parte del administrador de base de datos.

Las bases de datos a exportar son:

* `archivo_<id_entidad>`: BD de Archivo.
* `eTramitacion_<id_entidad>`: BD de Tramitación Telemática.
* `registro_<id_entidad>`: BD de Registro Presencial.
* `tramitador_<id_entidad>`: BD de Gestión de Expedientes.
* `fwktd-audit_<id_entidad>`: BD de Auditoría.
* `fwktd-sir_<id_entidad>`: BD del Sistema de Intercambio Registral.

### Exportación de repositorio documental

La exportación del repositorio documental se debe realizar haciendo una copia del
directorio FTP de cada repositorio dado de alta en la aplicación de Repositorios
Documentales.


## Importación de una entidad

### Importación de base de datos

Esta tarea se realizará por parte del administrador de base de datos.

Las bases de datos a importar son:

* `archivo_<id_entidad>`: BD de Archivo.
* `eTramitacion_<id_entidad>`: BD de Tramitación Telemática.
* `registro_<id_entidad>`: BD de Registro Presencial.
* `tramitador_<id_entidad>`: BD de Gestión de Expedientes.
* `fwktd-audit_<id_entidad>`: BD de Auditoría.
* `fwktd-sir_<id_entidad>`: BD del Sistema de Intercambio Registral.


### Importación de repositorio documental

En esta tarea se va a realizar en dos pasos:


#### Importación del contenido del repositorio documental
La importación del repositorio documental se debe realizar haciendo una copia de los
documentos en el servidor FTP, para a continuación modificar el identificador de
entidad en el nombre del directorio raíz de cada uno de los repositorios documentales.

#### Configuración del acceso al repositorio documental
En la base de datos de registro presencial de la entidad (`registro_<id_entidad>`)
hay que actualizar la ruta de los repositorios importados en el paso anterior.

Para ello hay que hacer una actualización del campo INFO en la tabla IVOLREPHDR,
modificando el identificador de entidad en el nombre del directorio del repositorio.

Por ejemplo, si se está importando un repositorio que se exportó de la entidad `000` y
se está importando en la entidad `001`, hay que modificar el valor del campo `INFO` que
para la entidad `000` podría ser:
```
"01.01"|"127.0.0.1,21,sigem,Ok04ddM=,/home/sigem/000_REPOSITORIO_REGISTRO"|1|3|3|0|0
```
Por el siguiente contenido:
```
"01.01"|"127.0.0.1,21,sigem,Ok04ddM=,/home/sigem/001_REPOSITORIO_REGISTRO"|1|3|3|0|0
```

### Creación de la entidad en la base de datos de sigemAdmin

En este paso, se dará de alta la entidad en la base de datos de administración de
entidades de `sigemAdmin`.

Los scripts SQL a ejecutar son:

1. Creación de la entidad en la tabla de `SGM_ADM_ENTIDADES`.  El script SQL tiene la forma:
```sql
INSERT INTO sgm_adm_entidades(id, nombrecorto, nombrelargo, codigo_ine)
     VALUES ('<id_entidad>', '<nombre_corto_entidad>', '<nombre_largo_entidad>','<codigo_ine>');
```
Donde:
	* `id_entidad`: Identificador de la nueva entidad.
	* `nombre_corto_entidad`: Nombre corto de la nueva entidad.
	* `nombre_largo_entidad`: Nombre descriptivo de la nueva entidad.
	* `codigo_ine`: Código INE de la nueva entidad (puede ser nulo).
Por ejemplo:
	```sql
INSERT INTO sgm_adm_entidades(id, nombrecorto, nombrelargo, codigo_ine)
     VALUES ('001', 'Entidad 001', 'Entidad 001', null);
```
2. Permisos de usuarios administradores sobre la nueva entidad en la tabla de `SGM_ADM_PERFILES`. El script SQL tiene la forma:
```sql
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('<id_entidad>', '<id_usuario>', '<id_aplicacion>');
```
Con el que se crea una entrada en la tabla para cada usuario administrador por aplicación que se quiera relacionar. Los usuarios administradores están en la tabla `SGM_ADM_USUARIOS` y las aplicaciones para las que se asignan
permisos son:

|Identificador|Aplicación|
|:----:|:----|
|1|Administración de Entidades|
|2|Administración de Estructura Organizativa|
|3|Catálogo de Procedimientos Administrativos|
|4|Administración de Archivo|
|5|Administración de Registro|
|6|Administración de Repositorio Documental|
|7|Catálogo de Trámites Telemáticos|
|8|Administración de Usuarios|

Por ejemplo:
```sql
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '1');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '2');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '3');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '4');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '5');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '6');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '7');
INSERT INTO sgm_adm_perfiles(id_entidad, id_usuario, id_aplicacion)
     VALUES ('001', 'administrador', '8');
```

### Configuración del servidor de aplicaciones

#### Orígenes de datos para la nueva entidad

En el servidor de aplicaciones se deben crear los orígenes de datos para los nuevos
esquemas de base de datos creados para la nueva entidad:

* `archivoDS_<id_entidad>`: origen de datos para Archivo.
* `eTramitacionDS_<id_entidad>`: origen de datos para Tramitación Telemática.
* `registroDS_<id_entidad>`: origen de datos para Registro Presencial.
* `tercerosDS_<id_entidad>`: origen de datos para Sistema de Terceros.
* `tramitadorDS_<id_entidad>`: origen de datos para Gestión de Expedientes.
* `tramitadorDS_<id_entidad>`: origen de datos para Gestión de Expedientes.
* `fwktd-auditDS_<id_entidad>`: origen de datos para Auditoría.
* `fwktd-sirDS_<id_entidad>`: origen de datos para Intercambio Registral.

y añadir una referencia a ellos en las aplicaciones web que los utilicen (por ejemplo,
allí donde se estén usando para la otra entidad origen de la clonación).


#### Particularizaciones de las aplicaciones por entidad

Hay que tener en cuenta aquellas personalizaciones por entidad realizadas en las aplicaciones web:

* Logos por defecto en las aplicaciones web:
```
conf/<webapp>/skin<id_entidad>
```
* Registro Telemático:
	- Formularios de trámites, bien en la configuración externalizada, o bien dentro de la propia aplicación `SIGEM_RegistroTelematicoWeb/tramites/<id_entidad>`
* Desarrollos específicos.
