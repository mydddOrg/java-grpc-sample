package org.myddd.java.rest.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.myddd.java.rest.AbstractControllerTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class HostControllerTest extends AbstractControllerTest {

    @Test
    void hostIp(){
        ResponseEntity<String> response = restTemplate.exchange(baseUrl()+"/v1/host", HttpMethod.GET,createEmptyHttpEntity(),String.class);
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

}
