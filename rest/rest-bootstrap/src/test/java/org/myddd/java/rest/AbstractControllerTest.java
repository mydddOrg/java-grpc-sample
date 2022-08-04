package org.myddd.java.rest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.myddd.domain.InstanceFactory;
import org.myddd.ioc.spring.SpringInstanceProvider;
import org.myddd.java.application.UserApplicationGrpcImpl;
import org.myddd.java.distributed.application.DistributedIdApplicationGrpcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest {
    @Value("${server.servlet.context-path:/}")
    public String contextPath;

    @Value("${local.server.port}")
    public int port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Inject
    public ApplicationContext applicationContext;


    private final static int PORT = 8081;

    private static Server server;

    protected static ManagedChannel managedChannel;

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

    @BeforeEach
    public void beforeClass(){
        InstanceFactory.setInstanceProvider(SpringInstanceProvider.createInstance(applicationContext));
    }

    public String baseUrl(){
        return "http://localhost:"+port+contextPath;
    }

    protected HttpEntity<String> createHttpEntityFromString(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        return entity;
    }

    protected HttpEntity createEmptyHttpEntity(){
        return new HttpEntity<>(new HttpHeaders());
    }

    protected String randomString() {
        return UUID.randomUUID().toString().replace("-","");
    }
}

