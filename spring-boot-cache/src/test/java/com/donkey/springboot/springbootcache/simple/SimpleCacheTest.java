package com.donkey.springboot.springbootcache.simple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SimpleCacheApplication.class)
@Slf4j
public class SimpleCacheTest {

    @Autowired
    private SimpleCacheService simpleCacheService;

    @Test
    public void testSimpleCache() {
        String firstString = simpleCacheService.cacheThis();
        log.info("First {}", firstString);

        String secondString = simpleCacheService.cacheThis();
        log.info("Second {}", secondString);
    }

}
