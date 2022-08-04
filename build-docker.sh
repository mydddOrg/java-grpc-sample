#!/bin/sh

./gradlew build && echo "编译成功" || exit 1

docker build -f ./docker/distributed.Dockerfile -t java-grpc-sample-distributed:1 ./distributed-id/distributed-id-bootstrap/build/libs/ && echo "构建distribute模块镜像成功" || exit 1

docker build -f ./docker/user.Dockerfile -t java-grpc-sample-user:1 ./user/grpc-bootstrap/build/libs/ && echo "构建User模块镜像成功" || exit 1

docker build -f ./docker/rest.Dockerfile -t java-grpc-sample-rest:1 ./rest/rest-bootstrap/build/libs/ && echo "构建Rest模块镜像成功" || exit 1

docker image prune -f && echo "=====构建成功=====" || echo "构建失败"