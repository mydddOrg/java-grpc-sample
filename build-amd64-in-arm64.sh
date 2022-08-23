#!/bin/sh

tag=$1

./gradlew build && echo "编译成功" || exit 1

# 构建带opentelemetry的Java基础镜像
docker buildx build -f ./docker/java-base.Dockerfile -t java-base:1 ./docker --platform=linux/amd64 && echo "构建Java基础镜像成功" || exit 1

# shellcheck disable=SC2086
docker buildx build -f ./docker/distributed.Dockerfile -t java-grpc-sample-distributed:${tag} ./distributed-id/distributed-id-bootstrap/build/libs/ --platform=linux/amd64 && echo "构建distribute模块镜像成功" || exit 1

docker buildx build -f ./docker/user.Dockerfile -t java-grpc-sample-user:"${tag}" ./user/grpc-bootstrap/build/libs/ && echo "构建User模块镜像成功" --platform=linux/amd64 || exit 1

docker buildx build -f ./docker/rest.Dockerfile -t java-grpc-sample-rest:"${tag}" ./rest/rest-bootstrap/build/libs/ && echo "构建Rest模块镜像成功" --platform=linux/amd64 || exit 1

docker image prune -f && echo "=====构建成功=====" || echo "构建失败"