package com.donkey.springboot.springbootcache.mutiple;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WitcherSchoolService {

    @Cacheable(cacheNames = "schoolOfWolf")
    public String recruitWolfStudent(String name) {
        log.info("Welcome to School of Wolf");
        return "Hello, " + name;
    }

    @Cacheable(cacheNames = "schoolOfSnake", cacheManager = "secondaryCacheManager")
    public String recruitSnakeStudent(String name) {
        log.info("Welcome to School of Snake");
        return "Hello, " + name;
    }

    @Cacheable
    public String trainWolf(String name) {
        return String.format("Train %s to become a Wolf Witcher", name);
    }

    @Cacheable
    public String trainSnake(String name) {
        return String.format("Train %s to become a Snake Witcher", name);
    }

}
