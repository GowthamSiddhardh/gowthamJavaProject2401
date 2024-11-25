package com.sample.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.quiz_service.model.QuestionWrapper;
import com.sample.quiz_service.model.QuizDto;
import com.sample.quiz_service.model.Response;
import com.sample.quiz_service.service.QuizService;



@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/createquiz")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quiz) {
		return quizService.createQuiz(quiz.getCategory(), quiz.getNumQuestions(), quiz.getTitle());
	}
	
	@GetMapping("getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable long id){
		return quizService.getQuizQuestion(id);
	}
	
	@GetMapping("/submit/{id}")
	public ResponseEntity<Integer> getScore(@PathVariable long id,@RequestBody List<Response> list){
		return quizService.getScore(id,list);
	}
	
	@GetMapping("/message")
	public String message(){
		return "Welcome";
	}

}
