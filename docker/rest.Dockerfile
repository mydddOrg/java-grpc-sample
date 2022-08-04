# syntax=docker/dockerfile:1

FROM amazoncorretto:11.0.16
WORKDIR /app
COPY ./rest-bootstrap-1.0.0-SNAPSHOT.jar  .
ENTRYPOINT ["java","-jar","/app/rest-bootstrap-1.0.0-SNAPSHOT.jar"]