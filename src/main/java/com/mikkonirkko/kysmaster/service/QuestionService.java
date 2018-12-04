package com.mikkonirkko.kysmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikkonirkko.kysmaster.model.Answer;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.repository.AnswerRepository;
import com.mikkonirkko.kysmaster.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public Question getRandomQuestion() {
		List<Question> questions = questionRepository.findAll();
		int idx = (int)(Math.random() * questions.size());
		return questions.get(idx);
	}
}
