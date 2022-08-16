#!/bin/sh

tag=$1
docker tag java-grpc-sample-distributed:"${tag}" lingen/java-grpc-sample-distributed:"${tag}"
docker push lingen/java-grpc-sample-distributed:"${tag}"

docker tag java-grpc-sample-user:"${tag}" lingen/java-grpc-sample-user:"${tag}"
docker push lingen/java-grpc-sample-user:"${tag}"

docker tag java-grpc-sample-rest:"${tag}" lingen/java-grpc-sample-rest:"${tag}"
docker push lingen/java-grpc-sample-rest:"${tag}"