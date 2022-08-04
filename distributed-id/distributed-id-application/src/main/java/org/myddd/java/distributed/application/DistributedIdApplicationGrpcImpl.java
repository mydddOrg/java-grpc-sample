package org.myddd.java.distributed.application;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.myddd.domain.InstanceFactory;
import org.myddd.java.distributed.api.DistributedIdApplication;
import org.myddd.java.distributed.api.DistributedIdApplicationGrpc;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Objects;

public class DistributedIdApplicationGrpcImpl extends DistributedIdApplicationGrpc.DistributedIdApplicationImplBase {


    private static DistributedIdApplication distributedIdApplication;

    private static DistributedIdApplication getDistributedIdApplication(){
        if(Objects.isNull(distributedIdApplication)){
            distributedIdApplication = InstanceFactory.getInstance(DistributedIdApplication.class);
        }
        return distributedIdApplication;
    }

    @Override
    public void distributedId(Empty request, StreamObserver<Int64Value> responseObserver) {
        responseObserver.onNext(getDistributedIdApplication().distributedId(Empty.getDefaultInstance()));
        responseObserver.onCompleted();
    }

    @Override
    public void hostIp(Empty request, StreamObserver<StringValue> responseObserver) {
        try {
            var hostIp = StringValue.of(Inet4Address.getLocalHost().getHostAddress());
            responseObserver.onNext(hostIp);
        } catch (UnknownHostException e) {
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
    }
}
