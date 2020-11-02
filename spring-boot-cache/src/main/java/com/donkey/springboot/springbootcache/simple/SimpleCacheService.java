package com.donkey.springboot.springbootcache.simple;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleCacheService {

    @Cacheable(cacheNames = "myCache")
    public String cacheThis() {
        log.info("Returning NOT from cache!");
        return "this Is it";
    }
}
