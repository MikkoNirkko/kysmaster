package com.mikkonirkko.kysmaster.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikkonirkko.kysmaster.model.Answer;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.repository.AnswerRepository;
import com.mikkonirkko.kysmaster.service.QuestionService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RESTController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/question") //Return a random question as JSON
	public Question fetchQuestion() {
		Question q = questionService.getRandomQuestion();
		List<Answer> answers = answerRepository.findByQuestionId(q.getQuestionid());
		q.setAnswers(answers);
		return q;
	}
}
