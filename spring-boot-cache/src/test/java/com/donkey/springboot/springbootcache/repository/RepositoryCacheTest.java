package com.donkey.springboot.springbootcache.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RepositoryCacheApplication.class)
@Slf4j
public class RepositoryCacheTest {

    @Autowired
    private WitcherRepository schoolWitcherRepository;

    @Test
    public void jpaCacheTest() {
        var optWicher = schoolWitcherRepository.findById(1L);
        assertTrue(optWicher.isPresent()
                   && "Geralt".equals(optWicher.get().getName())
                   && "Human".equals(optWicher.get().getRace()));

        schoolWitcherRepository.updateRaceById(1L, "Witcher");

        var optDatabase = schoolWitcherRepository.findByIdWithoutCache(1L);
        assertTrue(optDatabase.isPresent()
                   && "Geralt".equals(optDatabase.get().getName())
                   && "Witcher".equals(optDatabase.get().getRace()));

        var optCache = schoolWitcherRepository.findById(1L);
        assertTrue(optCache.isPresent()
                   && "Geralt".equals(optCache.get().getName())
                   && "Human".equals(optCache.get().getRace()));

    }

}
