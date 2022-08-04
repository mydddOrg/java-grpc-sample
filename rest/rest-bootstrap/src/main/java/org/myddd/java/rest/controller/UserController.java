package org.myddd.java.rest.controller;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.myddd.domain.InstanceFactory;
import org.myddd.java.rest.ConfigProperties;
import org.myddd.java.rest.vo.UserVO;
import org.myddd.java.user.api.UserApplicationGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller()
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/v1")
public class UserController {

    private static ConfigProperties properties;

    private static ConfigProperties getProperties(){
        if(Objects.isNull(properties)){
            properties = InstanceFactory.getInstance(ConfigProperties.class);
        }
        return properties;
    }

    private static ManagedChannel managedChannel;

    private static ManagedChannel getManagedChannel(){
        if(Objects.isNull(managedChannel)){
            managedChannel = ManagedChannelBuilder.forTarget(getProperties().getUserGrpcAddress()).usePlaintext().build();
        }
        return managedChannel;
    }

    @PostMapping("/users")
    ResponseEntity<UserVO> createUser(@RequestBody UserVO userVO){
        var userApplicationStub = UserApplicationGrpc.newBlockingStub(getManagedChannel());
        var created = userApplicationStub.createUser(userVO.toUserDto());
        return ResponseEntity.ok(UserVO.of(created));
    }
}
