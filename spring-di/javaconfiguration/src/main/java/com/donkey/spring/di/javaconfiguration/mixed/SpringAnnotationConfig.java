package com.donkey.spring.di.javaconfiguration.mixed;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:app-context-xml.xml"})
public class SpringAnnotationConfig {
}
