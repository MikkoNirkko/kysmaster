package com.mikkonirkko.kysmaster.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.model.QuestionForm;
import com.mikkonirkko.kysmaster.repository.CategoryRepository;
import com.mikkonirkko.kysmaster.repository.QuestionRepository;
import com.mikkonirkko.kysmaster.service.AnswerService;
import com.mikkonirkko.kysmaster.service.QuestionService;

@Controller
public class DefaultController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		int idx = (int) (Math.random() * 1000000);
		Long quizKey = new Long(idx);
		request.getSession().setAttribute("quizKey", quizKey);
		return "index";
	}

	@RequestMapping("/new/")
	public String addQuestion(Model model, QuestionForm qform) {
		model.addAttribute("qform", qform);
		model.addAttribute("categories", categoryRepository.findAll());
		return "addquestion";
	}

	@RequestMapping("/savequestion")
	public String saveQuestion(QuestionForm qform) {
		Question q = new Question(qform.getTitle(), categoryRepository.findByCategoryId(qform.getCategoryId()),
				qform.getSources());
		questionRepository.save(q);
		System.out.println(q);
		answerService.SaveAnswers(q.getQuestionid(), qform.getCorrectAnswer(), qform.getFalseAnswer1(),
				qform.getFalseAnswer2(), qform.getFalseAnswer3());
		return "redirect:/";
	}

	@RequestMapping("/play")
	public String play(Model model, @RequestParam("winCount") Long winCount, HttpServletRequest request) {
		Question q = questionService.getRandomQuestion();
		int idx = (int) (Math.random() * 1000000);
		Long quizKey = new Long(idx);
		request.getSession().setAttribute("quizKey", quizKey);
		model.addAttribute("question", q);
		model.addAttribute("answers", answerService.fetchAnswers(q.getQuestionid()));
		model.addAttribute("winCount", winCount);
		model.addAttribute("quizKey", quizKey);
		request.getSession().setAttribute("active", 1);
		return "game";
	}


	@RequestMapping("/nextquestion")
	public String play(Model model, @RequestParam("winCount") Long winCount, @RequestParam("correct") Boolean correct,@RequestParam("quizKey")Long quizKey,
			HttpServletRequest request) {
		int active = (int) request.getSession().getAttribute("active");
		Long quizKey2 = (Long)request.getSession().getAttribute("quizKey");
		int key = quizKey.compareTo(quizKey2);
		if (active == 1 && key==0) {
			if (correct) {
				winCount++;
				Question q = questionService.getRandomQuestion();
				model.addAttribute("question", q);
				model.addAttribute("answers", answerService.fetchAnswers(q.getQuestionid()));
				model.addAttribute("winCount", winCount);
				model.addAttribute("quizKey", quizKey);
				return "game";
			} else {
				model.addAttribute("winCount", winCount);
				request.getSession().setAttribute("active", 0);
				return "results";
			}
		} else {
			return "redirect:/";
		}
	}

}
