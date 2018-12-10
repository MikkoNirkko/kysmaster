package com.mikkonirkko.kysmaster.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	public Long questionid;
	
	@Column(name = "question_title")
	@NotNull
	private String title;
	
	
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	@Column
    @ElementCollection(targetClass=Answer.class)
	public List<Answer> answers;
	
	
	public Question() {
		this.title="";
		this.category=null;
		this.answers=null;
	}
	
	public Question(String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [questionid=" + questionid + ", title=" + title + ", category=" + category + "]";
	}
	
	
	
}
