package com.sample;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	//created a bean here because it can be use across the project 
	//instead of creating new object in every class
    @Bean
    Random getRandom() {
    	logger.info("Bean is created");
		return new Random();
	}
    
    @Bean
    Gson getgson() {
    	return new Gson();
    }
}
