package com.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sample.Dao.PaymentDao;
import com.sample.constant.ActionsEnum;
import com.sample.constant.ErrorEnum;
import com.sample.constant.ValidationEnum;
import com.sample.exception.controlleradvice.ValidationException;
import com.sample.model.PaymentRequest;
import com.sample.service.interfaces.Animals;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	private PaymentDao paymentDao;
	private RedisService redisService;
	private ApplicationContext applicationContext;
	public PaymentService(PaymentDao paymentDao, RedisService redisService, ApplicationContext applicationContext) {
		this.paymentDao = paymentDao;
		this.redisService=redisService;
		this.applicationContext = applicationContext;
	}

	public String addToData(PaymentRequest paymentRequest) {
		log.info("adding data of PaymentRequest");
		paymentDao.savePayment(paymentRequest);

		if(paymentRequest.getPayment().getAmount()<0) {
			log.info("Amount can't be lower then 0");
			ValidationException validationException = new ValidationException(
					ErrorEnum.INVALID_AMOUNT.getErrorCode(),
					ErrorEnum.INVALID_AMOUNT.getErrorMessage(),
					HttpStatus.BAD_REQUEST); 
			log.info("Raising Validation Exception",validationException);

			throw validationException;
		}

		log.info("adding data of PaymentRequest:  {}",paymentRequest);
		return "This as saved the data";

	}

	public List<String> getMatchingValidationValues() {
		log.info("Loading the cache list from Redis");
		List<String> list = redisService.getAllValuesFromList();
		log.info("The Redis list: {}", list);

		List<String> matchingValues = new ArrayList<>();
		for (String key : list) {
			String validationValue = ValidationEnum.getValidationValue(key);
			if (validationValue != null) {
				matchingValues.add(validationValue);
			}
		}

		log.info("Matching validation values: {}", matchingValues);

		if (matchingValues.isEmpty()) {
			log.warn("matchingValues is empty:{}",matchingValues);
			throw new ValidationException(
					ErrorEnum.THE_ERROR.getErrorCode(),
					ErrorEnum.THE_ERROR.getErrorMessage(),
					HttpStatus.BAD_REQUEST
					);
		}
		return matchingValues;
	}

	public String getAllAction(String name) {
		try {
			Animals animal = null;
			Class<? extends Animals> validatorClass = ActionsEnum.getActionClassByName(name);
			if(validatorClass!=null) {
				animal = applicationContext.getBean(validatorClass);
			}
			if (animal != null) {
				return animal.Actions();
			}else {
				throw new IllegalStateException("Failed to retrieve the bean for the action class: " + validatorClass.getName());
			} 
		}catch(IllegalStateException e) {
			log.error("this is the Illegal state Exception",e.getMessage());
			return "Error: " + e.getMessage();
		}
		catch(NullPointerException e) {
			log.error("this is the NullPointer Exception:{}",e.getMessage());
			throw new ValidationException(
					ErrorEnum.POINTING_AT_NULL.getErrorCode(),
					ErrorEnum.POINTING_AT_NULL.getErrorMessage()+" " +e.getMessage(),
					HttpStatus.BAD_REQUEST
					);

		}
	}

}









