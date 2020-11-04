package com.donkey.springboot.springbootcache.keygenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class KeyGeneratorTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SchoolOfWolfService schoolOfWolfService;

    @Test
    public void keyGeneratorTest() {
        schoolOfWolfService.train(new Witcher(1L, "Geralt"));
        var cacheValue = cacheManager.getCache(SchoolOfWolfService.CACHE_NAME)
                                     .get("SchoolOfWolfService_train_[id=1, name=Geralt]").get();
        assertEquals("[id=1, name=Geralt] is training", cacheValue);
    }

    @Test
    public void keySpELTest() {
        log.info(schoolOfWolfService.select(new Witcher(1L, "Ciri")));
        log.info(schoolOfWolfService.select(new Witcher(1L, "Geralt")));
    }

}
