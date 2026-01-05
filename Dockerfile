# Stage 1: Build with Gradle
FROM gradle:8.5-jdk17 AS builder

WORKDIR /build
COPY . .
RUN gradle build -x test

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /build/build/libs/*.jar ./application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
