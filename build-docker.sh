#!/bin/sh

gradle build -x test -Plocal=false&& echo "编译成功" || exit 1

docker buildx build -f ./docker/distributed.Dockerfile  -t myddd-micro-java-distribute:dev ./distributed-id/distributed-id-bootstrap/build/libs/ --platform=linux/amd64 && echo "构建distribute模块镜像成功" || exit 1

docker buildx build -f ./docker/document.Dockerfile -t myddd-micro-java-document:dev ./document/document-bootstrap/build/libs/ --platform=linux/amd64 && echo "构建Document模块镜像成功" || exit 1

docker buildx build -f ./docker/rest.Dockerfile -t myddd-micro-java-rest:dev ./rest/rest-bootstrap/build/libs/ --platform=linux/amd64 && echo "构建Rest模块镜像成功" || exit 1

docker image prune -f && echo "=====构建成功=====" || echo "构建失败"