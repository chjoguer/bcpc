# ---- Stage 1: Build the application ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src
COPY src/main/resources/application.properties ./application.properties

# Build the application (this generates the .jar)
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the application ----
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the generated JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 9090

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

