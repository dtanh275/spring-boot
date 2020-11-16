package com.donkey.springboot.session.springredissession;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringRedisSessionApp.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@Slf4j
public class SpringRedisSessionTest {

    private String baseUrl = "http://localhost:8080";

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSessionId() {
        var url = String.format("%s/%s", baseUrl, "session");

        var firstResponse = given().get(url);
        var firstSessionId = firstResponse.getBody().print();

        var cookie = firstResponse.getCookie("SESSION");
        var secondSessionId = given().cookie("SESSION", cookie).get(url).getBody().print();

        assertEquals(firstSessionId, secondSessionId);

    }

    @Test
    public void testBeanIdentification() {
        var url = String.format("%s/%s", baseUrl, "identify");

        var firstResponse = given().get(url);
        var firstId = firstResponse.jsonPath().getLong("id");

        var cookie = firstResponse.getCookie("SESSION");
        var secondId = given().cookie("SESSION", cookie).get(url).jsonPath().getLong("id");

        var thirdId = given().get(url).jsonPath().getLong("id");

        assertEquals(firstId, secondId);
        assertNotEquals(firstId, thirdId);
    }

    @Test
    public void testRedisSession() {
        var url = String.format("%s/%s", baseUrl, "identify");

        var firstResponse = given().get(url);
        var firstId = firstResponse.jsonPath().getLong("id");

        var sessionId = firstResponse.jsonPath().getString("sessionId");
        redisTemplate.delete(String.format("spring:session:sessions:%s", sessionId));

        var cookie = firstResponse.getCookie("SESSION");
        var secondId = given().cookie("SESSION", cookie).get(url).jsonPath().getLong("id");

        assertNotEquals(firstId, secondId);
    }
}
