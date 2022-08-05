package org.myddd.java.application;

import io.grpc.stub.StreamObserver;
import org.myddd.domain.InstanceFactory;
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
        var created = getUserApplication().createUser(request);
        responseObserver.onNext(created);
        responseObserver.onCompleted();
    }

    @Override
    public void searchUser(SearchUserDto request, StreamObserver<PageUserDto> responseObserver) {
        responseObserver.onNext(getUserApplication().searchUser(request));
        responseObserver.onCompleted();
    }
}
