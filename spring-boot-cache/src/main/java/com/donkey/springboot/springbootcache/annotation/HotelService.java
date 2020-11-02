package com.donkey.springboot.springbootcache.annotation;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HotelService {
    public static final String CACHE_NAME = "customerName";

    @Cacheable(cacheNames = CACHE_NAME)
    public String hello(String name) {
        log.info("Enter the `@Cacheable` method");
        return String.format("Hello, Mr.%s", name);
    }

    @CachePut(cacheNames = CACHE_NAME)
    public String putName(String name) {
        log.info("Enter the `@CachePut` method");
        return String.format("Welcome, Mr.%s", name);
    }

    @CacheEvict(cacheNames = CACHE_NAME)
    public void evictName(String name) {
    }

    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public void evictAll() {
    }
}
