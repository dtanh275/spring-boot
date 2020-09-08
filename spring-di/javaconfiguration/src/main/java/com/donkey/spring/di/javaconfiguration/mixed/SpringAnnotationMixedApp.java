package com.donkey.spring.di.javaconfiguration.mixed;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.donkey.spring.springconfiguration.context.interfaces.MessageRenderer;

public class SpringAnnotationMixedApp {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);

        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        System.out.println(renderer.render());

    }
}
