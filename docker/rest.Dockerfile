# syntax=docker/dockerfile:1

FROM lingen/java-base:1
WORKDIR /app
COPY ./rest-bootstrap-1.0.0-SNAPSHOT.jar  .
ENV OTEL_SERVICE_NAME=rest
ENV OTEL_TRACES_EXPORTER=jaeger
ENV OTEL_EXPORTER_JAEGER_ENDPOINT=http://jaeger:14250
ENV OTEL_METRICS_EXPORTER=prometheus
ENTRYPOINT ["java","-javaagent:/app/opentelemetry-javaagent-1.16.0.jar","-jar","/app/rest-bootstrap-1.0.0-SNAPSHOT.jar"]