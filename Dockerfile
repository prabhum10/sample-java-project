# Multi-stage build: Stage 1 - Builder
FROM gradle:8.5-jdk17 AS builder

WORKDIR /build

# Copy source code
COPY . .

# Build the application (skip tests)
RUN gradle build -x test

# Stage 2 - Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy JAR from builder stage - copy all jars to temp directory then rename
COPY --from=builder /build/build/libs/*.jar /app/
RUN mv /app/*.jar /app/application.jar

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD java -cp application.jar -version || exit 1

# Expose port (adjust as needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "application.jar"]
