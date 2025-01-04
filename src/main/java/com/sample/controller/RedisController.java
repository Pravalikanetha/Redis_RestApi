package com.sample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.impl.RedisService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class RedisController {
	
	public RedisService redisService;
	public RedisController(RedisService redisService) {
		super();
		this.redisService = redisService;
	}

	@PostMapping("/right")
    public String addRight(@RequestBody String value) {
        redisService.rightAdd(value);
        return "right add into list";
    }

	@PostMapping("/left")
    public String addLeft(@RequestBody String value) {
        redisService.leftAdd(value);
        return "left add into list";
    } 
	
	@GetMapping
	public List<String> loadedAllKeys(){
		List<String> list = redisService.getAllValuesFromList();
		log.info("List of rules:{}",list);
		return list;
	}
	
	@PostMapping("/delete")
	public String reload() {
        redisService.delete();
        return "list got deleted";
    }
	
	@PostMapping("/hash/{key}")
	public void addHashEntries(@PathVariable String key, @RequestBody String value) {
		redisService.addValueInHash(key, value);
	}
}
