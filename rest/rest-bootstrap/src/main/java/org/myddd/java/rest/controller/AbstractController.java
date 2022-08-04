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

    private static ManagedChannel userManagedChannel;

    protected static ManagedChannel getUserManagedChannel(){
        if(Objects.isNull(userManagedChannel)){
            userManagedChannel = ManagedChannelBuilder.
                    forTarget(getProperties().getUserGrpcAddress())
                    .defaultLoadBalancingPolicy("round_robin")
                    .usePlaintext()
                    .build();
        }
        return userManagedChannel;
    }

    private static ManagedChannel distributeManagerChannel;

    protected static ManagedChannel getDistributeManagerChannel(){
        if(Objects.isNull(distributeManagerChannel)){
            distributeManagerChannel = ManagedChannelBuilder.
                    forTarget(getProperties().getDistributeGrpcAddress())
                    .defaultLoadBalancingPolicy("round_robin")
                    .usePlaintext()
                    .build();
        }
        return distributeManagerChannel;
    }
}
