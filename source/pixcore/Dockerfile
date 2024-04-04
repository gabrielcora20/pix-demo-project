FROM gradle:8.7-jdk21-alpine AS builder
USER root
WORKDIR /builder
COPY . .
RUN ./gradlew build

FROM openjdk:21-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/pixcore-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "pixcore-0.0.1-SNAPSHOT.jar"]