package com.sample.Dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.sample.model.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PaymentDao {

	private final JdbcTemplate jdbcTemplate;
    private final Gson gson = new Gson();

    public PaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int savePayment(PaymentRequest paymentRequest) {
    	
    	String transactionRequestJson = gson.toJson(paymentRequest);
        log.info("Payment request data: {}", transactionRequestJson);

        String sql = "INSERT INTO payment (userId, transactionRequest) VALUES (?, ?)";
        log.info("Inserting into the data: userId={}, transactionRequest={}", paymentRequest.getUser().getId(), transactionRequestJson);

        return jdbcTemplate.update(sql, paymentRequest.getUser().getId(), transactionRequestJson);
    }
    
}