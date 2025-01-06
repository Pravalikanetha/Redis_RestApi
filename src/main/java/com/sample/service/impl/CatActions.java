package com.sample.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sample.service.interfaces.Animals;

import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class CatActions implements Animals {

	@Override
	public String Actions() {
		log.info("The CAT class as been invoked");
		return "MEOW MEOW";
	}

	
}
