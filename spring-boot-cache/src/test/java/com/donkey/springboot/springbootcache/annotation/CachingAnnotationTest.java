package com.donkey.springboot.springbootcache.annotation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CachingAnnotationApp.class)
@Slf4j
public class CachingAnnotationTest {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void cachPutTest() {
        hotelService.putName("Harry");
        log.info(hotelService.hello("Harry"));

        log.info(hotelService.hello("Ron"));
        log.info(hotelService.hello("Ron"));
    }

    @Test
    public void cacheableTest() {
        log.info(hotelService.hello("Harry"));
        hotelService.putName("Harry");
        log.info(hotelService.hello("Harry"));
    }

    @Test
    public void cacheEvictTest() {
        hotelService.putName("Harry");
        assertNotNull(cacheManager.getCache(HotelService.CACHE_NAME).get("Harry"));

        hotelService.evictName("Harry");
        assertNull(cacheManager.getCache(HotelService.CACHE_NAME).get("Harry"));
    }

    @Test
    public void cacheEvictAllTest() {
        hotelService.putName("Harry");
        hotelService.putName("Ron");

        hotelService.evictAll();

       assertNull(cacheManager.getCache(HotelService.CACHE_NAME).get("Harry"));
       assertNull(cacheManager.getCache(HotelService.CACHE_NAME).get("Ron"));
    }
}
