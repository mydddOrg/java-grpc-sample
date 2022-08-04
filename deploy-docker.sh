#!/bin/sh

docker tag java-grpc-sample-distributed:1 lingen/java-grpc-sample-distributed:1
docker push lingen/java-grpc-sample-distributed:1

docker tag java-grpc-sample-user:1 lingen/java-grpc-sample-user:1
docker push lingen/java-grpc-sample-user:1


docker tag java-grpc-sample-rest:1 lingen/java-grpc-sample-rest:1
docker push lingen/java-grpc-sample-rest:1