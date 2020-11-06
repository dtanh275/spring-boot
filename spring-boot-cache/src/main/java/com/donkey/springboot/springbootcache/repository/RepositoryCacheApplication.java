package com.donkey.springboot.springbootcache.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RepositoryCacheApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RepositoryCacheApplication.class, args);
    }
}
