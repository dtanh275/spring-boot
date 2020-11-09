package com.donkey.springboot.springrediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisCacheApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringRedisCacheApp.class, args);
    }
}
