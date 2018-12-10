package com.mikkonirkko.kysmaster.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.model.Report;
import com.mikkonirkko.kysmaster.repository.QuestionRepository;
import com.mikkonirkko.kysmaster.repository.ReportRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ReportRepository reportRepository;

	public Question getRandomQuestion() {//Fetch a random question from the entire database.
		List<Question> questions = questionRepository.findAll();
		int idx = (int) (Math.random() * questions.size());
		return questions.get(idx);
	}

	public void report(Long reason, Long questionId) {//Manage a report on bad question.
		Question question = questionRepository.findByQuestionid(questionId);
		Date date = new Date();
		Boolean falseb = new Boolean(false);
		Report rep = new Report(question, reason, falseb, date);
		reportRepository.save(rep);
		System.out.println(rep);
	}
}
