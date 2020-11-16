package com.donkey.springboot.session.springredissession;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SessionContext implements Serializable {
    private Long id;
    private String sessionId;
}
