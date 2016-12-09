---
layout: default
title: Manual de Uso Herramienta Configuraciones básicas
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Manual de Uso Herramienta Configuraciones Básicas.pdf](pdfs/SGM_2012_10_Manual_de_Uso_Herramienta_Configuraciones_Basicas.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El presente documento contiene la información necesaria para ejecutar la herramienta
de generación de configuración básica que se proporciona con AL SIGM.




### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Ejecución de la Aplicación

La ejecución de la herramienta de generación de configuración básica requiere que se
tenga instalado correctamente Apache Maven en su versión 2.2.1 junto a una JDK 1.5,
así como acceso al código fuente de AL SIGM. Para la instalación y configuración de la
jdk y maven se puede consultar el siguiente documento:

[Configuración del entorno de desarrollo](Configuración-del-entorno-de-desarrollo.html)


Desde el directorio raíz del código fuente se podrá lanzar la ejecución de Maven con los
perfiles necesarios para la generación de la configuración.

A continuación se muestran varios ejemplos de ejecución:

|Comando|Descripción|
|:----|:----|
|`mvn clean package -Pgenerate-config Dmaven.test.skip=true `|Ejecuta el perfil de generación de configuración con los parámetros por defecto:<br> * Sistema operativo Unix<br> * Base de datos Postgres<br> * Servidor de aplicaciones Tomcat<br> * Autenticación Invesdoc<br> * Repositorio documental Invesdoc|
|`mvn clean package -Pgenerate-config,unix,postgres,tomcat,invesdoc,repIdoc -Dmaven.test.skip=true`|Ejecuta el perfil de generación de configuración con los parámetros deseados para sistema operativo, base de datos, servidor de aplicaciones, autenticación y repositorio documental |
|`mvn clean package -Pgenerateconfig,win,db2,websphere,ldap,repAlfresco -Dmaven.test.skip=true`|Ejecuta el perfil de generación de configuración con los parámetros deseados para sistema operativo, base de datos,servidor de aplicaciones, autenticación y repositorio documental|


Mediante el parámetro `-P` se le indican a Maven los perfiles que se activarán para la
construcción de la configuración. En la siguiente tabla se indica el tipo de cada perfil y
los valores que se deberían proporcionar para cada tipo de perfil. Es obligatorio dar
valor a cada uno de los perfiles y que los valores sean válidos, de otro modo es posible
que la construcción falle, aunque es posible generar una configuración por defecto
mediante el primer comando de ejemplo de la tabla anterior.

|Tipo de perfil|Descripción|Valores posibles|
|:----|:----|:----|
|Generación de configuración|Perfil para indicar que se va a construir la configuración|`generate-config`|
|Sistema operativo|Perfil para indicar el sistema operativo para el que se quiere generar la configuración |`unix`<br>`win`|
|Base de datos|Perfil para indicar la base de datos para la que se quiere generar la configuración|`postgres`<br>`sqlserver`<br>`db2`<br>`oracle`|
|Servidor de aplicaciones|Perfil para indicar el servidor de aplicaciones para el que se quiere generar la configuración|`tomcat`<br>`jbos`<br>`websphere`|
|Autenticación|Perfil para indicar el sistema de autenticación para el que se quiere generar la configuración|`invesdoc`<br>`ldap`|
|Repositorio|Perfil para indicar el repositorio documental para el que se quiere generar la configuración|`repIdoc`<br>`repAlfresco`|

El zip resultante de la ejecución (`sigem_config-x.x.zip`) con los ficheros necesarios
para la externalización de la configuración se almacena en el subdirectorio
`SIGEM_Config/target` una vez que termine la ejecución de Maven.

