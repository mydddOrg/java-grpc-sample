# syntax=docker/dockerfile:1

FROM openjdk:17.0.2-slim-bullseye
WORKDIR /app
COPY ./distributed-id-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","--add-opens","java.base/java.lang=ALL-UNNAMED","/app/distributed-id-bootstrap-1.0.0-SNAPSHOT.jar"]