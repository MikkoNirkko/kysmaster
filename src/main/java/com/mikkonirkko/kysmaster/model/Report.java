package com.mikkonirkko.kysmaster.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "report_id")
	private Long reportId;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	@Column(name="report_reason")
	private Long reason;
	
	@Column(name="report_reasontext")
	private String reasonText;
	
	@Column(name="report_seen")
	private Boolean seen;
	
	@Column(name="report_date")
	private Date reportDate;
	
	public Report() {
		this.question=null;
		this.reason=null;
		this.reasonText="";
		this.seen=false;
		this.reportDate=null;
	}
	
	public Report(Question question, Long reason, Boolean seen, Date reportDate) {
		this.question=question;
		this.reason=reason;
		this.seen=seen;
		this.reportDate=reportDate;
		int reasonvalue = Integer.valueOf(reason.toString());
		switch(reasonvalue) {
		case 1:
			this.reasonText="OFFENSIVE";
			break;
		case 2:
			this.reasonText="IMPOSSIBLE";
			break;
		case 3:
			this.reasonText="OBVIOUS";
			break;
		}
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getReason() {
		return reason;
	}

	public void setReason(Long reason) {
		this.reason = reason;
	}
	
	

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", question=" + question + ", reason=" + reason + ", reasonText="
				+ reasonText + ", seen=" + seen + ", reportDate=" + reportDate + "]";
	}

	
	
	
	
}
