<br />
<div align="center">
  <a href="https://github.com">
    <img src="./resources/img/logo.png" alt="Logo" width="300" height="180">
  </a>

  <h1 style="font-size: 60px; font-weight: 700" align="center">Kruger Cell</h1>


<h4 align="center">

  <p align="center">https://kruger-cell.vercel.app</p>

</h4>

---

  <p align="center">
    The perfect phone and services for you
    <br />
    <br />
    <a href="https://github.com/joseandresgavilanes/spotify/issues">View Demo</a>
    ·
    <a href="https://github.com/joseandresgavilanes/spotify/issues">Report Bug</a>
    ·
    <a href="https://github.com/joseandresgavilanes/spotify/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->


## Construido con 

- Spring Boot 3.0.1
- JAVA 17
- Gradle
- Mvn
- Eureka Netflix
- Feign Client
- Resilience4j
- Spring Boot Security
- Spring Boot Gateway
- Spring Mail
- Postman
- Mockito
- Swagger
- Junit 5
- JasperReport
- Docker



# 📱 About The Project

Este proyecto de programación se enfoca en el desarrollo de un ecommerce de celulares. El objetivo es crear una plataforma en línea fácil de usar y accesible para los clientes, donde puedan navegar y comprar dispositivos móviles de diferentes marcas y modelos.

La plataforma incluirá una página principal que muestre los productos destacados y una sección de búsqueda avanzada para ayudar a los clientes a encontrar el teléfono que desean. Cada producto tendrá su propia página con detalles completos sobre las especificaciones técnicas, imágenes y opiniones de los clientes.

Además, se implementará un carrito de compras para que los clientes puedan agregar los productos que desean comprar y proceder con el pago en línea a través de una pasarela segura. También se incluirán opciones de envío y seguimiento del paquete para que los clientes puedan monitorear el progreso de su compra.

El proyecto también incluirá un panel de administración para que el equipo de ecommerce pueda administrar el catálogo de productos, procesar los pedidos y monitorear las estadísticas de la tienda en línea.

En resumen, este proyecto de programación brindará a los clientes una experiencia de compra en línea fluida y segura para comprar sus dispositivos móviles, y al mismo tiempo, brindará una solución eficiente y fácil de usar para el equipo de ecommerce.

# 💾 🗄️  Correlo localmente

 ### Configuración

 - Clona este reposiotrio utilizando `git clone https://github.com/khebinSd/kruger-ecommerce-b`
 - Es necesario tener instalado el JDK para Java 17.
 - Postman u otra herramienta como Thunder Client o Insonmia para el test de la API

 ### Pasos

 - Primero abrir el proyecto `config-microservice`, este tiene centralizado los archivos de configruación de cada microservicio, ejectuar el proyecto con el IDE de confianza o utilizando `gradle bootrun`, se ejecutará en el puerto `8080`

 - Segundo abrir el proyecto `registry-microservice`, este microservicio mantiene el registro de los microservicios del proyecto a manos de Eureka, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `8081`

 - Tercero abrir el proyecto `product-microservice`, este microservicio contiene la API  de los productos, reviews y categorias y se conecta con el microservicio `customer-microservice` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9090`

 - Cuarto abrir el proyecto `order-microservice`, este microservicio contiene la API  de los productos y customers/admis y se conecta con el microservicio `´product-microservice` y `auth-microservice` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9091`

 - Quinto abrir el proyecto `auth-microservice`, este microservicio contiene la API  de los usuarios y se conecta con el microservicio `order-microservice` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9092`

 - Sexto abrir el proyecto `company-microservice`, este microservicio contiene la API de la compañia, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9093`

 - Finalmente abrir el proyecto `gateway-microservice`, este microservicio se conecta con Eureka para obtener la información de los microservicios registrados e implementar, circuit breaker utilizando resilience4j con feign client además de otorgar balanceo de carga al resto de los microservicios levantados previamente, ejectuar el proyecto con el IDE de confianza o utilizando `gradle bootrun`, se ejecturá en el puerto `8082`

 ### Documentación

  - Para probar la documentación del microservicio `product-microservice` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9090/swagger-ui/index.html#/](http://localhost:9090/swagger-ui/index.html#/)

  - Para probar la documentación del microservicio `order-microservice` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9091/swagger-ui/index.html#/](http://localhost:9091/swagger-ui/index.html#/)

  - Para probar la documentación del microservicio `auth-microservice` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9092/swagger-ui/index.html#/](http://localhost:9092/swagger-ui/index.html#/)

  - Para probar la documentación del microservicio `company-microservice` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9093/swagger-ui/index.html#/](http://localhost:9092/swagger-ui/index.html#/)


<!-- ACKNOWLEDGMENTS -->

<br/>

# ℹ️ Acknowledgments

<table>
    <tbody>
        <tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="https://avatars.githubusercontent.com/u/76002851?v=4" width="100px;" alt="Jose Andres Gavilanes"/><br /><sub><b>Jose Andres Gavilanes</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
<tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="https://avatars.githubusercontent.com/u/33032880?v=4" width="100px;" alt="Alexander Cangas"/><br /><sub><b>Kevin Mantilla</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
        <tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="https://avatars.githubusercontent.com/u/52118245?v=4" width="100px;" alt="Alexander Cangas"/><br /><sub><b>Kenan Al-jaber</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
        <br/>
    </tbody>
</table>

[linkedin.js]: https://img.shields.io/badge/-LinkedIn-1C82AD?logo=LinkedIn
[linkedin-url]: https://www.linkedin.com/in/jose-andres-gavilanes-2954691b5/1
[linkedin2-url]: https://www.linkedin.com/in/jose-andres-gavilanes-2954691b5/
[linkedin3-url]: https://www.linkedin.com/in/jose-andres-gavilanes-2954691b5/
[github.js]: https://img.shields.io/badge/-GitHub-181717?logo=GitHub
[github-url]: https://github.com/joseandresgavilanes
[github2-url]: https://github.com/KenanAljaber
[github3-url]: https://github.com/khebinSd



## 🤲 Show your support

- Give me a ⭐ if you like it!
