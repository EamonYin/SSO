package com.sso.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

//    将RestTemplate注入到Spring容器中
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
