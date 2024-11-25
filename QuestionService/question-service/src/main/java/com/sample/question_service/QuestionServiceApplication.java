package com.sample.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuestionServiceApplication {

	public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
