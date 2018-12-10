package com.mikkonirkko.kysmaster.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "result_id")
	private Long resultId;

	@Column(name = "result_name")
	private String name;

	@Column(name = "result_score")
	private Long score;

	@Column(name = "result_date")
	private Date date;

	public Result() {
		this.name = "";
		this.score = null;
		this.date = null;
	}

	public Result(String name, Long score, Date date) {
		this.name = name;
		this.score = score;
		this.date = date;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", name=" + name + ", score=" + score + ", date=" + date + "]";
	}

}
