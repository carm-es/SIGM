---
layout: default
title: Guía de actualización de versión
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Guía de actualización de versión.pdf](pdfs/SGM_2012_10_Guia_de_actualizacion_de_version.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

En este documento se enumeran los pasos más importantes, ordenadamente, para
realizar la actualización de versión de AL SIGM de la versión 2.3 a la 3.0.




### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|


## Actualización de la versión 2.3 a la 3.0

### Actualización de las bases de datos

Se deben crear los siguientes esquemas de base de datos:
* `fwktd-audit_000`
* `fwktd-dir3`
* `fwktd-sir_000`

Para la inicialización del modelo de datos en estos esquemas, se recomienda la
lectura del documento de instalación [Manual de Instalación de AL SIGM](../instalación/Manual-de-instalación-AL-SIGM.html)

###  Actualización de esquemas existentes
Se han actualizado los siguientes esquemas de base de datos:

* `archivo`
* `eTramitacion`
* `registro`
* `sigemAdmin`
* `tramitador`
* `sisnot`

Para actualizar estos esquemas, se deberán ejecutar los scripts correspondientes al
gestor de bases de datos (DB2, Oracle, PostgreSQL o SQLServer) para los módulos
que se quieran actualizar, en el orden indicado a continuación:

* **archivo**
	- `SIGEM_Parche-v2_3_0-v3_0_0-01-archivo.sql`
	- `SIGEM_Parche-v2_3_0-v3_0_0-02-functions-archivo.sql`
	- `SIGEM_Parche-v2_3_0-v3_0_0-03-archivo-clobs.sql`
* **eTramitacion**
	- `SIGEM-Parche-v2_3_0-v3_0_0-01-eTramitacion.sql`
	- `SIGEM-Parche-v2_3_0-v3_0_0-02-eTramitacion.sql`
* **registro**
	- `SIGEM-Parche-v2_3_0-v3_0_0-01-registro.sql`
	- `SIGEM-Parche-v2_3_0-v3_0_0-02-registro.sql`
	- `SIGEM-Parche-v2_3_0-v3_0_0-03-registro.sql`
* **sigemAdmin**
	- `SIGEM-Parche-v2_3_0-v3_0_0-01-sigemAdmin.sql`
* **tramitador**
	- `SIGEM-Parche-v2_3_0-v3_0_0-01-tramitador.sql`
	- `SIGEM-Parche-v2_3_0-v3_0_0-04-tramitador-auditoria.sql`
Será necesario descomprimir y ejecutar el parche (ver fichero leeme.txt):
	- `SIGEM-Parche-v2_3_0-v3_0_0-02-tramitador.zip`
* **sisnot**
	- `SIGEM-Parche-v2_3_0-v3_0_0-01-sisnot.sql`


### Actualización en el servidor de aplicaciones

#### Nuevos orígenes de datos

Se han creado nuevos esquemas de base de datos y esto implica la creación de los
siguientes orígenes de datos en el servidor de aplicaciones:

|Nombre|Esquema de base de datos|
|:----:|:----:|
|`jdbc/fwktd-sirDS_000`|`fwktd-sir_000`|
|`jdbc/fwktd-auditDS_000`|`fwktd-audit_000`|
|`jdbc/fwktd-dir3DS`|`fwktd-dir3`|

Para la configuración de estos orígenes de datos se recomienda la lectura del
documento de instalación [Manual de Instalación de AL SIGM](../instalación/Manual-de-instalación-AL-SIGM.html)

####  Despliegue de nuevas aplicaciones web

En esta versión de AL SIGM se han añadido las siguientes aplicaciones web:

* `fwktdsirWS.war`
* `SIGEM_ConsultaExpedienteBackOfficeWeb.war`
* `SIGEM_ConsultaRegistroTelematicoBackOfficeWeb.war`
* `SIGEM_GestionCSVWeb.war`
* `SIGEM_GestionCSVWS.war`
* `SIGEM_RegistroTelematicoDefaultTercerosConnectorWS.war`
* `SIGEM_ServicioIntermediacionClienteLigeroWS.war`
* `SIGEM_SignoWS.war`

Para el despliegue de estas aplicaciones web en el servidor de aplicaciones, se
recomienda la lectura del documento de instalación  [Manual de Instalación de AL SIGM](../instalación/Manual-de-instalación-AL-SIGM.html)

Se han actualizado las siguientes aplicaciones web:

* `portal.war`
* `SIGEM_AdministracionSesionesAdmWS.war`
* `SIGEM_AdministracionSesionesBackOfficeWS.war`
* `SIGEM_AdministracionUsuariosWeb.war`
* `SIGEM_AdministracionWeb.war`
* `SIGEM_AntivirusWS.war`
* `SIGEM_ArchivoWeb.war`
* `SIGEM_AutenticacionAdministracionWeb.war`
* `SIGEM_AutenticacionBackOfficeWeb.war`
* `SIGEM_AutenticacionUsuariosWS.war`
* `SIGEM_AutenticacionWeb.war`
* `SIGEM_BuscadorDocsWeb.war`
* `SIGEM_CalendarioWS.war`
* `SIGEM_CatalogoProcedimientosWeb.war`
* `SIGEM_CatalogoTramitesWeb.war`
* `SIGEM_CatalogoTramitesWS.war`
* `SIGEM_CatastroWeb.war`
* `SIGEM_CatastroWS.war`
* `SIGEM_CertificacionWeb.war`
* `SIGEM_CertificacionWS.war`
* `SIGEM_ConsultaRegistroTelematicoWeb.war`
* `SIGEM_ConsultaWeb.war`
* `SIGEM_ConsultaWS.war`
* `SIGEM_CriptoValidacionWS.war`
* `SIGEM_EntidadesWS.war`
* `SIGEM_EstructuraOrganizativaWS.war`
* `SIGEM_EstructuraWeb.war`
* `SIGEM_FirmaDigitalWS.war`
* `SIGEM_GeoLocalizacionWeb.war`
* `SIGEM_GeoLocalizacionWS.war`
* `SIGEM_MensajesCortosWS.war`
* `SIGEM_NotificacionUpdaterWeb.war`
* `SIGEM_NotificacionWeb.war`
* `SIGEM_NotificacionWS.war`
* `SIGEM_PagoElectronicoWeb.war`
* `SIGEM_PagoElectronicoWS.war`
* `SIGEM_PublicadorWeb.war`
* `SIGEM_PublicadorWS.war`
* `SIGEM_RdeWS.war`
* `SIGEM_RegistroPresencialAdminWeb.war`
* `SIGEM_RegistroPresencialWeb.war`
* `SIGEM_RegistroPresencialWS.war`
* `SIGEM_RegistroTelematicoWeb.war`
* `SIGEM_RegistroTelematicoWS.war`
* `SIGEM_RepositoriosDocumentalesWeb.war`
* `SIGEM_SchedulerWeb.war`
* `SIGEM_SessionUsuarioWS.war`
* `SIGEM_TercerosWS.war`
* `SIGEM_TramitacionWeb.war`
* `SIGEM_TramitacionWS.war`

Se detallan a continuación los orígenes de datos que se han añadido a las
aplicaciones web existentes:


|Aplicación Web|Orígenes de datos añadidos|
|:----|:----|
|SIGEM_AdministracionUsuariosWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_AdministracionWeb|`jdbc/tramitadorDS_000`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_ArchivoWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_AutenticacionAdministracionWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_AutenticacionBackOfficeWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_AutenticacionUsuariosWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_AutenticacionWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_CatalogoProcedimientosWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_CatalogoTramitesWS|`jdbc/registroDS_000`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_CatalogoTramitesWeb|`jdbc/registroDS_000`<br>`jdbc/fwktd-auditDS_000`<br>`jdbc/fwktd-dir3DS`|
|SIGEM_CertificacionWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_CertificacionWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_ConsultaWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_ConsultaWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_EstructuraWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_EstructuraOrganizativaWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_NotificacionUpdaterWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_NotificacionWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_NotificacionWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_PagoElectronicoWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_PagoElectronicoWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_PublicadorWeb|`jdbc/tercerosDS_000`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_PublicadorWS|`jdbc/tercerosDS_000`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_RdeWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_RegistroPresencialAdminWeb|`jdbc/fwktd-dir3DS`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_RegistroPresencialWS|`jdbc/fwktd-dir3DS`<br>`jdbc/fwktd-sirDS_000`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_RegistroPresencialWeb|`jdbc/fwktd-dir3DS`<br>`jdbc/fwktd-sirDS_000`<br>`jdbc/sigemAdmin`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_RegistroTelematicoWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_RegistroTelematicoWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_ConsultaRegistroTelematicoWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_RepositoriosDocumentalesWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_SessionUsuarioWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_TramitacionWS|`jdbc/fwktd-auditDS_000`|
|SIGEM_TramitacionWeb|`jdbc/fwktd-dir3DS`<br>`jdbc/fwktd-sirDS_000`<br>`jdbc/sigemAdmin`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_BuscadorDocsWeb|`jdbc/fwktd-auditDS_000`|
|SIGEM_SchedulerWeb|`jdbc/fwktd-dir3DS`<br>`jdbc/fwktd-auditDS_000`|
|SIGEM_CalendarioWS|`jdbc/fwktd-auditDS_000`|

### Actualización de ficheros de configuración

#### Nuevos ficheros de configuración

Se han añadido los siguientes ficheros de configuración:

|Directorio|Ficheros|
|:----:|:----|
|`fwktd-audit`|(todos)|
|`fwktd-csv`|(todos)|
|`fwktd-dir3`|(todos)|
|`fwktd-dm`|(todos)|
|`fwktd-sir`|(todos)|
|`fwktd-sir-ws`|(todos)|
|`fwktd-time`|(todos)|
|`SIGEM_AutenticacionAdministracionWeb`|`autenticacionAdministracionWebConfig.properties`|
|`SIGEM_AutenticacionBackOfficeWeb`|`autenticacionBackOfficeWebConfig.properties`|
|`SIGEM_Catastro`|(todos)|
|`SIGEM_Consolidacion`|`default/consolidacion.properties`|
|`SIGEM_ConsultaExpedienteBackOfficeWeb`|`skin/logos/logo.gif`<br>`log4j.properties`|
|`SIGEM_ConsultaRegistroTelematicoBackOfficeWeb`|(todos)|
|`SIGEM_CriptoValidacion`|`default/AFirmaConfiguration.properties`
|`SIGEM_ECOLogo`|(todos)|
|`SIGEM_FirmaDigital`|`default/AFirmaConfiguration.properties`<br>`default/firmaAFirmaConfig.properties`|
|`SIGEM_GeoLocalizacion`|(todos)|
|`SIGEM_GestionCSVWeb`|(todos)|
|`SIGEM_GestionCSVWS`|(todos)|
|`SIGEM_PagoElectronico`|`red.es/cripto.properties`<br>`red.es/SPTRedes.properties`<br>`PagoElectronico.properties`|
|`SIGEM_PagoElectronicoWeb`|`ReceiptCreator.properties`|
|`SIGEM_RegistroPresencial`|`database.properties`<br>`intercambioRegistral.properties`<br>`ISicres-Extension-Files-Configuration.xml`<br>`ISicres-scheduler.xml`<br>`quartz.properties`<br>`ISicres-Audit/ISicres-Audit-Configuration.xml`|
|`SIGEM_RegistroPresencialAdminWeb`|`ISicresAdmin-Core`<br>`ISicresAdmin-EstructuraCore`<br>`ISicresAdminWeb`<br>`log4j.properties`<br>`ISicresAdminCore/ISicresUserAdminLDAP.xml`<br>`ISicresAdminEstructuraCore/IeciTd_LdapConn_Cfg.xml`<br>`ISicresAdminWeb/log4j.properties`|
|`SIGEM_RegistroPresencialWS`|`Isicres/log4j.xml`|
|`SIGEM_RegistroTelematicoDefaultTercerosConnectorWS`|(todos)|
|`SIGEM_RegistroTelematicoTercerosConnector`|(todos)|
|`SIGEM_ServicioIntermediacionClienteLigeroWS`|(todos)|
|`SIGEM_SignoWS`|(todos)|
|`SIGEM_Tramitacion`|`alfresco-config.xml`|


Se recomienda la lectura del documento de instalación
 [Manual de Instalación de AL SIGM](../instalación/Manual-de-instalación-AL-SIGM.html)



#### Ficheros de configuración modificados

Los siguientes ficheros de configuración han sido modificados en esta versión:

|Directorio|Ficheros|
|:----:|:----|
|`SIGEM_ArchivoWeb`|(todos)|
|`SIGEM_AutenticacionBackOfficeWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_AutenticacionWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_CatalogoProcedimientosWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_CertificacionWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_ConsultaRegistroTelematicoWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_ConsultaWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_Core`|`database.properties`<br>`SIGEM_spring.properties`|
|`SIGEM_Notificacion`|`conectores.properties`<br>`SisnotBD.properties`|
|`SIGEM_NotificacionWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_PagoElectronicoWeb`|`skinDefecto/logos/logo.gif`|
|`SIGEM_Publicador`|`skinDefecto/logos/logo.gif`|
|`SIGEM_RegistroPresencial`|`hibernate.cfg.xml`<br>`ISicres-Configuration.xml`|
|`SIGEM_RegistroPresencialWeb`|`log4j.xml`<br>`skinDefecto/css/skin.css`<br>`skinDefecto/logos/logo.gif`|
|`SIGEM_RegistroTelematicoWeb`|`registroTelematicoWebConfig.properties`<br>`skin/logos/logo.gif`|
|`SIGEM_RepositoriosDocumentalesWeb`|`log4j.xml`|
|`SIGEM_Tramitacion`|`ispac.properties`<br>`jspbuilder.properties`<br>`firma/datosFirma.txt`<br>`skinDefecto/logos/logo.gif`|

### Ficheros susceptibles de cambio

Se necesita comprobar si los ficheros de configuración existentes en la aplicación
son diferentes a los que incluye la nueva versión tanto en contenido como en
estructura.

Ocurre lo mismo con los ficheros utilizados para la compulsa, las hojas de estilo y
las imágenes.

#### Ficheros de Archivo

Los directorios y ficheros a tener en cuenta son:

```
| archivo-cfg.xml
| log4j.xml
| OrganizationResources.properties
|
+---busquedas
| | busquedas-cfg.xml
| |
| +---bandeja
| |
| +---descripcion
| |    reemplazo_simple.xml
| |    reemplazo_simple_archivo.xml
| |
| +---elementos
| |    elementos.xml
| |    elementos_archivo.xml
| |
| +---eliminacion
| |    eliminacion_sel_udocs.xml
| |    eliminacion_sel_udocs_archivo.xml
| |
| +---fondos
| |    fondos_avanzada.xml
| |    fondos_avanzada_archivo.xml
| |    fondos_rapida.xml
| |    fondos_rapida_archivo.xml
| |    udocs_serie.xml
| |
| \---prestamos
|      prestamos.xml
|      prestamos_archivo.xml
|
+---gestores
|    ldapUserAttributes.xml
|    sistema_gestor_geograficos.xml
|
+---skinDefecto
| \---logos
|      logo.gif
|
+---transferencias
|    mapeo_fs_transferencia.xml
|    mapeo_udoc_transferencia.xml
|    map_fs_udoc.xml
|
\---xsl
     templateConsultaFicha.xsl
     templateEdicionFicha.xsl
     templateExportacionFicha.xsl
     transformLogTemplate.xsl
```

En el `archivo-cfg.xml` se especifican los conectores que utilizará la aplicación.

A continuación se detallan los valores especificados por defecto. Si alguno de los
valores ha cambiado, quiere decir que se está utilizando un conector diferente al
proporcionado en el entregable. Por lo que se deberá guardar la implementación de
estos conectores para poder volver a usarlos posteriormente.


* **Gestor de Organismos**. 
El conector de gestor de organismos se especifica en la etiqueta

``` xml 
<Sistemas_Gestores_Organismos>
  <Sistemas_Externos>
    <Sistema>
      <Clase>
``` 
	El valor especificado por defecto es: `se.instituciones.archivo.invesdoc.GestorOrganismoInvesdoc`

* **Gestor de Usuarios**.
El conector de gestión de usuarios se especifica en la etiqueta:

``` xml
<Sistemas_Gestores_Usuarios>
  <Sistema>
    <Clase>
```

	El valor especificado por defecto es: `se.autenticacion.idoc.InvesDocConnector`

* **Gestor de Catálogo**
El conector del sistema gestor de catálogo se especifica en la etiqueta:

``` xml
<Sistema_Gestor_Catalogo>
  <Clase>
```

	El valor especificado por defecto es: `se.procedimientos.GestorCatalogoSigem`

* **Tramitador**
El conector del sistema tramitador se especifica en la etiqueta:

``` xml
<Sistemas_Tramitadores>
  <Sistema>
    <Clase>
```

	El valor especificado por defecto es: `se.tramites.SistemaTramitadorSigem`

* **Gestor de Geográficos** 
El conector del sistema gestor de geográficos se especifica en la etiqueta:

``` xml
<Sistema_Gestor_Geograficos>
  <Clase>
```

	El valor especificado por defecto es: `se.geograficos.DefaultGestorGeograficosImpl`

* **Gestor de Terceros**
El conector del sistema gestor de geográficos se especifica en la etiqueta:

``` xml
<Sistema_Gestor_Terceros>
  <Clase>
```

	El valor especificado por defecto es: `se.terceros.sicres.SicresGestorTerceros`

* **Gestor de Repositorio Electrónico**
El conector del gestor de repositorio electrónico se especifica en la etiqueta:

```xml
<Sistema_Gestor_Repositorio_Electronico>
  <Clase>
```

	El valor especificado por defecto es: `se.repositorios.GestorRepositorioSigem`

#### Ficheros de Registro

Los directorios y ficheros a tener en cuenta son:

```
Internos a la aplicación:
  \css
  \images

Externalizados:
  datosCompulsa.txt
  fondo.gif
  ISicres-Configuration.xml
  hibernate.cfg.xml
  log4j.xml
  scheduler-config.xml
  ISicres-Audit\ISicres-Audit-Configuration.xml
```

En el caso de los ficheros externalizados, habría que comprobar si estos ficheros,
antes internos a la aplicación, son diferentes de los ahora distribuidos de forma
independiente.

En el archivo de configuración `ISicres-Configuration.xml`, que se encuentra en el
subdirectorio `SIGEM_RegistroPresencial` del directorio donde se descomprima la
configuración de SIGEM, se especifican los conectores que utilizará la aplicación.

En este fichero se ha añadido una nueva etiqueta, `<IdocImgEnableSaveAs>`,
que establece si se da o no la posibilidad de extraer los ficheros anexados a un
asiento de registro, para todos aquellos ficheros que utilicen el activeX IdocImgX
para su visualización.

Valores posibles:

* `true` (se activa la opción de extraer ficheros anexados)
* `false` (no se da la opción de extraer ficheros anexados)

Se añade una nueva variable `<AuthenticationPolicyType>` en la política de
autenticación que identifica el tipo de autenticación utilizada, si es ldap, invesdoc...

El conector de repositorio documental se especifica en la etiqueta `<Repository>`. El
valor especificado por defecto es: `es.ieci.tecdoc.isicres.document.manager.invesdoc.InvesDocRepositoryManager`

El conector del localizador documental de la compulsa se especifica en la etiqueta `<Compulsar>`. El valor especificado por defecto es: `es.ieci.tecdoc.isicres.compulsa.manager.invesicres.InvesicresCompulsaManager`

El conector de gestión de terceros se especifica en la etiqueta `<PersonValidationImplementation>`. El valor especificado por defecto es: `com.ieci.tecdoc.person.validation.core.PersonValidationImpl`

Si alguno de los valores especificados anteriormente no es el utilizado en el archivo
de configuración `ISicres-Configuration.xml` ya existente, quiere decir que se está
utilizando un conector diferente al proporcionado en el entregable. Por lo que se
deberá guardar la implementación de esos conectores para poder usarlos
posteriormente.


#### Ficheros de Gestión de Expedientes

* **Configuración API de Gestión de Expedientes**
Los ficheros de configuración del API de Gestión de Expedientes que hay que tener en cuenta a la hora de actualizar la versión son:

```
$SIGEM_CONF/Tramitacion/IDocStorageCfg.xml
$SIGEM_CONF/Tramitacion/ispacldap.properties
$SIGEM_CONF/Tramitacion/ispac.properties
$SIGEM_CONF/Tramitacion/jspbuilder.properties
$SIGEM_CONF/Tramitacion/firma/datosFirma.txt
$SIGEM_CONF/Tramitacion/firma/fondo.gif
``` 

* **Catálogo de Procedimientos**
La aplicación web que gestiona el Catálogo de Procedimientos se encuentra desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_CatalogoProcedimientosWeb`
	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
WEB-INF\classes
WEB-INF\lib\
```
	- Externalizados:
```
skinDefecto/logos/logo.jpg
log4j.xml
```
El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_CatalogoProcedimientosWeb`

* **Gestión de Expedientes**
La aplicación web que realiza la Gestión de Expedientes se encuentra desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_TramitacionWeb`
	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
forms\
ispac\digester\
ispac\skin1\
WEB-INF\classes
WEB-INF\lib\
```
	- Externalizados:
```
skinDefecto/logos/logo.jpg
log4j.xml
scheduler-config.xml
```
El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_TramitacionWeb`

* **Publicador**
La aplicación web que realiza la Publicación de información se encuentra desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_PublicadorWeb`

	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
WEB-INF\classes
WEB-INF\lib
```
	- Externalizados:
```
skinDefecto/logos/logo.jpg
log4j.xml
scheduler-config.xml
```
El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_PublicadorWeb`

* **Servicio Web de Publicación**
El servicio web de Publicación se encuentra desplegado en el directorio `$CATALINA_HOME/webapps/SIGEM_PublicadorWS`
	El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_PublicadorWS`
	El fichero de configuración externalizado que hay que tener en cuenta es `log4j.xml`

* **Servicio Web de Terceros**
El servicio web de Terceros se encuentra desplegado en el directorio `$CATALINA_HOME/webapps/SIGEM_TercerosWS`
	El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_TercerosWS`
	El fichero externalizado que hay que tener en cuenta es `log4j.xml`

* **Servicio Web de Tramitación** 
El servicio web de Tramitación se encuentra desplegado en el directorio `$CATALINA_HOME/webapps/SIGEM_TramitacionWS`
	El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_TramitacionWS`
	El fichero externalizado que hay que tener en cuenta es `log4j.xml`


#### Ficheros de Tramitación Electrónica

* **Configuración común de Pago Electrónico**
Los ficheros que hay que tener en cuenta son:
	- Externalizados:
```
PagoElectronico.properties
red.es/crypto.properties
red.es/SPTRedes.properties
```
El directorio de configuración externalizada es `$SIGEM_CONF/SIGEM_PagoElectronico`

* **Pago Electrónico**
La aplicación web de Pago Electrónico se encuentra desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_PagoElectronicoWeb`
	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
WEB-INF\classes
WEB-INF\lib\
```
	- Externalizados:
```
skinDefecto/logos/logo.jpg
log4j.properties
ReceiptCreator.properties
```
Los directorios utilizados por la configuración externalizada de este módulo son `$SIGEM_CONF/SIGEM_PagoElectronicoWeb`

* **Configuración común de notificaciones**
Este módulo ahora soporta multientidad
	Los ficheros que hay que tener en cuenta son:
	- Externalizados:
```
sisnotBD.properties
conectores.properties
```
En esta versión de SIGEM, se ha introducido la posibilidad de configuración multientidad. Por esta razón el directorio de configuración externalizada cambia.
Ahora tiene que ser `$SIGEM_CONF/SIGEM_Notificacion/<entidad>`
Por defecto: `SIGEM_Notificacion/default`

* **Gestión de notificaciones**
La aplicación web de Notificaciones se encuentra desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_NotificacionWeb`
	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
WEB-INF\classes
WEB-INF\lib\
```
	- Externalizados:
```
skinDefecto/logos/logo.jpg
log4j.properties
```
Los directorios utilizados por la configuración externalizada de este módulo son `$SIGEM_CONF/SIGEM_NotificacionWeb`

* **Tarea de actualización de notificaciones**
Desplegada en el directorio `$CATALINA_HOME/webapps/SIGEM_NotificacionUpdaterWeb`
	Los directorios y ficheros que hay que tener en cuenta son:
	- Internos a la aplicación web:
```
WEB-INF\classes
WEB-INF\lib\
```
	- Externalizados:
```
log4j.properties
updater.properties
```
El directorio de la configuración externalizada es `$SIGEM_CONF/SIGEM_NotificacionUpdaterWeb`

* **Ficheros de CriptoValidacion**
Los ficheros de configuración del API de CriptoValidacion que hay que tener en cuenta a la hora de actualizar la versión son `$SIGEM_CONF/SIGEM_CriptoValidacion/default/ValidacionConfiguration.properties`  y los respectivos, si existen, ficheros de configuración propios de cada entidad, en los directorios de `$SIGEM_CONF/SIGEM_CriptoValidacion/CODIGO_ENTIDAD/ValidacionConfiguration.properties`

En la configuración de la validación de certificados para @Firma se ha añadido una
nueva propiedad que permite establecer el modo de validación:

`VALIDA_AFIRMA_API_VALID_MODE=?`

en la que se puede establecer uno de los siguientes modos de validación de certificados soportados por @Firma:

	- `0`: para una validación simple, donde se validará la caducidad, integridad y confianza del certificado.
	- `1`: para una validación compleja, donde se validará la misma información del modo 0, más el estado de revocación y la validación de la cadena de confianza al completo.

En las versiones anteriores a la versión 2.3, el modo de validación estaba establecido siempre a cero.


### Migración de Códigos de Cotejos de la Gestión de Expedientes al módulo de Consulta de Documentos por CSV

Al incluirse en AL SIGM 3.0 la nueva funcionalidad de generación y consulta de
documentos por Código Seguro de Verificación (CSV), el código de cotejo de los
documentos generados en la Gestión de Expedientes se generará a partir de esta
versión mediante el nuevo módulo de Gestión de CSV. Dichos documentos se
podrán consultar, a partir de ese CSV generado, desde la aplicación web de
Consulta de Documentos por CSV.

Para que los documentos ya existentes en la Gestión de Expedientes, generados
con código de cotejo en versiones anteriores de SIGM, también puedan ser
consultados mediante la aplicación de Consulta de Documentos por CSV, se
proporciona un ejecutable para incluir dichos códigos de cotejo en el modelo de
datos del módulo de Gestión de CSV.

Para realizar esta migración de datos, se deberá ejecutar el archivo CMD para el
correspondiente gestor de bases de datos (DB2, Oracle, PostgreSQL o SQLServer)
del fichero ZIP de actualizaciones:

```
SIGEM-Migracion-v2_3_0-v3_0_0-tramitador-CSV.zip
```


## Actualización a la versión 3.0 desde versiones anteriores a la 2.3

Para realizar una actualización a AL SIGM 3.0 desde versiones anteriores a la
versión 2.3, se tendrá que actualizar previamente dicha implantación a la 2.3,
siguiendo las indicaciones de la documentación correspondiente a dicha versión, y
realizar a continuación la actualización a AL SIGM 3.0, tal y como se indica en el
actual documento.


La distribución de la versión 2.3 de AL SIGM se puede obtener de la página del
Ministerio de Industria Turismo y Comercio.

