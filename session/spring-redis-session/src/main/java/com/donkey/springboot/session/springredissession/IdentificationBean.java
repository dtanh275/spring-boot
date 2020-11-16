package com.donkey.springboot.session.springredissession;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdentificationBean implements Serializable {
    private Long id;
    private String sessionId;
}
