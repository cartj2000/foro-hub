
<p align="center"> <strong>Desafío Foro Hub</strong>
<br> Proyecto usando: Java, Maven, Spring Boot Dev Tools, Lombok, Spring MVC Web, Jakarta, MySQL, Spring Data JPA, Flyway, Spring Security, Spring Security Test, auth0 tokens JWT, Spring Doc Swagger UI y Arquitectura limpia. </p>

<h1 align="center">Hola , mi nombre es Carlos <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="35"></h1>
<picture> <img align="right" src="https://github.com/7oSkaaa/7oSkaaa/blob/main/Images/Right_Side.gif?raw=true" width = 250px></picture>

## :triangular_ruler:
🛠 Funcionalidades del proyecto

✔ Responsabilidad REST STATELESSNESS (el servidor no necesita saber en que estado se encuentra el cliente y viceversa)

✔ El cliente realiza una solicitud al servidor para enviar o modificar datos a través de un método HTTP

✔ Uso del método GET (solicitar) para listar tópicos

✔ Uso del método POST (enviar) para registrar tópicos

✔ Uso del método PUT (actualizar) para actualizar tópicos

✔ Uso del método DELETE (eliminar) para eliminar tópicos

✔ Resultados testeados y validados

✔ Autenticación / Autorización para restringir el acceso a la información

✔ Migración a través de Flyway

## :pencil2:
📌 Operación del programa

Antes de empezar: entrar a MySQL y ejecutar:

create database forohub;

insert into usuarios set login ='jessica.fernandez@alura.com',contrasena = '123456';

update usuarios set nombre = 'jessica fernandez' where login = 'jessica.fernandez@alura.com';

update usuarios set contrasena='$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.' where login='jessica.fernandez@alura.com';

Introducir la información usando insomnia a través de los siguientes end points:

Registro de curso:	POST http://localhost.8080/cursos

Eliminar curso:	DELETE http://localhost.8080/cursos/id

Actualizar curso:	PUT http://localhost.8080/cursos/id

Lista de cursos:	GET http://localhost.8080/cursos

Detallar curso:	GET http://localhost.8080/cursos/id

Registro de usuario:	POST http://localhost.8080/usuarios

Eliminar usuario:	DELETE http://localhost.8080/usuarios/id

Actualizar usuario:	PUT http://localhost.8080/usuarios/id

Lista de usuarios:	GET http://localhost.8080/usuarios

Detallar usuario:	GET http://localhost.8080/usuarios/id

Registro de topico:	POST http://localhost.8080/topicos

Eliminar topico:	DELETE http://localhost.8080/topicos/id

Actualizar topico:	PUT http://localhost.8080/topicos/id

Lista de topicos:	GET http://localhost.8080/topicos

Detallar topico:	GET http://localhost.8080/topicos/id

Registro de respuesta:	POST http://localhost.8080/respuestas

Eliminar respuesta:	DELETE http://localhost.8080/respuestas/id

Actualizar respuesta:	PUT http://localhost.8080/respuestas/id

Lista de respuestas:	GET http://localhost.8080/respuestas

Detallar respuesta:	GET http://localhost.8080/respuestas/id

## :pencil2:
📌 Test del programa

Una vez ingresada la información también es posible correr test (Uso de H2 en los tests)

uso de spring doc Swagger desde: http://localhost:8080/swagger-ui/index.html#/ (/v3/api-docs)

Si se requiere volver a ejecutar el programa después de haber realizado el test:

Debido a que durante el test se deshabilita el flyway: 

entrar al MySQL Workbench -> Navigator -> SCHEMAS

seleccionar forohub -> Server -> Data Export

seleccionar cursos, respuestas, topicos, usuarios

Export to Dump Project Folder -> Downloads

entrar a MySQL y ejecutar:

drop database forohub;

create database forohub;

entrar al MySQL Workbench -> Navigator -> SCHEMAS

seleccionar forohub -> Server -> Data Import

Import from Dump Project Folder -> Downloads

seleccionar cursos, respuestas, topicos, usuarios

## :pencil2:
📌 Cumplimiento de los siguientes requerimientos del trello:

endpoint para el registro de tópicos para aceptar solicitudes POST para la URI /topicos

envio de los datos del topico: titulo, mensaje, autor y curso en el cuerpo de la solicitud en formato JSON

uso de la anotación @RequestBody para recibir los datos

uso del método save del JpaRepository para la persistencia del tópico creado

uso de la anotación Java integrada en Spring @Valid

todos los campos obligatorios

no se permite el registro de tópicos duplicados con el mismo título y mensaje

endpoint para el listado de los tópicos para aceptar solicitudes GET para la URI /topicos

datos devueltos de los tópicos: titulo, mensaje, fecha de creación, estado, autor y curso en el cuerpo de la respuesta en formato JSON

uso del método findALL del JpaRepository asociado al tópico

listado de los primeros 10 resultados ordenados por fecha de creación del tópico en orden ASC

uso de la anotación @PageableDefault para el listado de los resultados con paginación

endpoint para el detalle del tópico para aceptar solicitudes GET para la URI /topicos{id}

datos devueltos de los tópicos: titulo, mensaje, fecha de creación, estado, autor y curso en el cuerpo de la respuesta en formato JSON

uso de la anotación @PathVariable para recibir el ID de la solicitud GET

verificación funcional del ID

endpoint para la actualización de los datos de un tópico para aceptar solicitudes PUT para la URI /topicos{id}

no se permite el registro de tópicos duplicados con el mismo título y mensaje

verificación funcional del ID

uso de la anotación @PathVariable para obtener el ID de la solicitud GET

uso del método isPresent() de la clase Java llamada Optional para verificar la existencia del tópico a actualizar

endpoint para la eliminación de un tópico para aceptar solicitudes DELETE para la URI /topicos{id}

verificación funcional del ID

uso de la anotación @PathVariable para obtener el ID de la solicitud DELETE

uso del método isPresent() de la clase Java llamada Optional para verificar la existencia del tópico a eliminar

uso del método deleteById() del JpaRepository asociado al tópico

pruebas de la API usando Insomnia

actualización del repositorio en GitHub

autenticación de los usuarios con Spring Security

uso de la dependencia Spring Security en el archivo pom.xml

uso de la clase SecurityConfigurations con información para el acceso a través de solicitudes http

uso de las anotaciones @Configuration y @EnableWebSecurity

uso de la clase HttpSecurity

uso de AutenticacionController para recibir las solicitudes de inicio de sesión.

uso de las anotaciones @RestController y @RequestMapping para definir la URL del controller

uso de la instancia DTO DatosAutenticacion para recibir los datos de incio de sesión y contraseña

uso del método AuthenticationManager de la clase SecurityConfigurations

uso de las anotaciones @PostMapping, @RequestBody y @Valid para recibir y validar los datos de la solicitud

migración para incluir una nueva tabla para los datos de los usuarios

uso de JWT (JSON Web Token) para compartir información entre cliente y servidor

uso de la biblioteca Auth0 en el archivo pom.xml

uso de la dependencia Spring Security en el archivo pom.xml

uso de la clase DTO UsernamePasswordAuthenticationToken para recibir el nombre de usuario y contraseña

uso de la clase TokenService para aislar la generación y validación del token

uso del método generarToken() utilizando la biblioteca JWT para crear un token con el algoritmo HMAC256 y una contraseña

configuración de la fecha de expiración del token

inyección de la clase TokenService en el AutenticacionController para obtener el token retornado en la respuesta de la solicitud de inicio de sesión

uso de los atributos jwt.secret y jwt.expiration definidos en el archivo application.properties

uso de la URL "http://localhost:8080/login" pasando el nombre de usuario y contraseña para la generación del token en formato JSON

uso de la clase SecurityFilter (interceptor) para mapear las URLs y validar los tokens en cada solicitud

manejo de excepciones a través de ValidacionException

## :rocket:
🚀 Tecnologías usadas :rocket:

Java 17: Lógica principal del sistema

Maven: Gestión de librerias (dependencias)

Spring Boot Dev Tools: Reinicio automático y recarga en vivo

Lombok: Automatización de constructores, métodos, captadores, definidores

Spring MVC Web: Arquitectura basada en anotaciones en aplicaciones web MVC

Jakarta: Validaciones mediante anotaciones 

MySQL: Base de datos relacional

Hibernate: Framework para mapeo objeto relacional e implementación de Java Persistence API (JPA)

Flyway: control de versiones en cambios de esquemas de la base de datos

Spring Security: autenticación y autorización para el control de acceso

Spring Security Test: soporte para pruebas de autenticación y autorización

auth0: generación y validación de tokens JWT

Spring Doc: Swagger UI Documentación 

Spring: Framework para Inversión de Control (IoC), Programación Orientada a Aspectos (AOP) y Spring Boot para automatización.

SOLID / Arquitectura Limpia	Diseño desacoplado, extensible y testeable

## :key:
🧠 Principios aplicados

SRP — Single Responsibility Principle

Cada clase tiene una única responsabilidad.

OCP — Open/Closed Principle

Abierto a extensión | Cerrado a modificación

DIP — Dependency Inversion Principle

Módulos de alto nivel no dependen de módulos de bajo nivel.

Ambos deben depender de abstracciones (interfaces).

usando:

IoC — Inversión de Control

DI — Dependency Injection

## :clapper:
📌 Esto permite:

Desacoplamiento:	Módulos independientes y mantenibles

Extensión sin romper código

## :pushpin:
🏆 Buenas prácticas aplicadas

Constructor injection para dependencia obligatoria

## :key:
Estructura de paquetes:

src/
└── main/java/com/alura/foro_hub/

│   └── ForoHubApplication

├── main/java/com/alura/foro_hub/controller/

│   ├── AutenticacionController

│   ├── CursoController

│   ├── RespuestaController

│   ├── TopicoController

│   └── UsuarioController

├── main/java/com/alura/foro_hub/domain/

│   └── ValidacionException

├── main/java/com/alura/foro_hub/domain/topico

│   ├── Curso

│   ├── CursoRepository

│   ├── Respuesta

│   ├── RespuestaRepository

│   ├── StatusRespuesta

│   ├── StatusTopico

│   ├── Topico

│   └── TopicoRepository

├── main/java/com/alura/foro_hub/domain/topico/dto

│   ├── DatosActualizacionCurso

│   ├── DatosDetalleCurso

│   ├── DatosListaCurso

│   ├── DatosRegistroCurso

│   ├── DatosActualizacionRespuesta

│   ├── DatosDetalleRespuesta

│   ├── DatosListaRespuesta

│   ├── DatosRegistroRespuesta

│   ├── DatosActualizacionTopico

│   ├── DatosDetalleTopico

│   ├── DatosListaTopico

│   └── DatosRegistroTopico

├── main/java/com/alura/foro_hub/domain/usuario

│   ├── AutenticacionService

│   ├── DatosAutenticacion

│   ├── PerfilUsuario

│   ├── Usuario

│   └── UsuarioRepository

├── main/java/com/alura/foro_hub/domain/usuario/dto

│   ├── DatosActualizacionUsuario

│   ├── DatosDetalleUsuario

│   ├── DatosListaUsuario

│   └── DatosRegistroUsuario

├── main/java/com/alura/foro_hub/infra/

└── main/java/com/alura/foro_hub/infra/exceptions/

│   └── GestorDeErrores

└── main/java/com/alura/foro_hub/infra/security/

│   ├── DatosTokenJWT

│   ├── SecurityConfigurations

│   ├── SecurityFilter

│   └── TokenService

└── main/java/com/alura/foro_hub/infra/springdoc/

│   └── SpringDocConfiguration

└── main/resources/

│   ├── application.properties

│   ├── application-prod.properties

│   └── application-test.properties

└── main/resources/db/migration/

│   ├── V1

│   ├── V2

│   ├── V3

│   ├── V4

│   ├── V5

│   ├── V6

│   ├── V7

│   ├── V8

│   ├── V9

│   ├── V10

│   └── V11

└── src/test/java/com/alura/foro_hub

│   └── ForoHubApplicationTests

└── src/test/java/com/alura/foro_hub/controller/

│   └── TopicoControllerTest

## :flashlight:
- Acceso al proyecto: a través de GitHub
- Estado del proyecto: funcional 100%
- Características de la aplicación: Desafio Foro Hub Alura Latam
- Desarrolladores: Carlos Arturo Torres Jara
- Licencia: código abierto
- Github: cartj2000
- Linkedin: CARLOS ARTURO TORRES JARA

## :heavy_exclamation_mark:
Agradecimientos:

- 👉Alura Latam: Equipo docente
- 👉Oracle: programa One Oracle Next Education
- 👉springdoc: documentación
