package com.sample.exception.controlleradvice;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
	
	private String errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;
}
