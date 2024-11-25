package com.sample.question_service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sample.question_service.dao.QuestionRepo;
import com.sample.question_service.model.Question;
import com.sample.question_service.model.QuestionWrapper;
import com.sample.question_service.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	CacheManager cacheManager;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Value("{$spring.mail.username}")
	private String fromMail;

	public ResponseEntity<List<Question>> getAllQuestions() {
		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
	}

	@Cacheable(value = "categoryWise-cache", key = "#category")
	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		System.out.println("entered getQuestionByCategory method");
		return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
	}

	@CachePut(value = "categoryWise-cache", key = "#question.category")
	public ResponseEntity<Question> saveQuestion(Question question) {
		System.out.println("entered saveQuestion method");
		questionRepo.save(question);
		return new ResponseEntity<>(question, HttpStatus.CREATED);

	}

	public ResponseEntity<String> saveQuestions(List<Question> question) {
		questionRepo.saveAll(question);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<Long>> generateQuestionForQuiz(String categoryName, int numOfQuestions) {
		List<Long> questions = questionRepo.findRandomQuestionByCategory(categoryName, numOfQuestions);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Long> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for (Long id : questionIds) {
			questions.add(questionRepo.findById(id).get());
		}
		for (Question question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.CREATED);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int rightAnser = 0;
		for (Response response : responses) {
			Question question = questionRepo.findById(response.getId()).get();
			if (response.getAnswer().equals(question.getRightAnswer())) {
				rightAnser++;
			}
		}
		return new ResponseEntity<>(rightAnser, HttpStatus.OK);
	}

	public Cache getCacheValue(String name) {
		return cacheManager.getCache(name);
	}

	public void sentMail(String to, String subject, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(text);
		mail.setFrom(fromMail);
		mail.setSentDate(new Date());
		mailSender.send(mail);
		
	}

}
