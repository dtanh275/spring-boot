package com.donkey.spring.springconfiguration.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.donkey.spring.springconfiguration.context.interfaces.MessageRenderer;

public class SpringXmlConfiguration {
    public static void main(String... args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "config/app-context.xml");

        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        System.out.println(renderer.render());

    }
}
