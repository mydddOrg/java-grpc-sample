package org.myddd.java.rest.controller;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import org.myddd.java.rest.AbstractControllerTest;
import org.myddd.java.rest.vo.UserVO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class UserControllerTest extends AbstractControllerTest {



    @Test
    void createUser(){
        ResponseEntity<UserVO> errorResponse = restTemplate.exchange(baseUrl()+"/v1/users", HttpMethod.POST,createHttpEntityFromString(randomUserJSON()),UserVO.class);
        Assertions.assertTrue(errorResponse.getStatusCode().is2xxSuccessful());
    }


    private String randomUserJSON(){
        var jsonObject = new JsonObject();
        jsonObject.addProperty("userId",randomString());
        jsonObject.addProperty("name",randomString());
        jsonObject.addProperty("phone",randomString());
        jsonObject.addProperty("email",randomString());
        return jsonObject.toString();
    }



}
