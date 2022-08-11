# syntax=docker/dockerfile:1

FROM eclipse-temurin:11.0.16_8-jre-alpine
WORKDIR /app
COPY ./rest-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","/app/rest-bootstrap-1.0.0-SNAPSHOT.jar"]