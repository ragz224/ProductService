package com.raghu.productservice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    private RestTemplate restTemplate;
//    private String url = "http://localhost:8080/Auth/validate";



    private RestTemplateBuilder restTemplateBuilder;


//    @Autowired
    public TokenValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Optional<JwtData> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.getForEntity(url, JwtData.class);
        return  null;
    }
}
