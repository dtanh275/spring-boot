package com.donkey.springboot.dockersample.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApiApplication.class, args);
    }
}
