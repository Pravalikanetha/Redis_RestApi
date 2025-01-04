package com.sample.constant;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ValidationEnum {
	PRAVALIKA("pravalika","This is pravalika"),
	PANDU("pandu","This is pandu");
	
	private static final Map<String, ValidationEnum> NAME_TO_ENUM_MAP = new HashMap<>();
	
	public String key;
	public String name;
	
	ValidationEnum(String key,String Value){
		this.key=key;
		this.name=Value;
	}
	
	static {
		for(ValidationEnum validate : values()) {
			NAME_TO_ENUM_MAP.put(validate.key, validate);
		}
	}
	
	public static String getValidationValue(String key) {
		ValidationEnum validationEnum = NAME_TO_ENUM_MAP.get(key);
		if (validationEnum == null) {
			log.info("Validator not found: {}", validationEnum);
			return null;
		}
		return validationEnum.name;
	}
}

























