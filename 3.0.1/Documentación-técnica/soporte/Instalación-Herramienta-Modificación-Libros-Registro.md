---
layout: default
title: Instalación Herramienta Modificación Libros de Registro
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Instalación Herramienta Modificación Libros Registro.pdf](pdfs/SGM_2012_10_Instalacion_Herramienta_Modificacion_Libros_Registro.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene el **manual de instalación** de las **aplicaciones
cliente-servidor**, distribuidas con AL SIGM, que permiten **modificar la estructura
de los libros de Registro de Entrada/Salida**, así como los *formularios* usados
para su posterior visualización en la aplicación de Registro Presencial.

### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Instalación de las aplicaciones
AL SIGM incluye un kit de herramientas para la Modificación de los Libros de Registro,
que incluye los siguientes elementos:

* `SIGM Modificación Libros Registro.rar`: aplicaciones para la Modificación de los Libros de Registro
* `SIGM.xml`: fichero necesario para activar el uso de las aplicaciones

Para la instalación de dicho dichas aplicaciones se deben cumplir los siguientes
requisitos y realizar los pasos indicados a continuación.


### Prerrequisitos de instalación

#### Requisitos hardware

* PC compatible con procesador Pentium a 133 MHz
* 1 GBytes de memoria
* 20 MBytes de espacio libre en disco para poder realizar la instalación

#### Requisitos software

* Sistema operativo:
	- Windows 2000 Profesional SP4
	- Windows XP SP1a o superior
	- Windows Vista
	- Windows 7
* Conectividad ODBC a la base de datos de Registro de AL SIGM

### Instalación

1. Descomprimir el fichero `SIGM Modificación Libros Registro.rar` en un directorio `[ruta_instalación]`.

2. Ejecutar la aplicación de Herramientas de sistema `[ruta_instalación]\bin\iDocTSys.exe`
	- Configurar la fuente de datos ODBC definida para la base de datos de Registro:
		* En el menú, seleccionar la opción *BD >> Conectar...* 
		* En la ventana mostrada, indicar los siguientes datos:
			- *Nombre*: DSN de la fuente de datos ODBC correspondiente a la base de datos de Registro de AL SIGM. Se puede seleccionar a través del botón *Examinar*
			- *Usuario*: Usuario de conexión a la base de datos de Registro de ALSIGM
			- *Contraseña*: Contraseña del usuario de conexión a la base de datos de Registro de ALSIGM
		* Pulsar el botón *Aceptar* para guardar los cambios.
	- Activar las aplicaciones instaladas:
		* En el menú, seleccionar la opción *Licencias >> Importar...*
		* Seleccionar el fichero proporcionado, `SIGM.xml`, y pulsar el botón *Abrir*
3. Ejecutar la aplicación de Herramientas de estación `[ruta_instalación]\bin\iDocTWS.exe`
	- Configurar la fuente de datos ODBC definida para la base de datos de Registro:
		* En el menú, seleccionar la opción de menú *Estación >> Estándar >> Configurar...*
		* En la ventana mostrada, en la pestaña Base de datos, indicar los siguientes datos:
			- *Nombre*: DSN de la fuente de datos ODBC correspondiente a la base de datos de Registro de AL SIGM. Se puede seleccionar a través del botón *Examinar*
			- *Usuario*: Usuario de conexión a la base de datos de Registro de ALSIGM
			- *Contraseña*: Contraseña del usuario de conexión a la base de datos de Registro de ALSIGM
		* Pulsar el botón Prueba para verificar la conexión.
		* Pulsar el botón Aceptar para guardar los cambios.


### Lista de aplicaciones instaladas

Una vez realizada la instalación, se podrá acceder a las siguientes aplicaciones:

|Aplicación|Ejecutable|
|:----|:----|
|Administrador de archivadores|`[ruta_instalación]\bin\IdocAdm.exe`|
|Editor de formatos|`[ruta_instalación]\bin\IdocEdit.exe`|

Para ello, se proporciona el usuario *administrador* por defecto, `SYSSUPERUSER / SYSPASSWORD`.

