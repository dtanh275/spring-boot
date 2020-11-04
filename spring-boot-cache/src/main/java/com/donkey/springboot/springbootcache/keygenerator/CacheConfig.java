package com.donkey.springboot.springbootcache.keygenerator;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableCaching
@ComponentScan
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) ->
                target.getClass().getSimpleName()
                + "_" + method.getName()
                + "_" + StringUtils.arrayToDelimitedString(params, "_");
    }
}
