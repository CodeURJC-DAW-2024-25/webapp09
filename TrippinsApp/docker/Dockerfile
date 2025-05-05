# Angular compilation
FROM node:19.0.1 AS angular
WORKDIR /code

# Copia los archivos necesarios del frontend
COPY ./frontend/package*.json ./frontend/angular.json ./frontend/tsconfig*.json /code/
RUN npm install && npm install chart.js

# Copia el código fuente del frontend
COPY ../frontend/src /code/src

# Construye la aplicación Angular
RUN npm run build -- --configuration production --base-href=/new/

# Maven build
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /daw

# Copia el archivo pom.xml y descarga las dependencias
COPY ./backend/pom.xml .  
RUN mvn dependency:go-offline

# Copia el código fuente del backend
COPY ./backend/src ./src

# Crea el directorio para los archivos estáticos de Angular
RUN mkdir -p src/main/resources/static/new

# Copia los archivos construidos de Angular al backend
COPY --from=angular /code/dist/trippins-app/browser/ src/main/resources/static/new

# Compila el backend y empaqueta la aplicación
RUN mvn clean package -DskipTests

# Runtime
FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app

# Copia el archivo JAR generado desde la etapa de compilación
COPY --from=builder /daw/target/*.jar app.jar

# Expone el puerto 443
EXPOSE 443

# Comando de entrada para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
