# syntax=docker/dockerfile:1

FROM java-base:1
WORKDIR /app
COPY ./distributed-id-bootstrap-1.0.0-SNAPSHOT.jar  .
ENV OTEL_SERVICE_NAME=distributed
ENV OTEL_TRACES_EXPORTER=jaeger
ENV OTEL_EXPORTER_JAEGER_ENDPOINT=http://jaeger:14250
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://jaeger:4317
ENV OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=http://jaeger:4317
ENV OTLP_METRICS_EXPORTER=none
ENTRYPOINT ["java","-javaagent:/app/opentelemetry-javaagent-1.16.0.jar","-jar","--add-opens","java.base/java.lang=ALL-UNNAMED","/app/distributed-id-bootstrap-1.0.0-SNAPSHOT.jar"]