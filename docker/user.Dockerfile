# syntax=docker/dockerfile:1

FROM eclipse-temurin:11.0.16_8-jre
WORKDIR /app
COPY ./grpc-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","/app/grpc-bootstrap-1.0.0-SNAPSHOT.jar"]