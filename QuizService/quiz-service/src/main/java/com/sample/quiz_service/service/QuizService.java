package com.sample.quiz_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.quiz_service.dao.QuizRepo;
import com.sample.quiz_service.feign.QuixInterface;
import com.sample.quiz_service.model.QuestionWrapper;
import com.sample.quiz_service.model.Quiz;
import com.sample.quiz_service.model.Response;



@Service
public class QuizService {
	
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuixInterface quizIface;
	
	
	public ResponseEntity<String> createQuiz(String category,int nNum,String title){
		List<Long> questions = quizIface.generateQuestionForQuiz(category, nNum).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(long id) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Long> questions = quiz.getQuestions();
		ResponseEntity<List<QuestionWrapper>> quesWrapList = quizIface.getQuestions(questions);
		return quesWrapList;
	}

	public ResponseEntity<Integer> getScore(long id, List<Response> response) {
		ResponseEntity<Integer> score = quizIface.getScore(response);
		return score;
	}

}
