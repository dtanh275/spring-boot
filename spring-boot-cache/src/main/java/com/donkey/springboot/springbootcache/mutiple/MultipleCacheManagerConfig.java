package com.donkey.springboot.springbootcache.mutiple;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class MultipleCacheManagerConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cfm = new CaffeineCacheManager("schoolOfWolf");
        cfm.setCaffeine(Caffeine.newBuilder().maximumSize(100));
        return cfm;
    }

    @Bean
    public CacheManager secondaryCacheManager() {
        return new ConcurrentMapCacheManager("schoolOfSnake");
    }
}
