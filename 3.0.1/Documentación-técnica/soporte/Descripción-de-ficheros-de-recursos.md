---
layout: default
title: Descripción del ficheros de log
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Guías y ayudas/SGM_2012_10_Descripción de ficheros de recursos.pdf](pdfs/SGM_2012_10_Descripcion_de_ficheros_de_recursos.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*


## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto de este documento es la enumeración de los distintos ficheros de recursos de
las aplicaciones que componen AL SIGM.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Ficheros de Recursos

### Aplicaciones Web

SIGEM_AdministracionUsuariosWeb

1. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_AdministracionUsuariosWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente `displaytag` en la aplicación.
2. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_AdministracionUsuariosWeb/WEBINF/classes/ieci/tecdoc/sgm/usuario/admin/struts`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_AdministracionWeb

1. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_AdministracionWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente displaytag en la aplicación.
2. *Fichero*: `AdministracionMessage.properties`
	- Localización: `SIGEM_AdministracionWeb/WEBINF/classes/ieci/tecdoc/sgm/admsistema/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_ArchivoWeb

1. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_ArchivoWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente displaytag en la aplicación.
2. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_ArchivoWeb/WEB-INF/classes/resources`
	- Descripción: Fichero de mensajes de la aplicación.
3. *Fichero*: `OrganizationResources.properties`
	- Localización: `ieci/tecdoc/sgm/sesiones/exception`
	- Descripción: Fichero de mensajes de error del API de Administración de Sesiones.

### SIGEM_AutenticacionAdministracionWeb

1. *Fichero*: `AutenticacionAdministracionMessage.properties`
	- Localización: `SIGEM_AutenticacionBackOfficeWeb/WEBINF/classes/ieci/tecdoc/sgm/administracion/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_AutenticacionBackOfficeWeb

1. *Fichero*: `AutenticacionBackOfficeMessage.properties`
	- Localización: `SIGEM_AutenticacionBackOfficeWeb/WEBINF/classes/ieci/tecdoc/sgm/backoffice/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_AutenticacionWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_AutenticacionWeb/WEB-INF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_CatalogoProcedimientosWeb

1. *Fichero*: `FixedHolidays.xml`
	- Localización: `SIGEM_CatalogoProcedimientosWeb/ispac/xml`
	- Descripción: Fichero de configuración de los días festivos fijos del calendario.
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_CatalogoProcedimientosWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente displaytag en la aplicación.
3. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_CatalogoProcedimientosWeb/WEBINF/classes/ieci/tdw/ispac/ispaccatalog/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_CatalogoTramitesWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_CatalogoTramites/WEB-INF/classes/resources`
	- Descripción: Contiene los mensajes y textos utilizados por la aplicación

### SIGEM_CertificacionWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_GeoLocalizacionWeb/WEB-INF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación

### SIGEM_ConsultaExpedienteBackOfficeWeb

1. *Ficheros*: `error.properties`  , `lenguaje.properties`
	- Localización: `SIGEM_ConsultaExpedienteBackOfficeWeb/WEBINF/classes/ieci/tecdoc/sgm/ct/resources`
	- Descripción: Contiene los mensajes de error y textos utilizados por la aplicación
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_ConsultaExpedienteBackOfficeWeb/WEB-INF/classes`
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_ConsultaRegistroTelematicoBackOfficeWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_ConsultaRegistroTelematicoBackOfficeWeb/WEBINF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_ConsultaRegistroTelematicoBackOfficeWeb/WEBINF/classes`
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_ConsultaRegistroTelematicoWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_ConsultaRegistroTelematicoWeb/WEBINF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación
2. *Fichero*: `displaytag.properties`
	- Localización: SIGEM_ConsultaRegistroTelematicoWeb/WEB-INF/classes
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_ConsultaWeb

1. *Ficheros*: `error.properties` , `lenguaje.properties`
	- Localización: `SIGEM_ConsultaWeb/WEBINF/classes/ieci/tecdoc/sgm/ct/resources`
	- Descripción: Contiene los mensajes de error y textos utilizados por la aplicación
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_ConsultaWeb/WEB-INF/classes`
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_EstructuraWeb

1. *Fichero*: `invesDoc_Admin_Error_Messages.properties`
	- Localización: `SIGEM_EstructuraWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación 
2. *Fichero*: `application.properties`
	- Localización: `SIGEM_EstructuraWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de la aplicación 
3. *Fichero*: `errors.properties`
	- Localización: `SIGEM_EstructuraWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación

### SIGEM_GeoLocalizacionWeb

1. *Fichero*: `vias.properties`
	- Localización: `SIGEM_GeoLocalizacionWeb/WEBINF/classes/ieci/tecdoc/sgm/geolocalizacion/resources`
	- Descripción: Fichero de recursos de la aplicación referente a vias de las calles.
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_GeoLocalizacionWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de la aplicación referente tablas
3. *Fichero*: `resources.properties`
	- Localización: `SIGEM_GeoLocalizacionWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de la aplicación referente tablas

### SIGEM_GestionCSVWeb

1. *Fichero*: `custom.properties`
	- Localización: `SIGEM_GestionCSVWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de la aplicación
2. *Fichero*: `errors.properties`
	- Localización: `SIGEM_GestionCSVWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación
3. *Fichero*: `labels.properties`
	- Localización: `SIGEM_GestionCSVWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de las etiquetas de la aplicación
4. *Fichero*: `messages.properties`
	- Localización: `SIGEM_GestionCSVWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de los mensajes de la aplicación

### SIGEM_NotificacionWeb

1. *Fichero*: `ApplicationResource.properties`
	- Localización: `SIGEM_NotificacionWeb/WEBINF/classes/ieci/tecdoc/sgm/nt/struts/configuracion`
	- Descripción: Contiene los mensajes de error y textos utilizados por la aplicación
2. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_NotificacionWeb/WEB-INF/clases/`
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_PagoElectronicoWeb

1. *Fichero*: `ReceiptCreator.properties`
	- Localización: `SIGEM_PagoElectronico/WEBINF/classes/ieci/tecdoc/sgm/pe/struts/receipt/resources`
	- Descripción: Fichero de configuración de la aplicación 
2. *Fichero*: `ApplicationResource.properties`
	- Localización: `SIGEM_PagoElectronico/WEBINF/classes/ieci/tecdoc/sgm/pe/struts`
	- Descripción: Contiene los mensajes de error y textos utilizados por la aplicación
3. *Ficheros*: `displaytag.properties`
	- Localización: `SIGEM_PagoElectronico/WEB-INF/classes`
	- Descripción: Ficheros de configuración del componente displayTag en la aplicación

### SIGEM_PublicadorWeb

1. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_PublicadorWeb/WEBINF/classes/ieci/tdw/ispac/ispacpublicador/business/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_PublicadorWS

1. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_PublicadorWS/WEBINF/classes/ieci/tecdoc/sgm/publicador/ws/server/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_RegistroPresencialAdminWeb

1. *Fichero*: `ApplicationResource.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/classes`
	- Descripción: Contiene mensajes de diferentes naturalezas
2. *Fichero*: `displaytag.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases`
	- Descripción: Contiene mensajes de las tablas presentes en la aplicación
3. *Fichero*: `ISicresDocumentoElectronico.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos relacionados con documentos electrónicos
4. *Fichero*: `ISicres-IntercambioRegistralErrores.properties`(más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos de errores en el intercambio registral
5. *Fichero*: `ISicres-IntercambioRegistral.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos relacionados con el intercambio registral
6. *Fichero*: `ISicres-resources.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Ficheros de recursos de la aplicación 
7. *Fichero*: `ISicres-Server.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Ficheros de recursos de la aplicación
8. *Fichero*: `TecDocException.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialAdminWeb/WEB-INF/clases/resources`
	- Descripción: Contiene los textos error que utilizan las excepciones que se lanzan por el codigo que se encuentra en `ISicres-Server`, `ISicres-ServerCore` e `ISicres-Common`

### SIGEM_RegistroPresencialWeb

1. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente displaytag en la aplicación.
2. *Fichero*: `errors.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes`
	Descripción: Fichero de recursos de errores de la aplicación 
3. *Fichero*: `labels.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de las etiquetas de la aplicación
4. *Fichero*: `messages.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de los mensajes de la aplicación
5. *Fichero*: `ISicres-DesktopWeb.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes/resources`
	- Descripción: Contiene mensajes de todo tipo, pero que solo son utilizados
por el codigo de la aplicacion que se encuentra en `ISicres-Desktop-Jar`
6. *Fichero*: `ISicresDocumentoElectronico.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos relacionados con documentos electrónicos
7. *Fichero*: `ISicres-IntercambioRegistralErrores.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos de errores en el intercambio registral
8. *Fichero*: `ISicres-IntercambioRegistral.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/clases/resources`
	- Descripción: Fichero de recursos relacionados con el intercambio registral
9. *Fichero*: `ISicres-resources.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/clases/resources`
	- Descripción: Ficheros de recursos de la aplicación
10. *Fichero*: `ISicres-Server.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes/resources`
	- Descripción: Contiene los textos de los formularios que se cargan dinamicamente.
11. *Fichero*: `TecDocException.properties` (más ficheros con sufijo referente a los diferentes idiomas traducidos)
	- Localización: `SIGEM_RegistroPresencialWeb/WEB-INF/classes/resources`
	- Descripción: Contiene los textos error que utilizan las excepciones que se lanzan por el codigo que se encuentra en `ISicres-Server`, `ISicres-ServerCore` e `ISicres-Common`


### SIGEM_RegistroTelematicoWeb

1. *Fichero*: `application.properties`
	- Localización: `SIGEM_RegistroTelematicoWeb/WEB-INF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_RepositoriosDocumentalesWeb

1. *Fichero*: `invesDoc_Admin_Error_Messages.properties`
	- Localización: `SIGEM_RepositoriosDocumentalesWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación
2. *Fichero*: `application.properties`
	- Localización: `SIGEM_RepositoriosDocumentalesWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de la aplicación 
3. *Fichero*: `errors.properties`
	- Localización: `SIGEM_RepositoriosDocumentalesWeb/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación

### SIGEM_SchedulerWeb

1. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_SchedulerWeb/WEB-INF/classes/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_SignoWS

1. *Fichero*: `errors.properties`
	- Localización: `SIGEM_SignoWS/WEB-INF/classes`
	- Descripción: Fichero de recursos de errores de la aplicación.

### SIGEM_TercerosWS

1. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_TercerosWS/WEBINF/classes/ieci/tecdoc/sgm/terceros/ws/server/resources`
	- Descripción: Fichero de recursos de la aplicación.

### SIGEM_TramitacionWeb

1. *Fichero*: `displaytag.properties`
	- Localización: `SIGEM_TramitacionWeb/WEB-INF/classes`
	- Descripción: Fichero de configuración del componente displaytag en la aplicación.
2. *Fichero*: `ApplicationResources.properties`
	- Localización: `SIGEM_TramitacionWeb/WEBINF/classes/ieci/tdw/ispac/ispacmgr/resources`
	- Descripción: Fichero de recursos de la aplicación.
3. *Fichero*: `CustomExceptionMessages.properties`
	- Localización: `SIGEM_TramitacionWeb/ WEBINF/classes/ieci/tdw/ispac/api/errors`
	- Descripción: Fichero de recursos de errores en la aplicación.

### SIGEM_TramitacionWS

1. Fichero: `ApplicationResources.properties`
	- Localización: `SIGEM_TramitacionWS/WEBINF/classes/ieci/tecdoc/sgm/tram/ws/server/resources`
	- Descripción: Fichero de recursos de la aplicación.

