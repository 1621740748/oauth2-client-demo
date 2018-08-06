package com.jrj.spingboot.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        String s= restTemplate.getForObject("http://127.0.0.1:8080/product/1",String.class,new Object[0]);
        return s;
    }

}