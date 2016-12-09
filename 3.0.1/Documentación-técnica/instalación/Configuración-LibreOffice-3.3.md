---
layout: default
title: Instalación LibreOffice 3.3
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Instalación y Configuración/SGM_2012_10_Configuración LibreOffice 3.3.pdf](pdfs/SGM_2012_10_Configuración_LibreOffice_3.3.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto de este documento es detallar la configuración de LibreOffice v3.3 en el
servidor de aplicaciones de SIGM, para asegurar un óptimo funcionamiento en el
tratamiento documental de la Gestión de Expedientes.

La configuración afecta a tres componentes:

* Repositorio de combinación (temporales y plantillas)
* Servidor de combinación (LibreOffice)
* Servidor de aplicaciones (framework ISPAC)



### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Servicio de combinación

Para disponer del servicio de combinación es necesario:

* Instalar LibreOffice v3.3. Se puede instalar desde los repositorios de binarios de la distribución que se esté utilizando, ó se puede instalar de los rpms de la página web de LibreOffice.
* Iniciar el servicio de escucha en el puerto `<<puerto>>` de la siguiente forma:

``` bash
soffice -headless -nologo -nodefault -accept=socket,host=<<servidor>>,port=<<puerto>>;urp;
```

El script de arranque para sistema operativo con entorno gráfico llamado `start_open_office.sh` debe contener

``` bash
#!/bin/sh
DISPLAY=:0.0
soffice -headless -nologo -nodefault \
        '- accept=socket,host=localhost,port=8100;urp;' &
```

En script de arranque para sistema operativo sin entorno gráfico llamado `start_open_office_nox.sh` debe contener

``` bash
#!/bin/sh
export DISPLAY=:0.0
Xvfb :0.0 2>/dev/null &
xhost + 2>/dev/null &
soffice -headless -nologo -nodefault 'accept=socket,host=localhost,port=8100;urp;'
```

Estos scripts no se entregan en el CD de distribución.

**Nota importante**: para iniciar más de un servicio en la misma máquina, es necesario
ejecutar los servicios de escucha con distintos usuarios. En caso contrario simplemente
se obtendría una única instancia de LibreOffice recibiendo peticiones en dos puertos
distintos.


## Repositorio de combinación

El repositorio requiere se creen dos directorios vacíos, que deben ser accesibles
mediante el sistema de ficheros, tanto para el servidor de combinación (LibreOffice),
como para el servidor de aplicaciones.

Se configura mediante las siguientes propiedades del framework de ISPAC en el fichero
`ispac.properties` que se encuentra en el zip de ficheros de configuración en el directorio
Conf del CD de distribución.

``` ini
# REPOSITORIO COMBINACIÓN

# Directorio temporal
TEMPORARY_PATH = /home/ sigem/SIGEM/temp/temporary

# Caché de plantillas en disco.
TEMPLATE_PATH = /home/sigem/SIGEM/temp/templates
```


## Servidor aplicaciones

Para finalizar la configuración, es necesario informar al framework dónde se encuentra
disponible el servicio de combinación.

Para ello se utilizan las siguientes propiedades del framework ISPAC (ispac.properties
en el zip de ficheros de configuración en el directorio Conf del CD de distribución).

``` ini
# Cadena conexión para OpenOffice.
OPEN_OFFICE_CONNECT = uno:socket,host=localhost,port=8100;urp;StarOffice.NamingService

# Instancias adicionales de OpenOffice
#OPEN_OFFICE_ADDITIONAL_INSTANCES = 1

# Añadir tantas entradas extras como servicios de combinación adicionales se deseen.
#OPEN_OFFICE_CONNECT_0 = uno:socket,host=localhost,port=8101;urp;StarOffice.NamingService
```

## Otras configuraciones

Se pueden distinguir varios tipos de configuración según las necesidades de instalación
y las arquitecturas de despliegue de SIGM. Según la disposición (local o remota) de los
componentes instalados en relación con el servidor de aplicaciones se obtiene todo el
abanico de configuraciones posibles.

* Local: el componente se encuentra en la máquina donde esté instalado el servidor de aplicaciones.
* Remota: el componente se encuentra instalado en una máquina distinta.

### Servicio combinación local

Se trata de la configuración más sencilla. Los tres componentes se encuentran en local
y por tanto las consideraciones a realizar son mínimas: el proceso de instalación básico
descrito anteriormente bastaría. Tiene como principal desventaja el compartir la CPU
por parte del servicio de combinación y el servidor de aplicaciones.

### Servicio combinación remoto

En esta configuración, el servicio de combinación se encuentra instalado en otra
máquina. Para su correcto funcionamiento son necesarios realizar dos cambios en la
instalación 

**Servidor de aplicaciones (framework ISPAC)** cambiar adecuadamente el nombre de servidor.

```ini
# Cadena conexión para OpenOffice.
OPEN_OFFICE_CONNECT = uno:socket,host=<<servidor>>,port=8100;urp;StarOffice.NamingService

# Instancias adicionales de OpenOffice
#OPEN_OFFICE_ADDITIONAL_INSTANCES = 1

# Añadir tantas entradas extras como servicios de combinación adicionales se deseen.
#OPEN_OFFICE_CONNECT_0 = uno:socket,host=localhost,port=8101;urp;StarOffice.NamingService
```

**Repositorio de combinación**. En este caso, los directorios deben ser accesibles de manera local con la misma ruta tanto al servidor de aplicaciones como al servicio de combinación. La forma difiere según el entorno hardware:

1. Entorno Ms Windows: utilizar unidades de red compartidas entre ambos. Ejemplo:

```ini
# REPOSITORIO COMBINACIÓN
#
# Unidad de red mapeada a unidad local V: en ambas máquinas: servidor de aplicaciones y
# servidor de combinación (OpenOffice)

# Directorio temporal
TEMPORARY_PATH = V:/ispac_files/temporary

# Caché de plantillas en disco.
TEMPLATE_PATH = V:/ispac_files/templates
```

2. Entorno Unix: mediante NFS, montar en una misma ruta el repositorio

```ini
# REPOSITORIO COMBINACIÓN
#
# Sistema de ficheros NFS montado sobre el path /data/ispac_files en ambas máquinas:
# servidor de aplicaciones y servidor de combinación (OpenOffice)

# Directorio temporal
TEMPORARY_PATH = /data/ispac_files/temporary

#Caché de plantillas en disco.
TEMPLATE_PATH = /data/ispac_files/templates
```

En ambos casos, es necesario asegurar que los usuarios bajo los cuales se ejecutan
ambos servicios, tengan el permiso de acceso adecuado a los ficheros.

### Varios servicios de combinación remotos

Para disponer de más de un servicio de combinación, estos se deben configurar de
igual forma que en el punto anterior. Además es necesario anotar en el fichero de
propiedades del framework ISPAC el nuevo servicio de combinación:

Ejemplo de configuración para dos servicios de combinación, instalados en las
máquinas `<<servidor1>>` y `<<servidor2>>` respectivamente:

``` ini 
# Cadena conexión para OpenOffice.
OPEN_OFFICE_CONNECT = uno:socket,host=<<servidor1>>,port=8100;urp;StarOffice.NamingService

# Se utilizará un servicio de combinación adicional.
OPEN_OFFICE_ADDITIONAL_INSTANCES = 1

# Segundo servicio de combinación
OPEN_OFFICE_CONNECT_0=uno:socket,host=<<servidor2>>,port=8100;urp;StarOffice.NamingService
```

