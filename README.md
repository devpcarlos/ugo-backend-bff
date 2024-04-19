# Ugo Backend BFF (Backend-for-Frontend)

Este repositorio contiene el código backend de la aplicación, desarrollado utilizando Java, Spring y PostgreSQL, entre otras tecnologías.
Aquí podrás encontrar la evolución actualizada del proyecto.

# Comandos útiles

## Spring

Para activar un **_profile_** específico de Spring:

    java -jar -Dspring.profiles.active=<PROFILE-NAME> <GENERATED-JAR>.jar

Por ejemplo:

    java -jar -Dspring.profiles.active=dev turistas-0.0.1-SNAPSHOT.jar

## Docker

Para construir una imagen de Docker, el comando es el siguiente:

    docker build -t <docker-hub-username>/ugo-backend-bff .

No es necesario disponer de una cuenta de Docker Hub, excepto si se quiere hacer push al Hub.

Para hacer push, ejecutar el comando:

    docker push <docker-hub-username>/ugo-backend-bff:latest

Correr la imagen creada en un contenedor de Docker requiere la inyección de variables de entorno.
Lo más práctico es disponer de un fichero con estas variables y pasarlas en tiempo de ejecución (runtime):

    docker run --env-file <env_file_name> <image-name>

Por ejemplo:

    docker run --env-file .env -p 8080:8080 <docker-hub-username>/ugo-backend-bff
    docker run --env-file .env -d -p 8080:8080 <docker-hub-username>/ugo-backend-bff

El último comando ejecuta el contenedor en modo "detach".

Para corroborar las variables de entorno que se setearon dentro del contenedor, ejecutar:

    docker exec <container-id> env

**IMPORTANTE: Recordar que nunca se debe versionar el archivo con las variables de entorno.**

# Equipo
- Leonel Ojeda
- Carlos Barrera
- Ignacio Coletta
- Emanuel Magallanes

# Contacto
Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto con nosotros.  **https://www.linkedin.com/company/ugo-webapp/**

¡Gracias por tu interés en nuestro proyecto! 🚀