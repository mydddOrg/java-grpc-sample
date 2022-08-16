package org.myddd.java.application;

import io.grpc.stub.StreamObserver;
import org.myddd.domain.InstanceFactory;
import org.myddd.grpc.GrpcRunner;
import org.myddd.java.user.api.*;

import java.util.Objects;

public class UserApplicationGrpcImpl extends UserApplicationGrpc.UserApplicationImplBase {


    private static UserApplication userApplication;

    private static UserApplication getUserApplication(){
        if(Objects.isNull(userApplication)){
            userApplication = InstanceFactory.getInstance(UserApplication.class);
        }
        return userApplication;
    }

    @Override
    public void createUser(UserDto request, StreamObserver<UserDto> responseObserver) {
        GrpcRunner.run(responseObserver,()->getUserApplication().createUser(request));
    }

    @Override
    public void searchUser(SearchUserDto request, StreamObserver<PageUserDto> responseObserver) {
        GrpcRunner.run(responseObserver,()->getUserApplication().searchUser(request));
    }
}
