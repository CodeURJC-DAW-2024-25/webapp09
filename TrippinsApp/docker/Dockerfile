FROM node:19.0.1 AS angular
WORKDIR /code
COPY ./frontend/package*.json ./frontend/angular.json ./frontend/tsconfig*.json /code/
RUN npm install && npm install chart.js
COPY ../frontend/src /code/src
RUN npm run build -- --configuration production --base-href=/new/

FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /daw
COPY ./backend/pom.xml .  
RUN mvn dependency:go-offline
COPY ./backend/src ./src
RUN mkdir -p src/main/resources/static/new
COPY --from=angular /code/dist/trippins-app/browser/ src/main/resources/static/new
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /daw
COPY --from=builder /daw/target/*.jar app.jar
EXPOSE 443
CMD ["java", "-jar", "app.jar"]
