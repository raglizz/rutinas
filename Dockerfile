# Etapa 1: build con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen final
FROM openjdk:21-jdk-slim
WORKDIR /app
RUN ls -l /app/target  
COPY --from=builder /app/target/rutinas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
