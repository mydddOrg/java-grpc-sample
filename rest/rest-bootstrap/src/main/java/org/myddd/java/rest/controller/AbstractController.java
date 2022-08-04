package org.myddd.java.rest.controller;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.myddd.domain.InstanceFactory;
import org.myddd.java.rest.ConfigProperties;

import java.util.Objects;

public class AbstractController {


    private static ConfigProperties properties;

    private static ConfigProperties getProperties(){
        if(Objects.isNull(properties)){
            properties = InstanceFactory.getInstance(ConfigProperties.class);
        }
        return properties;
    }

    private static ManagedChannel managedChannel;

    protected static ManagedChannel getManagedChannel(){
        if(Objects.isNull(managedChannel)){
            managedChannel = ManagedChannelBuilder.
                    forTarget(getProperties().getUserGrpcAddress())
                    .defaultLoadBalancingPolicy("round_robin")
                    .usePlaintext()
                    .build();
        }
        return managedChannel;
    }
}
