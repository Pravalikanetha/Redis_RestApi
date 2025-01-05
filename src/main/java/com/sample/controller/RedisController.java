package com.sample.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return "ok, list got deleted";
    }
	
	@PostMapping("/hash/{key}")
    public ResponseEntity<String> addHashEntry(@PathVariable String key, @RequestBody String value) {
        redisService.addValueInHash(key, value);
        return ResponseEntity.ok("Key-value pair added to hash successfully.");
    }
	
	@GetMapping("/hash/{key}")
    public ResponseEntity<String> getHashValue(@PathVariable String key) {
        String value = redisService.getValueFromHash(key);
        if (value != null) {
            return ResponseEntity.ok(value);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Key not found.");
        }
    }
	
}
