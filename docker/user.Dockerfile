# syntax=docker/dockerfile:1

FROM amazoncorretto:11.0.16
WORKDIR /app
COPY ./grpc-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","/app/grpc-bootstrap-1.0.0-SNAPSHOT.jar"]