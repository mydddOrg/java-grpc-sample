# syntax=docker/dockerfile:1

FROM openjdk:17.0.2-slim-bullseye
WORKDIR /app
COPY ./document-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","/app/document-bootstrap-1.0.0-SNAPSHOT.jar"]