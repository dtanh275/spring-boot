package com.donkey.spring.di.javaconfiguration.annotated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotatedApp {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);

        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        System.out.println(renderer.render());

    }
}
