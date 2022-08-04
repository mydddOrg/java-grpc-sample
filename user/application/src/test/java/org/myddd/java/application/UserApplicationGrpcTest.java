package org.myddd.java.application;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.myddd.java.AbstractTest;
import org.myddd.java.user.api.SearchUserDto;
import org.myddd.java.user.api.UserApplicationGrpc;

import java.io.IOException;

public class UserApplicationGrpcTest extends AbstractTest {

    private final static int PORT = 8081;

    private static Server server;

    private static ManagedChannel managedChannel;

    @BeforeAll
    public static void beforeAll() throws IOException {
        server = NettyServerBuilder.forPort(PORT).addService(new UserApplicationGrpcImpl()).build();
        server.start();

        managedChannel = ManagedChannelBuilder.forTarget("127.0.0.1:"+ PORT).usePlaintext().build();
    }

    @AfterAll
    public static void afterAll(){
        server.shutdown();
    }

    @Test
    void createUser(){
        var userApplicationStub = UserApplicationGrpc.newBlockingStub(managedChannel);
        Assertions.assertNotNull(userApplicationStub.createUser(randomUserDTO()));
    }

    @Test
    void searchUser(){
        var userApplicationStub = UserApplicationGrpc.newBlockingStub(managedChannel);

        var searchUserDto = SearchUserDto.newBuilder()
                .setPage(0)
                .setPageSize(10)
                .setSearch(randomString())
                .build();
        Assertions.assertTrue(userApplicationStub.searchUser(searchUserDto).getUsersList().isEmpty());

        var created = userApplicationStub.createUser(randomUserDTO());
        searchUserDto = SearchUserDto.newBuilder()
                .setPage(0)
                .setPageSize(10)
                .setSearch(created.getName())
                .build();

        Assertions.assertFalse(userApplicationStub.searchUser(searchUserDto).getUsersList().isEmpty());
    }
}
