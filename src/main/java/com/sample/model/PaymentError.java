package com.sample.model;

import lombok.Data;

@Data
public class PaymentError {
	private String errorCode;
	private String errorMessage;
	
	public PaymentError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
