package org.myddd.java.distributed;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.junit.jupiter.api.*;
import org.myddd.domain.InstanceFactory;
import org.myddd.ioc.spring.SpringInstanceProvider;
import org.myddd.java.distributed.api.DistributedIdApplicationGrpc;
import org.myddd.java.distributed.application.DistributedIdApplicationGrpcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootTest(classes = Application.class)
public class DistributedIdApplicationGrpcImplTest {

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
        server = NettyServerBuilder.forPort(PORT).addService(new DistributedIdApplicationGrpcImpl()).build();
        server.start();

        managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", PORT).usePlaintext().build();
    }

    @AfterAll
    public static void afterAll(){
        server.shutdown();
    }

    @Test
    void distributeId(){
        var distributedIdApplicationStub = DistributedIdApplicationGrpc.newBlockingStub(managedChannel);
        Assertions.assertNotNull(distributedIdApplicationStub.distributedId(Empty.getDefaultInstance()));
    }

    @Test
    void hostIp(){
        var distributedIdApplicationStub = DistributedIdApplicationGrpc.newBlockingStub(managedChannel);
        Assertions.assertNotNull(distributedIdApplicationStub.hostIp(Empty.getDefaultInstance()));
    }
}
