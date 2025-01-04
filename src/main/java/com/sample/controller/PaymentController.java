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

import com.sample.model.PaymentRequest;
import com.sample.service.impl.PaymentService;
import com.sample.service.interfaces.Animals;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pays")
@Slf4j
public class PaymentController {
	
	private  PaymentService paymentService;
	private Animals animals;
	public PaymentController(PaymentService paymentService,Animals animals) {
		this.paymentService = paymentService;
		this.animals=animals;
	}
	
	@PostMapping
	public ResponseEntity<String> createPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("Payment posted successfully", paymentRequest);
		String pay = paymentService.addToData(paymentRequest);
		log.info("Returned to controller:{}" , pay);
		return new ResponseEntity<>(pay, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/validation-values")
    public ResponseEntity<List<String>> getValidationValues() {
        List<String> matchingValues = paymentService.getMatchingValidationValues();
        return ResponseEntity.ok(matchingValues);
    }
	
	@GetMapping("/{name}")
    public ResponseEntity<String> aboutAnimal(@PathVariable String name) {
        String actions = paymentService.getAllAction(name);
        return ResponseEntity.ok(actions);
    }
}























