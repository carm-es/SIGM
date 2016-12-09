---
layout: default
title: Configuración del Servicio de Catastro
---

> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración del servicio de Catastro.pdf](pdfs/SGM_2012_10_Configuracion_del_servicio_de_Catastro.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objetivo del presente documento es explicar la configuración del servicio de Catastro.

### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Implementaciones y su configuración

En SIGM hay actualmente dos implementaciones del servicio de Catastro: API y
WEBSERVICE.

La implementación a utilizar se establece en el fichero de configuración externalizado:
`SIGEM_Core/SIGEM_spring.properties`.  Los parámetros son:

``` ini
# Implementación del servicio. Valores posibles:
# - SIGEM_ServicioCatastro.SIGEM.API
# - SIGEM_ServicioCatastro.SIGEM.WEBSERVICE
#
CATASTRO_DEFAULT_IMPL=SIGEM_ServicioCatastro.SIGEM.API

# URL del servicio web
CATASTRO_WS_ENDPOINT=http://localhost:8080/SIGEM_CatastroWS/services/CatastroWebService
```


### Validación mediante API

La configuración del API se establece en el fichero de configuración externalizado `SIGEM_GeoLocalizacion/GeoLocalizacion.properties`.

La configuración por defecto es:

``` ini
# Autenticación en el servicio Web
security.mode=UsernameToken
security.usertoken.user=syssuperuser
security.usertoken.password=sysgeopass
security.usertoken.passwordType=PasswordText

# Configuración del proxy
http.proxyHost=
http.proxyPort=
http.proxyUser=
http.proxyPassword=

# Excepciones del proxy: a list of hosts that should be reached directly,
# bypassing the proxy. This is a list of patterns separated by '|'.
# The patterns may start or end with a '*' for wildcards.
# Any host matching one of these patterns will be reached through a direct
# connection instead of through a proxy.
http.nonProxyHosts=

# URL del servicio Web
service.url=http://localgis.mityc.es:8080/SOALocalGIS/services/ISOALocalGIS
```


> En caso de necesitar configurar un proxy para la conexión con AL LocalGIS, se recomienda configurar estos parámetros (`http.proxyHost`, `http.proxyPort`, `http.proxyUser`, `http.proxyPassword`, `http.nonProxyHosts`) a nivel de servidor de aplicaciones, en lugar de hacerlo en este fichero de configuración, porque aplican sobre todas las aplicaciones desplegadas.


### Validación mediante WEBSERVICE

Como se indica anteriormente, la configuración del servicio web se estable en el fichero `SIGEM_spring.properties`. La configuración por defecto es:

``` ini
# URL del servicio web
CATASTRO_WS_ENDPOINT= http://localhost:8080/SIGEM_CatastroWS/services/CatastroWebService

``` 

