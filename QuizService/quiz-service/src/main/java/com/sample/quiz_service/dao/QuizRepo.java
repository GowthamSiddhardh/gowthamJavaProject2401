package com.sample.quiz_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.quiz_service.model.Quiz;



@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long>{

}
