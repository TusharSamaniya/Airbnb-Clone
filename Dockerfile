# Use a small Java 21 image (good for Spring Boot)
FROM eclipse-temurin:21-jre-alpine

# Create folder for your app
WORKDIR /app

# Copy your built jar file (the one from target folder)
COPY target/*.jar app.jar

# Tell Docker that your app will listen on port 8080
EXPOSE 8080

# Run the app when container starts
CMD ["java", "-jar", "app.jar"]