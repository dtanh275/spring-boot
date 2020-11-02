package com.donkey.springboot.springbootcache.custom;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SimpleCacheCustomizerApp.class)
@Slf4j
public class SimpleCacheCustomizeTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCustomCache() {
        var cacheNames = cacheManager.getCacheNames();
        assertTrue(cacheNames.contains("myCustomCache"));
    }

}
