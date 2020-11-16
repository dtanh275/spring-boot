package com.donkey.springboot.session.springredissession;

import java.time.Instant;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleSessionController {

    @Autowired
    private SessionContext sessionContext;

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/identify")
    public IdentificationBean getIdentification(HttpSession session) {
        if (sessionContext.getId() == null) {
            sessionContext.setId(Instant.now().toEpochMilli());
            sessionContext.setSessionId(session.getId());
        }
        var bean = new IdentificationBean();
        BeanUtils.copyProperties(sessionContext, bean);

        return bean;
    }
}
