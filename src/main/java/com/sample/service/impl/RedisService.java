package com.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ListOperations<String, String> listOperations;
	private final HashOperations<String, String, String> hashOperations;
	private static final String KEY = "Key";
	private static final String PREFIX_KEY = "prefixkey:"; 

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

	public void addValueInHash(String key, String value) {
		hashOperations.put(PREFIX_KEY, key, value);
	}
	
	public String getValueFromHash(String key) {
		return hashOperations.get(PREFIX_KEY, key);
	}
	
	public Map<String, String> getAllEntriesFromHash() {
		return hashOperations.entries(PREFIX_KEY);
	}


}
