package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class TestController {

    final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private Environment env;

    @GetMapping("")
    public String index() {
        String app = env.getProperty("stage");
        String result = Optional.ofNullable(app).orElse("test");
        logger.debug(result);
        return result ;
    }

    @GetMapping("test")
    public String test() {
        return "test" ;
    }

    @GetMapping("chain")
    public String chain() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/test";
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);
        return response.toString();
    }




}
