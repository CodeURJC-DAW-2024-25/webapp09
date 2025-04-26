# Angular compilation
FROM node:18.13 AS angular
WORKDIR /code
COPY frontend2/package*.json frontend2/angular.json frontend2/tsconfig*.json /code/
RUN npm install && npm install chart.js
COPY frontend2/src /code/src
RUN npm run build -- --configuration production --base-href=/new/



FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /daw
COPY ../backend/pom.xml .  
COPY ../backend/src ./src  

# Run Maven command to build the project
RUN mvn clean package -DskipTests

# Execution
FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app
# Copy the generated JAR file from the build stage
COPY --from=builder /daw/target/*.jar app.jar
EXPOSE 443
CMD ["java", "-jar", "app.jar"]
