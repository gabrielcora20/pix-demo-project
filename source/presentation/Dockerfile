FROM gradle:8.7-jdk21-alpine AS builder
USER root
WORKDIR /builder
COPY . .
WORKDIR /builder/presentation
RUN ./gradlew build

FROM openjdk:21-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/presentation/build/libs/presentation-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "presentation-0.0.1-SNAPSHOT.jar"]