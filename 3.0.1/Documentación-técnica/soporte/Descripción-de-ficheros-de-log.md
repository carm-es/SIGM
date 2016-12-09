---
layout: default
title: Descripción del ficheros de log
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Descripción de ficheros de log.pdf](pdfs/SGM_2012_10_Descripcion_de_ficheros_de_log.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*



## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto de este documento es la enumeración de los distintos ficheros de log que hay
en las aplicaciones que componen AL SIGM.

### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|


## Ficheros de log

### Configuración de los logs
El sistema de logs usado por AL SIGM es el `log4j`, un framework de referencia en el mundo J2EE.

Por defecto `log4J` tiene 6 niveles de prioridad para los mensajes (`trace`, `debug`, `info`,
`warn`, `error`, `fatal`). Además existen otros dos niveles extras (`all` y `off`).

Niveles de prioridad de mayor *(poco detalle)* a menor *(mucho detalle)*:

* `FATAL`: se utiliza para mensajes críticos del sistema, generalmente después de guardar el mensaje el programa abortará.
* `ERROR`: se utiliza en mensajes de error de la aplicación que se desea guardar, estos eventos afectan al programa pero lo dejan seguir funcionando, como por ejemplo que algún parámetro de configuración no es correcto y se carga el parámetro por defecto.
* `WARN`: se utiliza para mensajes de alerta sobre eventos que se desea mantener constancia, pero que no afectan al correcto funcionamiento del programa.
* `INFO`: se utiliza para mensajes similares al modo "verbose" en otras aplicaciones.
* `DEBUG`: se utiliza para escribir mensajes de depuración. Este nivel no debe estar activado cuando la aplicación se encuentre en producción.
* `TRACE`: se utiliza para mostrar mensajes con un mayor nivel de detalle que debug.

Extras:

* `ALL`: este es el nivel de máximo detalle, habilita todos los logs (en general equivale a `TRACE`).
* `OFF`: este es el nivel de mínimo detalle, deshabilita todos los logs.

### Ficheros a modificar para establecer el nivel de log

La configuración de `log4j` para las diferentes aplicaciones se encuentra externalizada

Los ficheros de configuración, relativos al directorio de configuración externalizada, son:

```
./SIGEM_AdministracionSesionesAdmWS/log4j.xml
./SIGEM_AdministracionSesionesBackOfficeWS/log4j.xml
./SIGEM_AdministracionUsuariosWeb/log4j.properties
./SIGEM_AdministracionWeb/log4j.properties
./SIGEM_AntivirusWS/log4j.xml
./SIGEM_ArchivoWeb/log4j.xml
./SIGEM_AutenticacionAdministracionWeb/log4j.xml
./SIGEM_AutenticacionBackOfficeWeb/log4j.xml
./SIGEM_AutenticacionUsuariosWS/log4j.xml
./SIGEM_AutenticacionWeb/log4j.xml
./SIGEM_BuscadorDocsWeb/log4j.properties
./SIGEM_CalendarioWS/log4j.properties
./SIGEM_CatalogoProcedimientosWeb/log4j.xml
./SIGEM_CatalogoTramitesWS/log4j.properties
./SIGEM_CatalogoTramitesWeb/log4j.properties
./SIGEM_CatastroWS/log4j.xml
./SIGEM_CatastroWeb/log4j.xml
./SIGEM_CertificacionWS/log4j.xml
./SIGEM_CertificacionWeb/log4j.properties
./SIGEM_ConsultaExpedienteBackOfficeWeb/log4j.properties
./SIGEM_ConsultaRegistroTelematicoBackOfficeWeb/log4j.properties
./SIGEM_ConsultaRegistroTelematicoWeb/log4j.properties
./SIGEM_ConsultaWS/log4j.properties
./SIGEM_ConsultaWeb/log4j.properties
./SIGEM_CriptoValidacionWS/log4j.properties
./SIGEM_EntidadesWS/log4j.properties
./SIGEM_EstructuraOrganizativaWS/log4j.xml
./SIGEM_EstructuraWeb/log4j.xml
./SIGEM_FirmaDigitalWS/log4j.properties
./SIGEM_GeoLocalizacionWS/log4j.xml
./SIGEM_GeoLocalizacionWeb/log4j.properties
./SIGEM_GestionCSVWS/log4j.xml
./SIGEM_GestionCSVWeb/log4j.xml
./SIGEM_MensajesCortosWS/log4j.xml
./SIGEM_NotificacionUpdaterWeb/log4j.properties
./SIGEM_NotificacionWS/log4j.properties
./SIGEM_NotificacionWeb/log4j.properties
./SIGEM_PagoElectronicoWS/log4j.properties
./SIGEM_PagoElectronicoWeb/log4j.properties
./SIGEM_PublicadorWS/log4j.xml
./SIGEM_PublicadorWeb/log4j.xml
./SIGEM_RdeWS/log4j.properties
./SIGEM_RegistroPresencialAdminWeb/log4j.properties
./SIGEM_RegistroPresencialWS/Isicres/log4j.xml
./SIGEM_RegistroPresencialWeb/log4j.xml
./SIGEM_RegistroTelematicoDefaultTercerosConnectorWS/log4j.xml
./SIGEM_RegistroTelematicoWS/log4j.xml
./SIGEM_RegistroTelematicoWeb/log4j.properties
./SIGEM_RepositoriosDocumentalesWeb/log4j.xml
./SIGEM_SchedulerWeb/log4j.xml
./SIGEM_ServicioIntermediacionClienteLigeroWS/log4j.xml
./SIGEM_SessionUsuarioWS/log4j.properties
./SIGEM_SignoWS/log4j.properties
./SIGEM_TercerosWS/log4j.xml
./SIGEM_TramitacionWS/log4j.xml
./SIGEM_TramitacionWeb/log4j.xml
./fwktd-sir-ws/log4j.xml
``` 

En estos ficheros entre otras cosas podremos particularizar la ruta donde queremos
que se depositen los ficheros de log correspondientes, así como el nivel de detalle de
log.

### Enlaces de interés

* [http://logging.apache.org/log4j/](http://logging.apache.org/log4j/)
* [http://logging.apache.org/log4j/1.2/manual.html](http://logging.apache.org/log4j/1.2/manual.html)
* [http://wiki.apache.org/logging-log4j/](http://wiki.apache.org/logging-log4j/)
* [http://wiki.apache.org/logging-log4j/Log4jXmlFormat](http://wiki.apache.org/logging-log4j/Log4jXmlFormat)

