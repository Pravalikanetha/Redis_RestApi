package com.sample.service.impl;

import org.springframework.stereotype.Service;

import com.sample.service.interfaces.Animals;

@Service
public class DogActions implements Animals {

	@Override
	public String Actions() {
		return "DOG BARK";
	}

}
