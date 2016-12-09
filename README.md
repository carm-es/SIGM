
# Documentación de ALSIGM


## Requisitos
Instala y construye las dependencias del paquete `jekyll`siguiendo la guía: [https://help.github.com/articles/using-jekyll-with-pages/](https://help.github.com/articles/using-jekyll-with-pages/).


## Realizando cambios

1. `git clone https://github.com/ctt-gob-es/SIGM.git `
2. `git checkout gh-pages`
3. Realiza los cambios necesarios
4. `git push --set-upstream origin gh-pages`

## Visualización en local

Para ver la documentación en nuestro equipo, mientras la modificamos:

```bash
cd <directorio_checkout_gh-pages>
jekyll serve
```
Accede desde tu navegador a [http://localhost:4000](http://localhost:4000) para ver la web de documentación.

La primera vez, tendrás que instalar las dependencias de jekyll

```bash
cd <directorio_checkout_gh-pages>
bundle install
```

## Estructura del sitio
La documentación está organizada siguiendo los siguientes bloques:

- La documentación de cada versión de AL SIGM estará organizada en un directorio diferente (*ejemplo: `3.0.1`*)
- Las revisiones que se realicen sobre versiones, también se organizarán en directorios diferentes (*ejemplo: `3.0.1-rev-carm`*), con la intención de identificar las aportaciones sobre la documentación oficial, mientras no se acelere el proceso de publicación de nuevas versiones.
- Cada versión (directorio) tendrá sus propio índice, en un fichero llamado `sidebar.md`. Este fichero será incluído automáticamente vía JavaScript, desde el `layout` por defecto. *Es la única forma que hemos visto viable con Jekyll y Markdown*
- El directorio `current` apunta a la versión de la documentación en desarrollo.
- El fichero raíz `index.html` apuntará siempre `current/index.html`.

