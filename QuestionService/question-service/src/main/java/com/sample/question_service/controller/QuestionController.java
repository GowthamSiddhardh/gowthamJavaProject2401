package com.sample.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.question_service.model.EmailRequest;
import com.sample.question_service.model.Question;
import com.sample.question_service.model.QuestionWrapper;
import com.sample.question_service.model.Response;
import com.sample.question_service.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	/**
	 * Another way @RequestMapping(value = "/allquestions",method = RequestMethod.GET)
	 */
	
	@Autowired
	QuestionService questionService;
	
	/**
	 * We can get all the questions by this method{getAllQuestions}
	 */
	@GetMapping("/allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	/**
	 * We can get all the questions according the category by this method{getQuestionByCategory}
	 */
	@GetMapping("/category/{cat}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("cat") String category) {
		return questionService.getQuestionByCategory(category);
	}
	
	/**
	 * We can add only one question by this method{addQuestion}
	 */
	@PostMapping("/addquestion")
	public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question questions) {
		return questionService.saveQuestion(questions);
	}
	
	/**
	 * We can add multiple questions by this method{addQuestions}
	 */
	@PostMapping("/addquestions")
	public ResponseEntity<String> addQuestions(@Valid @RequestBody List<Question> questions) {
		return questionService.saveQuestions(questions);
	}
	
	/**
	 * We can generate the questions for quiz by this method{generateQuestion}
	 */
	@GetMapping("/generate")
	public ResponseEntity<List<Long>> generateQuestionForQuiz(@RequestParam String categoryName,@RequestParam int numOfQuestions){
		return questionService.generateQuestionForQuiz(categoryName,numOfQuestions);
	}
	
	@PostMapping("getquestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Long> questionIds){
		return questionService.getQuestions(questionIds);
	}
	
	
	@PostMapping("/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
	
	/**
	 * We can get cache value by this method{getCacheValue}
	 * cacheName = "categoryWise-cache"
	 */
	@GetMapping("/cache")
	public Cache getCacheValue(@RequestParam("name") String cacheName) {
		return questionService.getCacheValue(cacheName);
	}
	
	@PostMapping("sentmail")
    public String sentMail(@RequestBody EmailRequest emailRequest) {
    	questionService.sentMail(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getText());
    	return "Email sent successfully";
    }

}
