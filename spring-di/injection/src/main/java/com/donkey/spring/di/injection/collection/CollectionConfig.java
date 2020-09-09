package com.donkey.spring.di.injection.collection;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.injection.collection")
public class CollectionConfig {

    @Bean("allBoys")
    public List<NameHolder> allBoys() {
        return List.of(ron(), harry());
    }

    @Bean("allGirls")
    public List<NameHolder> allGirls() {
        return List.of(hermione(), ginny());
    }

    @Bean("ron")
    @Order(4)
    public NameHolder ron() {
        return new NameHolder("Ron");
    }

    @Bean("harry")
    @Order(2)
    public NameHolder harry() {
        return new NameHolder("Harry");
    }

    @Bean("hermione")
    @Order(3)
    public NameHolder hermione() {
        return new NameHolder("Hermione");
    }

    @Bean("ginny")
    @Order(1)
    public NameHolder ginny() {
        return new NameHolder("Ginny");
    }
}
