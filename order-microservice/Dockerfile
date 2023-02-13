# Uso del jdk 17 
FROM openjdk:17-alpine

# Directorio dentro del contenedor donde se copiara
WORKDIR /app

# Que archivos voy a copiar al directorio /app
COPY target/order-microservice-0.0.1-SNAPSHOT.jar /app/order-microservice-0.0.1-SNAPSHOT.jar

# Run the JAR file as the default command
CMD ["java", "-jar", "order-microservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 9091
