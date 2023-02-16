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




## Correlo localmente

 ### Configuración

 - Clona este reposiotrio utilizando `git clone https://github.com/khebinSd/KrugerSFinalTask`
 - Es necesario tener instalado el JDK para Java 17.
 - Postman u otra herramienta como Thunder Client o Insonmia para el test de la API

 ### Pasos

 - Primero abrir el proyecto `config-microservicio`, este tiene centralizado los archivos de configruación de cada microservicio, ejectuar el proyecto con el IDE de confianza o utilizando `gradle bootrun`, se ejecutará en el puerto `8080`

 - Segundo abrir el proyecto `registry-microservicio`, este microservicio mantiene el registro de los microservicios del proyecto a manos de Eureka, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `8081`

 - Tercero abrir el proyecto `gateway-microservicio`, este microservicio se conecta con Eureka para obtener la información de los microservicios registrados e implementar, circuit breaker utilizando resilience4j y feign client además de otorgar balanceo de carga a los microservicios, ejectuar el proyecto con el IDE de confianza o utilizando `gradle bootrun`, se ejecturá en el puerto `8082`

 - Cuarto abrir el proyecto `product-microservicio`, este microservicio contiene la API  de los productos, reviews y categorias y se conecta con el microservicio `customer-microservicio` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9090`

 - Cuarto abrir el proyecto `order-microservicio`, este microservicio contiene la API  de los productos y customers/admis y se conecta con el microservicio `´product-microservicio` y `customer-microservicio` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9091`

 - Quinto abrir el proyecto `auth-microservicio`, este microservicio contiene la API  de los usuarios y se conecta con el microservicio `order-microservicio` mediante Feign client, ejectuar el proyecto con el IDE de confianza o utilizando`gradle bootrun`, se ejecturá en el puerto `9092`


 ### Documentación

  - Para probar la documentación del microservicio `customer-microservicio` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9090/swagger-ui/index.html#/](http://localhost:9090/swagger-ui/index.html#/)

  - Para probar la documentación del microservicio `company-microservicio` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9091/swagger-ui/index.html#/](http://localhost:9091/swagger-ui/index.html#/)

  - Para probar la documentación del microservicio `auth-microservicio` utilizando swagger se ejecuta localmente en la siguiente ruta  [http://localhost:9091/swagger-ui/index.html#/](http://localhost:9092/swagger-ui/index.html#/)


<!-- ACKNOWLEDGMENTS -->

<br/>

# ℹ️ Acknowledgments

<table>
    <tbody>
        <tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="./public/images/pepe.jpg" width="100px;" alt="Alexander Cangas"/><br /><sub><b>Jose Andres Gavilanes</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
<tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="https://avatars.githubusercontent.com/u/33032880?v=4" width="100px;" alt="Alexander Cangas"/><br /><sub><b>Kevin Mantilla</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
        <tr>
        <td align="center"><a href="https://github.com/joseandresgavilanes"><img src="https://avatars.githubusercontent.com/u/52118245?v=4" width="100px;" alt="Alexander Cangas"/><br /><sub><b>Kenan Al-jaber</b></sub></a><br /><a href="https://github.com/joseandresgavilanes" title="Code">💻 Desarrollador</a></td>
        </tr>
        <br/>
        <tr>
            <td align="center"><a href="https://github.com/Juanse7793"><img src="https://avatars.githubusercontent.com/u/96317674?v=4" width="100px;" alt="Yuval Hazaz"/><br /><sub><b>Juan Sebastian Sotomayor</b></sub></a><br /><a href="https://github.com/Juanse7793" title="Code">FrontEnd Kruger Mentor</a></td>
        </tr>
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
