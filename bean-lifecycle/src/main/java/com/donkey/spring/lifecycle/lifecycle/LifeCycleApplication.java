package com.donkey.spring.lifecycle.lifecycle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LifeCycleApplication {

    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();

        ctx.getBean("lifeCycleBean");

        ctx.destroy();
    }
}
