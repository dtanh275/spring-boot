package com.donkey.springboot.springbootcache.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingAnnotationApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CachingAnnotationApp.class, args);
    }
}
