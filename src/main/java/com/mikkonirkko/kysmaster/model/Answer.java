package com.mikkonirkko.kysmaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long answerid;

	@NotNull
	@Column(name = "content")
	private String content;

	@NotNull
	@Column(name = "correct")
	private Boolean correct;

	@NotNull
	@Column(name = "question_id")
	private Long questionId;

	public Answer() {
		this.content = "";
		this.correct = false;
		this.questionId = null;
	}

	public Answer(String content, Boolean correct, Long questionId) {
		this.content = content;
		this.correct = correct;
		this.questionId = questionId;
	}

	public Long getAnswerid() {
		return answerid;
	}

	public void setAnswerid(Long answerid) {
		this.answerid = answerid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Answer [answerid=" + answerid + ", content=" + content + ", correct=" + correct + ", questionId="
				+ questionId + "]";
	}

}
