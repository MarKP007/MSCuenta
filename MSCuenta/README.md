# LLI - Backend

## Table of Contents
- [Project Structure](#introduction)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running the application](#running-the-application)
- [Testing](#testing)
- [Back Development](#back-development)

## Project Structure

Esta solución consta de dos proyectos, cliente y cuentas, los cuales siguen un enfoque estructurado para organizar el código base, haciéndolo escalable y mantenible. A continuación, se ofrece una descripción general de los directorios principales y su contenido previsto:

- src/: Contiene todo el código fuente.
  - main/: Este directorio contiene los archivos de configuración y recursos de la aplicación.
    - controller/: Este paquete contiene las clases de controladores, que manejan las solicitudes HTTP y definen los endpoints de la API.
	- model/: Este paquete contiene las clases de modelo, que representan las entidades del dominio.
	- repository/: Este paquete contiene las interfaces de repositorios, que extienden JpaRepository para realizar operaciones CRUD en la base de datos.
	- service/: Este paquete contiene las clases de servicios, que implementan la lógica de negocio.
	- utils/: funciones de utilidad y ayudantes que proporcionan funcionalidades de uso común en toda la aplicación.
	- Application.java: Clase principal que inicia la aplicación Spring Boot.
  - tests/: Contiene todos los archivos de prueba de la aplicación.
    - unidad/: Pruebas unitarias para componentes individuales, como servicios y utilidades.
    - integración/: Pruebas de integración que prueban las interacciones entre diferentes capas y componentes, como rutas y controladores.
  - pom.xml: Archivo de configuración de Maven, que define las dependencias del proyecto y cómo se debe construir la aplicación.
 
## Getting Started

### Prerequisites

- GIT
- docker
- Postman

### Installation

1. Clone el repositorio:

git clone git@github.com:MarKP007/MSCuenta.git

1.1 En caso de requerir passphrase coloque "a"

2. Navegar al directorio del proyecto:

cd MSCuenta/MSCuenta

3. Contruir dockerfile para Cuentas:

docker build -t cuentas .

4. Clone el repositorio:

git clone git@github.com:MarKP007/MSCliente.git

4.1 En caso de requerir passphrase coloque "a"

5. Navegar al directorio del proyecto:

cd MSCliente/MSCliente

6. Contruir dockerfile para Cliente:

docker build -t cliente .

7. Contruir docker compose:

docker compose up -d

8. Importar a Postman la colección:

My collection.postman_collection.json

## Configuration

- Ya especificado en el archivo docker compose.

## Running the Application

- Ya especificado en el archivo docker compose.

## Testing

- Ya especificado en el archivo docker compose.

## Back Development

Necesitas instalar:
- GIT
- docker
- Postman

1. En docker-compose.yml reemplaza los valores de env variables con los requeridos por tu ambiente o dejalos por default.
2. Run docker compose up -d