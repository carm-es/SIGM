---
layout: default
title: Plataformas certificadas en SIGM
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Guías y ayudas/SGM_2012_10_Plataformas certificadas AL SIGM 3.0.pdf](pdfs/SGM_2012_10_Plataformas_certificadas_ALSIGM_3.0.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

Este documento tiene por objeto detallar y enumerar las versiones de productos sobre
las que se certifica SIGM 
*"SISTEMA INTEGRADO DE GESTIÓN DE EXPEDIENTES MODULAR"*.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|




## Requisitos de Instalación
Se describen a continuación los requisitos hardware (servidores y comunicaciones) y
software (básico y aplicaciones) para la implantación de SIGM con sus versiones sobre
los que se encuentra certificado.

### Requisitos hardware

#### Requisitos servidor
SIGM se puede montar en uno, dos o más servidores distribuidos. La configuración
más usual, sería la de dos servidores: una máquina con un Linux OpenSuse para el
servidor Web y de aplicaciones y otra máquina (Unix o Windows) para la base de datos
y el repositorio documental.

Requisitos de estas máquinas:

* Mínimo: Intel Pentium, 3 GHz, 2 Gb de RAM.
* Recomendado: 2 procesadores Pentium a 8 GHz, con 4 Gb de RAM.
* Almacenamiento: 6 Gb para la base de datos y 10 Gb para el repositorio de documentos.

#### Estaciones cliente
Mínimo: Pentium 4, 1Gb de RAM y 2 Gb de disco.

### Requisitos software

* Servidor de aplicaciones:
	- Los componentes de SIGM, se ejecutan en un **servidor de aplicaciones J2EE**.
	- **Otras tecnologías** en uso son:
		* **Hibernate** como framework ORM/persistencia.
		* **Struts** para la separación entre UI y negocio.
		* **Spring** para facilitar la arquitectura orientada a conectores/servicios de todo el sistema.
		* Los servicios Web SIGM son SOAP estándar, y por tanto, usan todas las tecnologías asociadas: WSDL, XML, UDDI, etc. Estos servicios se construirán usando el framework Axis 1.4.

* Estaciones cliente:
	- Necesita tener Java JRE 1.5 (update 22) o superior instalado en su máquina para poder firmar las solicitudes. Además tiene que tener el JRE configurado para tener acceso a Internet.
	- Activar los ActiveX en el Navegador
	- TWAIN para los dispositivos de captura (digitalización de imágenes)

## Versiones de plataformas en los que se encuentra certificado AL SIGM 3.0

En SIGM 3.0 se han realizado las certificaciones en las siguientes versiones de productos:

###  Estaciones cliente

* Sistema operativo:
	- Windows Vista/Microsoft Office 2007
	- Windows 7/Microsoft Office 2010
* Navegador:
	- Internet Explorer 8
	- Mozilla Firefox 3.6.x (aplicaciones del Ciudadano)
	- Chrome 8.0 (aplicaciones del Ciudadano)
	- Safari 5 (aplicaciones del Ciudadano)
	- Opera 11.0 (aplicaciones del Ciudadano)

### Sistema operativo del servidor

* Open Suse 11.3
* Windows Server 2008
* Suse Linux Enterprise 11
* CentOS 5.5


### Bases de datos

* DB2 9.7
* Postgre SQL 9.0
* SQL Server 2008
* Oracle 11g

### Servidores de aplicaciones

* WebSphere 6.0
* JBoss Enterprise Aplication Platform 5.1.2
* Tomcat 7.0


### Sistemas de autenticación de usuarios

* Sistema integrado propio
* LDAP versión protocolo 2
* Microsoft Active Directory


### Generador de documentos

* LibreOffice v.3.3

### Sistemas externos

* SISNOT 2.3
* LocalGIS 2.1
* Portafirmas MINHAP 2.1
* Plataforma de Intermediación – SCSP 3.2.2
* SIGNO 1.6

## Máquina virtual

* JDK 1.6
* Open Suse 11.3
* Apache Tomcat 7.0.16
* Postgres 9.0
* LibreOffice v.3.3

