package org.myddd.java.rest.controller;

import com.google.gson.JsonObject;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.junit.jupiter.api.*;
import org.myddd.domain.InstanceFactory;
import org.myddd.ioc.spring.SpringInstanceProvider;
import org.myddd.java.application.UserApplicationGrpcImpl;
import org.myddd.java.distributed.application.DistributedIdApplicationGrpcImpl;
import org.myddd.java.rest.AbstractControllerTest;
import org.myddd.java.rest.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class UserControllerTest extends AbstractControllerTest {

    private final static int PORT = 8081;

    private static Server server;

    private static ManagedChannel managedChannel;


    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void beforeClass(){
        InstanceFactory.setInstanceProvider(SpringInstanceProvider.createInstance(applicationContext));
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        server = NettyServerBuilder.forPort(PORT)
                .addService(new DistributedIdApplicationGrpcImpl())
                .addService(new UserApplicationGrpcImpl())
                .build();
        server.start();

        managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", PORT).usePlaintext().build();
    }

    @AfterAll
    public static void afterAll(){
        server.shutdown();
    }


    @Test
    void createUser(){
        ResponseEntity<UserVO> errorResponse = restTemplate.exchange(baseUrl()+"/v1/users", HttpMethod.POST,createHttpEntityFromString(randomUserJSON()),UserVO.class);
        Assertions.assertTrue(errorResponse.getStatusCode().is2xxSuccessful());
    }


    private String randomUserJSON(){
        var jsonObject = new JsonObject();
        jsonObject.addProperty("userId",randomString());
        jsonObject.addProperty("name",randomString());
        jsonObject.addProperty("phone",randomString());
        jsonObject.addProperty("email",randomString());
        return jsonObject.toString();
    }



}
