---
layout: default
title: ALSIGM 3.0.1 - Inicio
---




[**A**dministración **L**ocal **S**istema **I**ntegrado de **G**estión **M**unicipal](https://administracionelectronica.gob.es/ctt/sigem) es una suite informática 
que implementa la gestión del procedimiento administrativo de un expediente.

Ofrece a las administraciones una solución integral para la
tramitación electrónica de los procedimientos administrativos:

* facilita a las entidades el cumplimiento de la ley 11/2007  de  Acceso  Electrónico  de  los  Ciudadanos  a  los  Servicios  Públicos
* favorece la reutilización de recursos e información pública
* fomenta la interoperabilidad entre administraciones
* soporta la interconexión con otras plataformas de la administración pública: Dir3, SIR, notificaciones SNTS-SISNOT, etc
* permite la personalización para adaptarla a necesidades concretas

Al ciudadano ofrece la posibilidad de relacionarse con las administraciones por medios electrónicos, con
el consiguiente ahorro de tiempo y dinero.


## Empezando 
Recomendamos la lectura de los siguientes documentos con el fin de planificar y ejecutar una implementación de ALSIGM.


* [Plataformas certificadas](Documentación-técnica/soporte/Plataformas-certificadas-AL-SIGM-3.0.html)
* [Probar AL SIGM en una máquina virtual](Documentación-técnica/soporte/Máquina-virtual.html)
* [Instalación de AL SIGM](Documentación-técnica/instalación/Manual-de-instalación-AL-SIGM.html)


Una vez decida implantar ALSIGM en su organización, le recomendamos la lectura de la [documentación técnica de la versión](Documentación-técnica/index.html).

## Demo
Puede probar ALSIGM desde una máquina virtual ya configurada en [https://github.com/e-admin/alsigm/releases/tag/v3.0](https://github.com/e-admin/alsigm/releases/tag/v3.0)



## Componentes
AL SIGM incluye los siguientes componentes

1. **Tramitador electrónico de expedientes**: permite el almacenamiento en formato electrónico  de  todos  los  documentos  asociados  a  un  expediente,  así  como  la definición  de  trámites  y  flujos  entre  ellos,  de  forma  que  se  puedan  asignar automáticamente   los   diferentes   trámites   a   distintas   personas   o   unidades administrativas.  
	Se  proporciona  un  procedimiento  genérico,  basado  en  los trámites  indicados  en  la  ley  30/1992,  a  partir  del  cual  se  pueden  crear  y modificar  los  procedimientos  que  se  deseen.  Se  proporciona  una  interfaz gráfica que facilita la creación de procedimientos. 
	Además la aplicación incluye una serie de procedimientos habituales ya creados y configurados: 
	- Acometida de agua 
	- Cambio de Titular de Licencia de Apertura 
	- Certificado Urbanístico 
	- Concesión de subvención 
	- Contrato negociado 
	- Expediente sancionador (genérico) 
	- Licencia de apertura de Actividad Clasificada / No Clasificada 
	- Licencia de Vado 
	- Obras menores 
	- Reclamación de Tributos 
	- Reclamaciones, quejas y sugerencias 
	- Tarjeta de estacionamiento para minusválidos 
	- Cédula o informe de Habitabilidad 
	- Cita previa con técnicos municipales 
	- Compra menor 
	- Proceso selectivo (convocatorias de empleo) 
	- Bonificaciones (reducciones de impuestos) 
	- Ocupación de vía pública 

2. **Registro  de  Entrada  y  Salida**:  permite  la  gestión  del  registro,  tanto  presencial  como  electrónico.  En  el  registro  electrónico  se  incluye  el  catálogo  de  trámites que pueden ser iniciados directamente por el ciudadano de forma electrónica. 
Se pueden configurar diferentes oficinas de registro, libros, usuarios, etc. 

3. **Archivo** :  almacena  temporal  y/o  definitivamente  la  documentación  de  los expedientes,  con  posibilidad  de  cambios  de  textos  masivos  de  acuerdo  a  las  normas de descripción y categorización definidas para los archivos. 

4. **Aplicaciones  para  el  ciudadano**:  se  incluyen  una  serie  de  servicios  que  pueden  ser  utilizados  por  el  ciudadano,  bien  de  forma  aislada  o  en  el  contexto  de  un  procedimiento: 
	- Consulta de expedientes 
	- Consulta de registros 
	- Pago electrónico de tributos y tasas (a través de la plataforma de Red.es) 
	- Notificación electrónica (mediante la plataforma SNTS-SISNOT) 
	- Recepción de avisos por mensajes SMS a móviles 
	- Certificaciones 

Los   ciudadanos   podrán   identificarse   y   firmar   utilizando   certificados   electrónicos 
admitidos por la plataforma @firma, incluyendo el DNI electrónico. 


