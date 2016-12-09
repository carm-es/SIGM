---
layout: default
title: Especificación ActiveX y Applets en SIGM
---


> *Este documento se ha migrado desde el original:
[Documentación/5 Manuales y documentación técnica/Documentación técnica/Guías y ayudas/SGM_2012_10_Especificacion ActiveX y Applets en SIGM.pdf](pdfs/SGM_2012_10_Especificacion_ActiveX_y_Applets_en_SIGM.pdf) en [https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar](https://github.com/e-admin/alsigm/releases/download/Documentacion/Documentacion_ALSIGM_3.0.rar)*


## Introducción


### Visión general del sistema

AL SIGM es la plataforma de Tramitación Electrónica del MINETUR, solución integral
para la tramitación electrónica de los procedimientos administrativos, que fomenta la
interoperabilidad entre administraciones mediante su adaptación a estándares de
comunicación así como la reutilización de recursos e información pública.

### Finalidad del documento

El objeto de este documento es enumerar los ActiveX y Applets que se utilizan dentro
de la plataforma SIGM y especificar el software que incluye cada uno de ellos y que
debe existir en cada puesto cliente para su correcto funcionamiento.



### Definiciones y Abreviaturas

A continuación se expone una tabla con los diferentes acrónimos y abreviaturas
utilizados a lo largo del documento, con su correspondiente definición.


|Acrónimo / Abreviatura | Definición |
|:----:|:----|
|MINETUR|Ministerio de Industria, Energía y Turismo|
|IECISA|Informática El Corte Inglés S.A.|
|SIGM|Sistema Integrado de Gestión de Expedientes Modular|
|AL|Administración Local|



## ActiveX

### Registro Presencial

Los ActiveX que incluye el módulo del Registro Presencial son:

#### Visualizador de documentos imagen

`ides.cab` que incluye:

* `iDocImgX.dll`
* `gear32pd.dll`

La `iDocImgX.dll` hay que registrarla en el puesto cliente donde vaya a usar. Para
ello hay que ejecutar desde el menú de Windows: *"Inicio->Ejecutar"*, la sentencia:

```
regsvr32  ruta donde se encuentra la dll\iDocImgX.dll

```

Por ejemplo, si este software está en `c:\activex`, hay que ejecutar la sentencia

```
regsvr32 c:\activex\iDocImgx.dll
```


#### Componente de impresión de documentos

El componente `isvalprn.cab` que incluye:

* `isValPrnASP.dll`
* `MFC42.dll`
* `MSVCRT.dll`
* `Arial_invertida.ttf`

La dll `isValPrnASP.dll` hay que registrarla en el puesto cliente donde vaya a usar.
Para ello hay que ejecutar desde el menú de Windows: *"Inicio->Ejecutar"*, la sentencia:

```
regsvr32 ruta donde se encuentra la dll\ isValPrnASP.dll.
```

Por ejemplo, si este software está en `c:\activex`, hay que ejecutar la sentencia:

``` 
regsvr32 c:\activex\ isValPrnASP.dll
``` 


## Applet

###  Registro Presencial

#### Componente de firma de documentos

Software de `@firma` que incluye:

```
afirmaBootLoader.jar
afirma_5_java_5.jar
afirma_5_java_5.jar.pack.gz
COMPLETA_afirma.jnlp
COMPLETA_j5_afirma5_core__V3.2.jar
COMPLETA_j5_afirma5_core__V3.2.jar.pack.gz
COMPLETA_j6_afirma5_core__V3.2.jar
COMPLETA_j6_afirma5_core__V3.2.jar.pack.gz
LINUX_nss_amd64_JRE64.txt
LINUX_nss_amd64_JRE64.zip
LINUX_nss_i386_JRE32.txt
LINUX_nss_i386_JRE32.zip
LITE_afirma.jnlp
LITE_j5_afirma5_core__V3.2.jar
LITE_j5_afirma5_core__V3.2.jar.pack.gz
LITE_j6_afirma5_core__V3.2.jar
LITE_j6_afirma5_core__V3.2.jar.pack.gz
MACOSX_nss_i386_JRE32.txt
MACOSX_nss_i386_JRE32.zip
MACOSX_nss_x86_64_JRE64.txt
MACOSX_nss_x86_64_JRE64.zip
MEDIA_afirma.jnlp
MEDIA_j5_afirma5_core__V3.2.jar
MEDIA_j5_afirma5_core__V3.2.jar.pack.gz
MEDIA_j6_afirma5_core__V3.2.jar
MEDIA_j6_afirma5_core__V3.2.jar.pack.gz
mscapi_amd64_JRE64.zip
mscapi_x86_JRE32.zip
sunmscapi.jar
sunpkcs11.jar
version.properties
WINDOWS_nss_amd64_JRE64.txt
WINDOWS_nss_amd64_JRE64.zip
WINDOWS_nss_x86_JRE32.txt
WINDOWS_nss_x86_JRE32.zip
WINDOWS_pkcs11lib_amd64_JRE64.zip
xalan.zip
xalanjar.zip
xmlsec.zip
xmlsecjar.zip
``` 

#### Componente de digitalización

La funcionalidad de digitalización de documentos está compuesta por el applet `fwktd-scan-applet.jar`


#### Componente de subida de ficheros al servidor

Funcionalidad de subida de ficheros al servidor está compuesta por dos applets:

* `fwktd-fileSystem-applet.jar`: selección de ficheros del sistema de ficheros
* `fwktd-sendFiles-applet.jar`: envío de ficheros al servidor


###  Gestión de expedientes

Los Applets que incluye el módulo de Gestión de Expedientes son:

#### Generación de documentos a partir de una plantilla

`Applauncherapplet` que incluye `applauncherapplet.jar`

#### Componente para anexar ficheros a partir de un directorio

`ispacdocapplet` que incluye:

* `commons-codec-1.3.jar`
* `commons-httpclient-3.0.jar`
* `commons-logging-1.0.4.jar`
* `ispacdocapplet.jar`

#### Componente de digitalización

`Idocscanapplet` que incluye:

* `commons-codec-1.3.jar`
* `commons-httpclient-3.0.jar`
* `commons-logging-1.0.4.jar`
* `iDocScan.cab` (ver activeX)
* `idocscanapplet.jar`
* `jawin-1.0.18.jar`
* `win32libraries.zip`

#### Componente de firma de documentos @firma

Los mismos componentes ya especificados en el apartado del Registro Presencial.

