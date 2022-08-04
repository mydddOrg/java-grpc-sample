#!/bin/sh

docker tag java-grpc-sample-distributed:2 lingen/java-grpc-sample-distributed:2
docker push lingen/java-grpc-sample-distributed:2

docker tag java-grpc-sample-user:2 lingen/java-grpc-sample-user:2
docker push lingen/java-grpc-sample-user:2


docker tag java-grpc-sample-rest:2 lingen/java-grpc-sample-rest:2
docker push lingen/java-grpc-sample-rest:2