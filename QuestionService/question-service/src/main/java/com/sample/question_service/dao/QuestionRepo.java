package com.sample.question_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sample.question_service.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long>{
	
	List<Question> findByCategory(String category);
	
	@Query(value = "select q.id from questions_table q where q.category=:category order by random() limit :nque",nativeQuery =true)
	List<Long> findRandomQuestionByCategory(String category,int nque);

}
