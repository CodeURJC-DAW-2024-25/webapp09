FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /daw
COPY pom.xml .
COPY src ./src

# Ejecutar el comando de Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Execution
FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app
# Copiar el archivo JAR generado desde la fase de construcción
COPY --from=builder /daw/target/*.jar app.jar
EXPOSE 443
CMD ["java", "-jar", "app.jar"]
