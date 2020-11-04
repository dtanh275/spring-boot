package com.donkey.springboot.springbootcache.keygenerator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchoolOfWolfService {

    public static final String CACHE_NAME = "witcher_cache";

    public static String getCacheKey(Witcher witcher){
        return "witcher-" + witcher.getId();
    }

    @Cacheable(cacheNames = CACHE_NAME)
    public String train(Witcher witcher) {
        log.info("Enter `@Cacheable` method");
        return witcher + " is training";
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "T(com.donkey.springboot.springbootcache.keygenerator.SchoolOfWolfService).getCacheKey(#witcher)")
    public String select(Witcher witcher) {
        log.info("Enter `@Cacheable` method");
        return "Select " + witcher;
    }
}
