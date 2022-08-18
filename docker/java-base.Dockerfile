# syntax=docker/dockerfile:1

#构建镜像
FROM eclipse-temurin:17.0.4_8-jre
WORKDIR /app
COPY libs/opentelemetry-javaagent-1.16.0.jar .