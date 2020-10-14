package com.donkey.springboot.dockersample.springexternaltomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "Spring boot with external tomcat") String name) {
        return String.format("%s, %s!!!", "Hello", name);
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
