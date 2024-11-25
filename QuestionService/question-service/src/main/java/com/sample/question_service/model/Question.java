package com.sample.question_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "questions_table")
public class Question {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "question_title")
	@NotNull(message = "QuestionTitle should not be empty")
    private String questionTitle;
	
	@Column(name = "option1",nullable = false,length = 200)
    private String option1;
	
	@Column(name = "option2",nullable = false,length = 200)
    private String option2;
	
	@Column(name = "option3",nullable = false,length = 200)
    private String option3;
	
	@Column(name = "option4",nullable = false,length = 200)
    private String option4;
	
	@Column(name = "right_answer",nullable = false,length = 200)
    private String rightAnswer;
	
	@Column(name = "difficulty_level",nullable = false,length = 20)
    private String difficultyLevel;
	
	@Column(name = "category")
	@Size(max = 20, message = "Category Name Should not be greater than 20")
    private String category;
	
	public Question() {
		super();
	}
	
	public Question(String questionTitle, String option1, String option2, String option3, String option4,
			String rightAnswer, String difficultyLevel, String category) {
		super();
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.rightAnswer = rightAnswer;
		this.difficultyLevel = difficultyLevel;
		this.category = category;
	}

	public Question(long id, String questionTitle, String option1, String option2, String option3, String option4,
			String rightAnswer, String difficultyLevel, String category) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.rightAnswer = rightAnswer;
		this.difficultyLevel = difficultyLevel;
		this.category = category;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
	@Override
	public String toString() {
		return "Question [id=" + id + ", questionTitle=" + questionTitle + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", rightAnswer=" + rightAnswer
				+ ", difficultyLevel=" + difficultyLevel + ", category=" + category + "]";
	}

}
