#!/bin/sh

tag=$1
docker tag java-grpc-sample-distributed:"${tag}" 172.16.1.131:5000/java-grpc-sample-distributed:"${tag}"
docker push 172.16.1.131:5000/java-grpc-sample-distributed:"${tag}"

docker tag java-grpc-sample-user:"${tag}" 172.16.1.131:5000/java-grpc-sample-user:"${tag}"
docker push 172.16.1.131:5000/java-grpc-sample-user:"${tag}"

docker tag java-grpc-sample-rest:"${tag}" 172.16.1.131:5000/java-grpc-sample-rest:"${tag}"
docker push 172.16.1.131:5000/java-grpc-sample-rest:"${tag}"