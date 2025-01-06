package com.sample.service.impl;

import org.springframework.stereotype.Service;

import com.sample.service.interfaces.Animals;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DogActions implements Animals {

	@Override
	public String Actions() {
		log.info("The dog class as been invoked");
		return "DOG BARK";
	}

}
