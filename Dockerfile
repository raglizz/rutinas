# Etapa 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar el .jar generado
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/rutinas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
