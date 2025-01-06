package com.sample.exception.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.model.PaymentError;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<PaymentError> handleValidationException(ValidationException ex) {
	    log.error("ValidationException occurred. Code: {}, Message: {}, Status: {}",
	        ex.getErrorCode(), ex.getErrorMessage(), ex.getHttpStatus());
	    
	    PaymentError paymentError = new PaymentError(ex.getErrorCode(), ex.getErrorMessage());
	    log.info("Returning error response: {}", paymentError);
	    return new ResponseEntity<>(paymentError, ex.getHttpStatus());
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<PaymentError> handleGenericException(Exception ex) {
		log.error("Unexpected exception occurred", ex);
		PaymentError paymentError = new PaymentError("ERR999", "An unexpected error occurred");
		return new ResponseEntity<>(paymentError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
