package com.donkey.spring.springconfiguration.hello;

import com.donkey.spring.springconfiguration.hello.interfaces.MessageRenderer;
import com.donkey.spring.springconfiguration.hello.message.MessageFactory;

public class MessageFactoryApplication {

    public static void main(String... args) {
        MessageFactory factory = MessageFactory.getInstance();

        MessageRenderer renderer = factory.getBean("class.renderer", MessageRenderer.class);
        System.out.println(renderer.render());
    }
}
