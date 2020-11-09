package com.donkey.springboot.springrediscache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringRedisCacheApp.class)
@Slf4j
public class SpringRedisCacheTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HogwartsService hogwartsService;

    @Test
    public void redisCacheTest() {
        hogwartsService.enter("Harry Potter");
        assertEquals("Hello, Harry Potter",
                     redisTemplate.opsForValue().get("hogwarts::Harry Potter"));
    }
}
