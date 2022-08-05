package org.myddd.java.rest;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.myddd.domain.IDGenerate;
import org.myddd.java.distributed.api.DistributedIdApplicationGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    private ManagedChannel managedChannel = ManagedChannelBuilder.forTarget("127.0.0.1:8081").usePlaintext().build();

    @Bean
    IDGenerate idGenerate(){
        return new IDGenerate() {
            @Override
            public Long nextId() {
                var distributedApplicationStub = DistributedIdApplicationGrpc.newBlockingStub(managedChannel);
                return distributedApplicationStub.distributedId(Empty.newBuilder().build()).getValue();
            }
        };
    }
}
