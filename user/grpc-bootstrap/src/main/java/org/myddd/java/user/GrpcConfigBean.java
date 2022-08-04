package org.myddd.java.user;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.myddd.domain.IDGenerate;
import org.myddd.java.distributed.api.DistributedIdApplicationGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfigBean {

    //在分布式部署中，每个服务都可以使用同一个端口，可以固定
    private static ManagedChannel managedChannel = ManagedChannelBuilder
            .forTarget("dns:///distribute:8081")
            .defaultLoadBalancingPolicy("round_robin")
            .usePlaintext()
            .build();

    @Bean
    IDGenerate idGenerate(){
        return () -> {
            var distributedStub = DistributedIdApplicationGrpc.newBlockingStub(managedChannel);
            return distributedStub.distributedId(Empty.newBuilder().build()).getValue();
        };
    }

}
