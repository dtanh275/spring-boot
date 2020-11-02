package com.donkey.springboot.springbootcache.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.donkey.springboot.springbootcache.simple.SimpleCacheApplication;

@SpringBootApplication
@EnableCaching
public class SimpleCacheCustomizerApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleCacheApplication.class, args);
    }
}
