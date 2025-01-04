package com.sample.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sample.service.interfaces.Animals;

@Service
@Primary
public class CatActions implements Animals {

	@Override
	public String Actions() {
		return "MEOW MEOW";
	}

}
