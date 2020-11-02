package com.donkey.springboot.springbootcache.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleCacheApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleCacheApplication.class, args);
    }
}
