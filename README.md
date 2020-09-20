# Titulo del Proyecto

Taller 3 - AREP 

Clientes y Servicios

 [![Deployed to AWS](https://github.com/JuanCe11/AREP-Taller-5/blob/master/BotonAWS.png)](http://ec2-100-26-178-129.compute-1.amazonaws.com:35000/)
 
 [![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://app.circleci.com/pipelines/github/JuanCe11/AREP-Taller-5)

 

## Comenzando 

Revise el archivo [Descripcion](https://github.com/JuanCe11/AREP-Taller-5/blob/master/Taller_5_AREP.pdf) para tener informacion basica del problema y la solucion propuesta.

Para tener una copia del repositorio, desde consola ejecute el siguiente comando.

```
git clone https://github.com/JuanCe11/AREP-Taller-5.git
```

### Pre-requisitos 

- Java 8 - [How install](https://www.java.com/es/download/help/windows_manual_download.xml)
- Git - [How install](https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalaci%C3%B3n-de-Git)
- Maven - [How install](https://maven.apache.org/install.html)


### Instalacion 

Para la correcta instalaci�n se debe primero clonar el repositorio como se indic� anteriormente, despu�s se ingresa al directorio del proyecto y para ejecutar la clase App (el servicio web de la clase fachada) se ejecutan los siguientes comandos en windows.

```
cd AREP-Taller-5/LoadBalancer
mvn package
java -cp target/classes;target/dependency/* edu.escuelaing.arep.taller5.App
```
Para sistemas linux usar: 

```
cd AREP-Taller-5/LoadBalancer
mvn package
java -cp target/classes:target/dependency/* edu.escuelaing.arep.taller5.App
```
Cuando se tenga el servicio corriendo se ingresa a la direccion http://localhost:1234/ para ver el el servicio en el navegador.

Puede generar la documentacion usando:

```
mvn javadoc:javadoc
```

## Construido con 

* [Java 8](https://www.java.com/es/about/whatis_java.jsp)
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Spark Java](http://sparkjava.com/) - Framework de aplicaci�n web.


## Wiki

Puedes encontrar mas de como utilizar este proyecto en nuestra [Wiki](https://github.com/JuanCe11/AREP-Taller-5/wiki)


## Autores 

* **Juan Sebastia Gomez Lopez** - *Trabajo Inicial* - [JuanCe11](https://github.com/JuanCe11)


## Licencia

Este proyecto est� bajo la Licencia GNU General Public License - mira el archivo [LICENSE.txt](LICENSE.txt) para detalles

