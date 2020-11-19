package com.donkey.springboot.springsimplesecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @GetMapping("/hellouser")
    public String getUser() {
        return "Hello User";
    }

    @GetMapping("/helloadmin")
    public String getAdmin() {
        return "Hello Admin";
    }
}
