package com.sample.quiz_service.model;

public class Response {
	
	private Long id;
	private String answer;
	
	public Response() {
		super();
	}

	public Response(Long id, String answer) {
		super();
		this.id = id;
		this.answer = answer;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
