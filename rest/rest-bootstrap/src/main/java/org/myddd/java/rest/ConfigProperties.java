package org.myddd.java.rest;

import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;

@Named
public class ConfigProperties {

    @Value("${user.grpc.address}")
    private String userGrpcAddress;

    @Value("${distribute.grpc.address}")
    private String distributeGrpcAddress;

    public String getUserGrpcAddress() {
        return userGrpcAddress;
    }

    public String getDistributeGrpcAddress() {
        return distributeGrpcAddress;
    }
}
