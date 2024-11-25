package com.sample.question_service.advice;

public class BusinessException extends Exception{
    public BusinessException(String message) {
    	super(message);
    }
}
