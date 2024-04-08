FROM gradle:8.7-jdk21-alpine AS builder
USER root
WORKDIR /builder
COPY . .
WORKDIR /builder/presentation
RUN gradle wrapper --gradle-version 8.7
RUN ./gradlew build

FROM openjdk:21-slim
WORKDIR /app
EXPOSE 80
COPY --from=builder /builder/presentation/build/libs/presentation-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "presentation-0.0.1-SNAPSHOT.jar"]