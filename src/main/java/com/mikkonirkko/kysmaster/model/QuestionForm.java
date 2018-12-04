package com.mikkonirkko.kysmaster.model;

public class QuestionForm {
	
	private Long categoryId;
	private String title;
	private String correctAnswer;
	private String falseAnswer1;
	private String falseAnswer2;
	private String falseAnswer3;
	private String sources;
	
	public QuestionForm() {
		this.categoryId=null;
		this.title="";
		this.correctAnswer="";
		this.falseAnswer1="";
		this.falseAnswer2="";
		this.falseAnswer3="";
		this.sources="";
	}
	
	public QuestionForm(Long categoryId,String title, String correctAnswer, String falseAnswer1, String falseAnswer2, String falseAnswer3, String sources) {
		this.categoryId=categoryId;
		this.title=title;
		this.correctAnswer=correctAnswer;
		this.falseAnswer1=falseAnswer1;
		this.falseAnswer2=falseAnswer2;
		this.falseAnswer3=falseAnswer3;
		this.sources=sources;
	}

	
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getFalseAnswer1() {
		return falseAnswer1;
	}

	public void setFalseAnswer1(String falseAnswer1) {
		this.falseAnswer1 = falseAnswer1;
	}

	public String getFalseAnswer2() {
		return falseAnswer2;
	}

	public void setFalseAnswer2(String falseAnswer2) {
		this.falseAnswer2 = falseAnswer2;
	}

	public String getFalseAnswer3() {
		return falseAnswer3;
	}

	public void setFalseAnswer3(String falseAnswer3) {
		this.falseAnswer3 = falseAnswer3;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	@Override
	public String toString() {
		return "QuestionForm [categoryId=" + categoryId + ", title=" + title + ", correctAnswer=" + correctAnswer
				+ ", falseAnswer1=" + falseAnswer1 + ", falseAnswer2=" + falseAnswer2 + ", falseAnswer3=" + falseAnswer3
				+ ", sources=" + sources + "]";
	}

	
	
	
}
