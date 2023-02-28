package com.example.demo.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErroMessage> customerNotFoundExceptionhandler(CustomException ex){
		ErroMessage errorMessage = new ErroMessage();
		errorMessage.setCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setMessage(ex.getMessage());	
		return ResponseEntity.badRequest().body(errorMessage);
	}
	
}
