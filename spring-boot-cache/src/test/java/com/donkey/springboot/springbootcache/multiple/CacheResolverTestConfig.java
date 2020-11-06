package com.donkey.springboot.springbootcache.multiple;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.donkey.springboot.springbootcache.mutiple.CacheResolverConfig;
import com.donkey.springboot.springbootcache.mutiple.WitcherSchoolService;

@SpringBootConfiguration
@EnableAutoConfiguration
public class CacheResolverTestConfig extends CacheResolverConfig {

    @Bean
    public WitcherSchoolService witcherSchoolService() {
        return new WitcherSchoolService();
    }
}
