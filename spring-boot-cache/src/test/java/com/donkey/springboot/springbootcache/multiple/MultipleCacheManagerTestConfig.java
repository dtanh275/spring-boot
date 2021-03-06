package com.donkey.springboot.springbootcache.multiple;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.donkey.springboot.springbootcache.mutiple.MultipleCacheManagerConfig;
import com.donkey.springboot.springbootcache.mutiple.WitcherSchoolService;

@SpringBootConfiguration
@EnableAutoConfiguration
public class MultipleCacheManagerTestConfig extends MultipleCacheManagerConfig {

    @Bean
    public WitcherSchoolService witcherSchoolService() {
        return new WitcherSchoolService();
    }
}
