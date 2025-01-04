package com.sample.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Payment {
    private double amount;
    private String currency;

    
}
