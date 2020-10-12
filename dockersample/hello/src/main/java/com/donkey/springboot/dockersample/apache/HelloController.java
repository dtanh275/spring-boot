package com.donkey.springboot.dockersample.nginx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "Spring boot") String name) {
        return String.format("%s, %s!!!", "Hello", name);
    }
}
