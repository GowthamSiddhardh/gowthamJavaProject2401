package com.sample.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.quiz_service.model.QuestionWrapper;
import com.sample.quiz_service.model.Response;



@FeignClient("QUESTION-SERVICE")
public interface QuixInterface {
  
	@GetMapping("question/generate")
	public ResponseEntity<List<Long>> generateQuestionForQuiz(@RequestParam String categoryName,@RequestParam int numOfQuestions);
	
	@PostMapping("question/getquestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds);
	
	
	@PostMapping("question/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
