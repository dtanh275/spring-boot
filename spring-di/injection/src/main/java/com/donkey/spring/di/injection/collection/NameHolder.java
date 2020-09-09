package com.donkey.spring.di.injection.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NameHolder {

	private String name;

    public String toString() {
		return "My name is " + name;
	}
}
