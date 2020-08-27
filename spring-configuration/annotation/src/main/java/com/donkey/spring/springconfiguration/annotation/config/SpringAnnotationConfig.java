package com.donkey.spring.springconfiguration.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.donkey.spring.springconfiguration.annotation.interfaces.MessageProvider;
import com.donkey.spring.springconfiguration.annotation.interfaces.MessageRenderer;
import com.donkey.spring.springconfiguration.annotation.message.ConsoleMessageRenderer;
import com.donkey.spring.springconfiguration.annotation.message.HelloSpringProvider;

@Configuration
public class SpringAnnotationConfig {

    @Bean
    public MessageProvider provider() {
        return new HelloSpringProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new ConsoleMessageRenderer();
        renderer.setProvider(provider());

        return renderer;
    }
}
