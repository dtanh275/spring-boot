package com.donkey.spring.di.injection.injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringInjectionApp {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringInjectionConfig.class);

        DefaultMessageRenderer defaultRenderer = ctx.getBean("defaultRenderer", DefaultMessageRenderer.class);

        ConstructorInjectionRenderer injectRenderer = ctx.getBean("constructorRenderer", ConstructorInjectionRenderer.class);

        SetterInjectionRenderer setterRenderer = ctx.getBean("setterRenderer", SetterInjectionRenderer.class);

        FieldInjectionRenderer fieldRenderer = ctx.getBean("fieldRenderer", FieldInjectionRenderer.class);

        System.out.println(defaultRenderer.render());
        System.out.println(injectRenderer.render());
        System.out.println(setterRenderer.render());
        System.out.println(fieldRenderer.render());
    }
}
