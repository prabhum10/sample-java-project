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

# Copy all JARs from builder
COPY --from=builder /build/build/libs/*.jar /app/

# Expose port
EXPOSE 8080

# Run any jar file found (shell expands wildcard at runtime)
CMD ["sh", "-c", "java -jar /app/*.jar"]
