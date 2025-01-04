package com.sample.constant;

public enum ErrorEnum {
	INVALID_AMOUNT("10001", "Amount cannot be negative, please correct the amount and try again"),
	THE_ERROR("50001", "basically the list is empty");

	private String errorCode;
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	private ErrorEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	
}
