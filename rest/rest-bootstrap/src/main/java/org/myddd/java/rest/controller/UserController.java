package org.myddd.java.rest.controller;

import org.myddd.java.rest.vo.UserVO;
import org.myddd.java.user.api.UserApplicationGrpc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/v1")
public class UserController extends AbstractController {


    @PostMapping("/users")
    ResponseEntity<UserVO> createUser(@RequestBody UserVO userVO){
        var userApplicationStub = UserApplicationGrpc.newBlockingStub(getManagedChannel());
        var created = userApplicationStub.createUser(userVO.toUserDto());
        return ResponseEntity.ok(UserVO.of(created));
    }
}
