package com.sample.question_service.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   
	@ExceptionHandler(Exception.class)
	public String handleCommonException(Exception ex){
		return  "An Exception occured "+ ex.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleBusinessException(MethodArgumentNotValidException ex){
		Map<String,String> response = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(msg -> {
			response.put(msg.getField(), msg.getDefaultMessage());
		});
		return response;
	}
	
}
