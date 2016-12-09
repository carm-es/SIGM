---
layout: default
title: Máquina virtual
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Guías y ayudas/SGM_2012_10_Máquina virtual.pdf](pdfs/SGM_2012_10_Máquina_virtual.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*




## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto del presente documento es describir la máquina virtual Vmware incluida en la
distribución de AL SIGM.


### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## Descripción
La máquina virtual que se entrega corre en un *openSuse 11.4* y tiene instaladas las
aplicaciones necesarias para el correcto funcionamiento de SIGEM:

* Postgres 9.0.3
* Apache Tomcat 7.0.16
* Vsftpd
* LibreOffice 3.3

### Usuarios del sistema

Se proporcionan dos usuarios en la máquina virtual:

|Usuario|Contraseña|
|:----:|:----:|
|sigem|sigem|
|root|sigem|


Es importante cambiar las contraseñas de los dos usuarios anteriores por contraseñas
que cumplan criterios de seguridad, combinando mayúsculas, minúsculas, números y
símbolos.

### Scripts de autoarranque

La máquina virtual tiene configurados unos scripts de autoarranque de las aplicaciones
citadas anteriormente bajo las rutas:

``` bash
/etc/init.d/postgresql
/etc/init.d/tomcat
/etc/init.d/vsftpd
/etc/init.d/libreoffice
```

Los scripts anteriores se arrancan por defecto en el inicio de la máquina virtual, pero
se puede parar/iniciar cada uno de ellos de forma individualizada. Para ello se podría
utilizar el comando stop para parar y start para iniciar. En el siguiente ejemplo
podemos ver como iniciar Tomcat:

``` bash
/etc/init.d/tomcat start
```

### Configuración de red

En ocasiones, cuando se mueve la maquina virtual de un directorio a otro o de una
maquina a otra, se requiere de una reconfiguración de la red.

Para ello usaremos la herramienta del sistema Yast:

``` bash
$ sudo su
# yast
Network Devices -> Network Settings
```

Si en la pantalla aparece listado algún dispositivo sin configurar procederemos a
configurarlo, pudiendo eliminar si aparece otro dispositivo ya configurado.

### Modo de Arranque de la maquina

La máquina puede ser configurada en dos modos, modo solo consola (nivel 3 que
consume menos recursos) y en modo escritorio (nivel 5).

Para pasar de un modo a otro utilizaremos la herramienta yast.

#### Modo Arranque Escritorio
Para pasar de modo consola a modo escritorio:

``` bash
$ sudo su
# yast
System -> System Services(Runlevel) -> Modo experto
``` 
Seleccionaremos 5: Modo multiusuario completo con red y entorno gráfico Reiniciamos el sistema.

``` bash
# reboot
```

Si el sistema rearranca todavía en modo consola pero en la consola muestra que ha
alcanzado el `run-level 5`. Ejecutaremos:

``` bash
$ sudo su
# init 3
# init 5
``` 

En este momento se inicializaría el modo escritorio.

#### Modo Arranque Consola
Pasar de modo escritorio a consola:

```
Inicio -> Maquina -> Yast -> Sistema -> Servicios de Sistema(Niveles de Ejecución) -> Modo experto
```

Seleccionaremos 3: Modo multiusuario con red.  Reiniciamos el sistema. En este momento se inicializaría el modo consola.


### URL de acceso a AL SIGM

Una vez arrancada la máquina virtual podemos acceder a la URL del portal de AL SIGM
de la siguiente manera:

```
http://<server_ip>:<server_port>/portal
```


* `server_ip` = dirección IP del servidor (se puede consultar mediante el comando `/sbin/ifconfig`)
* `server_port` = puerto del servidor, por defecto `8080`.


###  Usuarios de las aplicaciones

Se proporcionan varios usuarios creados para acceder a las aplicaciones de AL SIGM:

|Aplicación|Usuario|Contraseña|
|:----|:----|:----|
|Registro presencial|sigem||
|Gestión de Expedientes|tramitador<br>alcalde<br>secretario|sigem<br>alcalde<br>secretario|
|Archivo|archivo|sigem|
|Consulta total de Registros Telemático<br>Consulta total de Expedientes|consulta|sigem|

