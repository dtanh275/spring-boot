package com.donkey.springboot.springsimplesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringSimpleSecurityApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringSimpleSecurityApp.class, args);
    }
}
