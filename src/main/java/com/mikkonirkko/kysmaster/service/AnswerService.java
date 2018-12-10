package com.mikkonirkko.kysmaster.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikkonirkko.kysmaster.model.Answer;
import com.mikkonirkko.kysmaster.repository.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	public List<Answer> fetchAnswers(Long questionId) {//To supply a question with its answers.
		List<Answer> answers = answerRepository.findByQuestionId(questionId);
		Collections.shuffle(answers);
		return answers;
	}

	public void SaveAnswers(Long questionId, String ca, String fa1, String fa2, String fa3) {//To save answers associated with created question.
		answerRepository.save(new Answer(ca, true, questionId));
		answerRepository.save(new Answer(fa1, false, questionId));
		answerRepository.save(new Answer(fa2, false, questionId));
		answerRepository.save(new Answer(fa3, false, questionId));
	}

}
