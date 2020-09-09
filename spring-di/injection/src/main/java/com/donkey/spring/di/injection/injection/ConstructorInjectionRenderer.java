package com.donkey.spring.di.injection.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("constructorRenderer")
public class ConstructorInjectionRenderer {

    private String message;

    @Autowired
    public ConstructorInjectionRenderer(String message) {
        this.message = message;
    }

    public String render() {
        return message;
    }

}
