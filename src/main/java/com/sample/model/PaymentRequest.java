package com.sample.model;

import lombok.Data;

@Data
public class PaymentRequest {
	private User user;
	private Payment payment;
}