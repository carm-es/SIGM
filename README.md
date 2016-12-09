
#mvn-repo

Este es el repositorio de dependencias de terceros que hemos recopilado para poder compilar SIGM. Estas dependencias dejaron de estar disponibles hace tiempo en el repositorio `maven-central`, o bien son demasiado exclusivas y sólo se encontraban en repositorios privados.

Para poder usar este respositorio en el proceso de compilación de SIGM, **edite su fichero `$M2_HOME/conf/settings.xml`**, y aplique los siguientes cambios:

1. En la sección `<profiles>` añada el siguiente bloque:

``` xml
<profile>
  <id>sigm</id>
  <repositories>
    <repository>
      <id>sigm_mvn-repo</id>
      <name>Repositorio de dependencias</name>
      <url>https://raw.githubusercontent.com/carm-es/SIGM/mvn-repo/thirdparty</url>
    </repository>
  </repositories>
</profile>
``` 

2. Actívelo en la sección `activeProfiles`, por ejemplo:

``` xml
<activeProfiles>
  <activeProfile>sigm</activeProfile>
</activeProfiles>
```


## Gestión de depencencias

Para añadir nuevas dependencias, el proceso será el siguiente:

1. Clonar el proyecto y cambiar a la rama

``` bash
mkdir /tmp/checkout
cd    /tmp/checkout

git clone https://github.com/carm-es/SIGM.git
cd SIGM
git checkout mvn-repo
```

2. Identificar la dependencia y descargar al disco local. Por ejemplo 

	* `GroupId`: `org.pruebas`
	* `ArtifactId`: `demo`
	* `Versión`: `1.4.5`
	* Fichero: `/tmp/test.jar`

3. Instalar en el repositorio, ejecutando

``` bash
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
    -DgroupId=org.pruebas -DartifactId=demo -Dversion=1.4.5 \
    -Dpackaging=jar -Dfile=/tmp/test.jar \
    -DlocalRepositoryPath=/tmp/checkout/SIGM/thirdparty \
    -DcreateChecksum=true -DgeneratePom=true
``` 

4. Añadir a git

``` bash
git add thirdparty/org/pruebas
git commit -m "Añado dependencia blah,blah..."

git push origin mvn-repo
``` 

