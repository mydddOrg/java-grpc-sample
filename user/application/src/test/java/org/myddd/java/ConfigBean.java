package org.myddd.java;

import com.google.protobuf.Empty;
import org.myddd.domain.IDGenerate;
import org.myddd.java.distributed.api.DistributedIdApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class ConfigBean {

    @Inject
    private DistributedIdApplication distributedIdApplication;

    @Bean
    IDGenerate idGenerate(){
        return new IDGenerate() {
            @Override
            public Long nextId() {
                return distributedIdApplication.distributedId(Empty.newBuilder().build()).getValue();
            }
        };
    }
}
