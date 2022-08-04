package org.myddd.java.user;

import io.grpc.netty.NettyServerBuilder;
import org.myddd.domain.InstanceFactory;
import org.myddd.ioc.spring.SpringInstanceProvider;
import org.myddd.java.application.UserApplicationGrpcImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan(basePackages = {"org.myddd","org.myddd.java.user"})
@EntityScan(basePackages = {"org.myddd","org.myddd.java.user"})
public class Application {

    @Value("${grpc.port:8081}")
    private int port;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        InstanceFactory.setInstanceProvider(SpringInstanceProvider.createInstance(ctx));

        var application = InstanceFactory.getInstance(Application.class);
        application.startGrpcService();
    }

    private void startGrpcService(){
        var server = NettyServerBuilder
                .forPort(port)
                .maxConnectionAge(10, TimeUnit.SECONDS)
                .maxConnectionAgeGrace(10,TimeUnit.SECONDS)
                .addService(new UserApplicationGrpcImpl())
                .build();

        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
