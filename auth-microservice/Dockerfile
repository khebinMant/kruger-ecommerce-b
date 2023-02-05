# Uso del jdk 17 
# FROM openjdk:17-alpine
FROM ubuntu:20.04

#Instalación de JDK17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Instalación de la biblioteca libfreetype
RUN apt-get update && apt-get install -y libfreetype6

# Directorio dentro del contenedor donde se copiara
WORKDIR /app

# Que archivos voy a copiar al directorio /app
COPY target/auth-microservice-0.0.1-SNAPSHOT.jar /app/auth-microservice-0.0.1-SNAPSHOT.jar

# Run the JAR file as the default command
CMD ["java", "-jar", "auth-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 9092
