package com.donkey.spring.di.injection.injection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("injectSimple")
public class InjectSimple {

    @Value("John Mayer")
    private String name;
    @Value("40")
    private int age;
    @Value("1.92")
    private float height;

    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringInjectionConfig.class);

        InjectSimple injectSimple = ctx.getBean("injectSimple", InjectSimple.class);

        System.out.println(injectSimple);
    }


    public String toString() {
        return "Name: " + name + "\n"
               + "Age: " + age + "\n"
               + "Height: " + height + "\n";
    }
}
