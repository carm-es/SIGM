---
layout: default
title: Configuración para la integración con ECO
---

> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración para la integración con ECO.pdf](pdfs/SGM_2012_10_Configuracion_para_la_integración_con_ECO.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto de este documento es explicar la configuración para la integración con ECO Logo.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Configuración

Para integrar el logo ECO es necesario configurar las siguientes propiedades en el
fichero externalizado que se encuentra en la ruta `/SIGEM_ECOLogo/EcoLogoConfig.properties`

Las propiedades que se pueden modificar son las siguientes. Los valores que se
muestran son los valores configurados por defecto:

``` ini 
# Nombre del servidor del Ministerio que alberga el logo.
srepals.logger.host=sedeaplicaciones2.minetur.gob.es

# Puerto de escucha por el que se sirve el logo.
srepals.logger.port=80

# Ruta del servlet que devuelve el logo.
srepals.logger.servlet=SREPALSLogger/logoServlet

# DNS de la aplicación. Si se deja vacío coge el nombre del servidor
application.dns=www.prueba.es

# Nombre de las aplicaciones registradas
application.name.A=SIGM.archivo
application.name.RP=SIGM.registro
application.name.TM=SIGM.tramitador
application.name.C=SIGM.ciudadano

# Versión de la aplicación de SIGEM
application.version=3.0

# Ancho en pixels del iframe que muestra el logo
iframe.width=226
# Alto en pixels del iframe que muestra el logo
iframe.height=61

# Ancho en pixels de la imagen del logo
logo.width=226

# Alto en pixels de la imagen del logo
logo.height=61

``` 


