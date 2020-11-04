package com.donkey.springboot.springbootcache.keygenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Witcher {
    private long id;
    private String name;

    @Override
    public String toString() {
        return String.format("[id=%d, name=%s]", id, name);
    }
}
