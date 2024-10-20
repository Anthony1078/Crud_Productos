# Crud_Productos

Aplicación monolítica desarrollada para el curso de DevOps en MitoCode. 
Permite a los usuarios gestionar productos mediante operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una interfaz web. 



## Tecnologías

* Spring Boot: Para la lógica del servidor y configuración del backend.
* Thymeleaf: Motor de plantillas para la generación de vistas HTML.
* Bootstrap: Framework CSS para el diseño responsive y estilizado de la aplicación.
* Serenity y Cucumber: Para pruebas BDD y generación de reportes detallados.



## Características

Lista de productos.
Formularios para agregar y editar productos.
Interfaz responsiva adecuada para diversos dispositivos.



## Configuración y ejecución
#### Requisitos previos
JDK 17 o superior
Maven configurado en el PATH



## Instalación
Clonar el repositorio y navegar al directorio del proyecto:

> git clone https://github.com/Anthony1078/Crud_Productos.git*

> cd Crud_Productos




## Ejecutar la aplicación
Utilizar Maven para iniciar la aplicación:

> mvn spring-boot:run

La aplicación estará disponible en http://localhost:8585/productos


## Pruebas
Ejecutar pruebas con Serenity y Cucumber:

> mvn clean verify

Los reportes de Serenity se pueden encontrar en target/site/serenity


