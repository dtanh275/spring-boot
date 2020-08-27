package com.donkey.spring.springconfiguration.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.donkey.spring.springconfiguration.annotation.config.SpringAnnotationConfig;
import com.donkey.spring.springconfiguration.annotation.interfaces.MessageRenderer;

public class SpringAnnotationApp {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);

        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        System.out.println(renderer.render());

    }
}
