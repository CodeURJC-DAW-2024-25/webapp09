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
