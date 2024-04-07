FROM gradle:8.7-jdk21-alpine AS builder
USER root
WORKDIR /builder
COPY . .
WORKDIR /builder/listener
RUN ./gradlew build

FROM openjdk:21-slim
WORKDIR /app
EXPOSE 8090
COPY --from=builder /builder/listener/build/libs/listener-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "listener-0.0.1-SNAPSHOT.jar"]