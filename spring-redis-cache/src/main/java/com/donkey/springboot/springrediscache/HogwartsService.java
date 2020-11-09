package com.donkey.springboot.springrediscache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HogwartsService {

    @Cacheable("hogwarts")
    public String enter(String name) {
        log.info("Welcome to Hogwarts");
        return String.format("Hello, %s", name);
    }
}
