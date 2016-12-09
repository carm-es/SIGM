---
layout: default
title: Configuración del servicio de Geolocalización
---

> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración del servicio de GeoLocalización.pdf](pdfs/SGM_2012_10_Configuracion-del-servicio-de-GeoLocalizacion.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objetivo del presente documento es explicar la configuración del servicio de GeoLocalización.


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

En SIGM hay actualmente dos implementaciones del servicio de GeoLocalización: API y WEBSERVICE.

La implementación a utilizar se establece en el fichero de configuración externalizado `SIGEM_Core/SIGEM_spring.properties`. Los parámetros son:

``` ini
# Implementación del servicio. Valores posibles:
# - SIGEM_ServicioGeoLocalizacion.SIGEM.API
# - SIGEM_ServicioGeoLocalizacion.SIGEM.WEBSERVICE
#
GEOLOCALIZACION_DEFAULT_IMPL=SIGEM_ServicioGeoLocalizacion.SIGEM.API

# URL del servicio web
GEOLOCALIZACION_WS_ENDPOINT=http://localhost:8080/SIGEM_GeoLocalizacionWS/services/GeoLocalizacionWebService
```

### Validación mediante API

La configuración del API se establece en el fichero de configuración externalizado `SIGEM_GeoLocalizacion/GeoLocalizacion.properties`

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
service.ip=http://localgis.mityc.es:8080/LOCALGIS-wfs-mne/services

# Configuración interna del conector WFS
filter.validate.via=<Filter><And><PropertyIsLike wildCard=\"*\" singleChar=\"_\" escapeChar=\"|\"><PropertyName>nombreEntidad/nombre</PropertyName><Literal>##NOMBRE_VIA##</Literal></PropertyIsLike><PropertyIsLike wildCard=\"*\" singleChar=\"_\" escapeChar=\"|\"><PropertyName>entidadLocal/municipio</PropertyName><Literal>##CODIGO_INE##</Literal></PropertyIsLike></And></Filter>

filter.validate.portal=<Filter><And><PropertyIsLike wildCard=\"*\" singleChar=\"_\" escapeChar=\"|\"><PropertyName>entidadRelacionada/idEntidad</PropertyName><Literal>##ID_VIA##</Literal></PropertyIsLike><PropertyIsLike wildCard=\"*\" singleChar=\"_\" escapeChar=\"|\"><PropertyName>nombreEntidad/nombre</PropertyName><Literal>##NUMERO_PORTAL##</Literal></PropertyIsLike></And></Filter>

filter.validate.idPortal=<Filter xmlns:gml=\"http://www.opengis.net/gml\"><PropertyIsLike wildCard=\"*\" singleChar=\"_\" escapeChar=\"|\"><PropertyName>fid</PropertyName><Literal>##ID_PORTAL##</Literal></PropertyIsLike></Filter>

param.request=GetFeature
param.version=1.1.0
param.service=WFS
param.namespace=xmlns(app=http://www.deegree.org/app)
param.via.typename=app:Via
param.portal.typename=app:Portal
```

> *NO UTILIZAR ESTE CONTENIDO*: El contenido de este fichero de configuración ha sido formateado para presentarlo en este documento. No se debe copiar y pegar para su uso en la herramienta SIGM.

> En caso de necesitar configurar un proxy para la conexión con AL LocalGIS, se recomienda configurar estos parámetros (`http.proxyHost`, `http.proxyPort`, `http.proxyUser`, `http.proxyPassword`, `http.nonProxyHosts`) a nivel de servidor de aplicaciones, en lugar de hacerlo en este fichero de configuración, porque aplican sobre todas las aplicaciones desplegadas

### Validación mediante WEBSERVICE

Como se indica anteriormente, la configuración del servicio web se estable en el fichero `SIGEM_spring.properties`.

La configuración por defecto es:

``` ini
# URL del servicio web
GEOLOCALIZACION_WS_ENDPOINT=http://localhost:8080/SIGEM_GeoLocalizacionWS/services/GeoLocalizacionWebService
``` 

