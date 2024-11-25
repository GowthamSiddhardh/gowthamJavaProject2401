package com.sample.quiz_service.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleCommonException(Exception ex){
		return new ResponseEntity<>("An Exception Occurred : "+ ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
