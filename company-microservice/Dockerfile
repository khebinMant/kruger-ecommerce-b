# Uso del jdk 17 
FROM openjdk:17-alpine

# Directorio dentro del contenedor donde se copiara
WORKDIR /app

# Que archivos voy a copiar al directorio /app
COPY build/libs/company-microservice-0.0.1-SNAPSHOT.jar /app/company-microservice-0.0.1-SNAPSHOT.jar

# Run the JAR file as the default command
CMD ["java", "-jar", "company-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081
