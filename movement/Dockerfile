# Step 1: Build the JAR file
# Use Maven image to build the project
FROM maven:3.8.7-eclipse-temurin-17 AS builder
# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files (pom.xml and src) into the container
COPY pom.xml .
COPY src ./src

# Run Maven build to generate the JAR file
RUN mvn clean package -DskipTests

# Step 2: Run the application
# Use a smaller JDK image to run the built JAR
FROM openjdk:17-jdk-slim

# Set the working directory for the app
WORKDIR /app

# Copy the generated JAR file from the build stage to the runtime stage
COPY --from=builder /app/target/movement-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the application port
EXPOSE 9080

# Environment variables for database connection


# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
