package com.donkey.springboot.springbootcache.mutiple;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
@ComponentScan
public class MultipleCacheManagerConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cfm = new CaffeineCacheManager("school_of_wolf", "schoolOfWolf");
        cfm.setCaffeine(Caffeine.newBuilder().maximumSize(100));
        return cfm;
    }

    @Bean
    public CacheManager secondaryCacheManager() {
        return new ConcurrentMapCacheManager("school_of_snake", "schoolOfSnake");
    }

    @Bean
    public CacheResolver cacheResolver() {
        return context -> {
            if ("trainWolf".equals(context.getMethod().getName())) {
                return Arrays.asList(cacheManager().getCache("school_of_wolf"));
            } else if ("trainSnake".equals(context.getMethod().getName())) {
                return Arrays.asList(secondaryCacheManager().getCache("school_of_snake"));
            } else {
                return null;
            }
        };
    }
}
