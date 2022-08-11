# syntax=docker/dockerfile:1

FROM eclipse-temurin:11.0.16_8-jre-alpine
WORKDIR /app
COPY ./distributed-id-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","--add-opens","java.base/java.lang=ALL-UNNAMED","/app/distributed-id-bootstrap-1.0.0-SNAPSHOT.jar"]