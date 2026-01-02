# Use pre-built JAR from gradle build
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy pre-built JAR from gradle build output
COPY build/libs/*.jar ./
RUN mv *.jar application.jar

# Expose port (adjust as needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "application.jar"]
