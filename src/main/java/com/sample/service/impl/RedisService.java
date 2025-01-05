package com.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ListOperations<String, String> listOperations;
	private final HashOperations<String, String, String> hashOperations;
	private static final String KEY = "Key";
	private static final String PREFIX_KEY = "prefixkey"; 

	public RedisService(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.listOperations = redisTemplate.opsForList();
		this.hashOperations = redisTemplate.opsForHash();
	}

	public void rightAdd(String value) {
		listOperations.rightPush(KEY, value);
	}

	public void leftAdd(String value) {
		listOperations.leftPush(KEY, value);
	}

	public List<String> getAllValuesFromList() {
		return listOperations.range(KEY, 0, -1);
	}

	public void delete() {
		redisTemplate.delete(KEY);
	}
	
	
	//hash map
	public void addValueInHash(String hashkey, String value) {
	    log.info("Adding key: {}| with value: {}| to hash: {}", 
	    		hashkey, value, PREFIX_KEY);
	    hashOperations.put(PREFIX_KEY, hashkey, value);
	}

	public String getValueFromHash(String hashkey) {
		return hashOperations.get(PREFIX_KEY, hashkey);
	}
	
	public Map<String, String> getAllEntriesFromHash() {
		return hashOperations.entries(PREFIX_KEY);
	}


}
