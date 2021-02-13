package dev.thejavaman.springboot.oauth2authorizationserver.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OAuth2AuthorizationServerApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OAuth2AuthorizationServerApp.class, args);
    }
}
