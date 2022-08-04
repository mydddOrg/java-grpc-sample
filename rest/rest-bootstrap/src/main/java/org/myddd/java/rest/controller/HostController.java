package org.myddd.java.rest.controller;

import com.google.protobuf.Empty;
import org.myddd.java.distributed.api.DistributedIdApplicationGrpc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/v1")
public class HostController extends AbstractController{

    @GetMapping("/host")
    ResponseEntity<String> hostIp(){
        var distributedIdApplicationBlockingStub = DistributedIdApplicationGrpc.newBlockingStub(getDistributeManagerChannel());
        var hostIp = distributedIdApplicationBlockingStub.hostIp(Empty.getDefaultInstance());
        return ResponseEntity.ok(hostIp.getValue());
    }
}
