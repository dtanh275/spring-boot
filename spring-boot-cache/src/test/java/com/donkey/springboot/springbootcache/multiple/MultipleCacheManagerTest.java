package com.donkey.springboot.springbootcache.multiple;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import com.donkey.springboot.springbootcache.mutiple.WitcherSchoolService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MultipleCacheManagerTestConfig.class)
@Slf4j
public class MultipleCacheManagerTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CacheManager secondaryCacheManager;
    @Autowired
    private WitcherSchoolService witcherSchoolService;

    @Test
    public void multipleCacheManagerTest() {
        witcherSchoolService.recruitWolfStudent("Geralt");
        witcherSchoolService.recruitSnakeStudent("Letho");

        assertNotNull(cacheManager.getCache("schoolOfWolf").get("Geralt"));
        assertNotNull(secondaryCacheManager.getCache("schoolOfSnake").get("Letho"));
    }
}
