package com.mikkonirkko.kysmaster.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikkonirkko.kysmaster.model.Answer;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.model.QuestionForm;
import com.mikkonirkko.kysmaster.model.Report;
import com.mikkonirkko.kysmaster.model.Result;
import com.mikkonirkko.kysmaster.model.User;
import com.mikkonirkko.kysmaster.repository.AnswerRepository;
import com.mikkonirkko.kysmaster.repository.CategoryRepository;
import com.mikkonirkko.kysmaster.repository.QuestionRepository;
import com.mikkonirkko.kysmaster.repository.ReportRepository;
import com.mikkonirkko.kysmaster.repository.ResultRepository;
import com.mikkonirkko.kysmaster.repository.UserRepository;
import com.mikkonirkko.kysmaster.service.AnswerService;
import com.mikkonirkko.kysmaster.service.QuestionService;
import com.mikkonirkko.kysmaster.service.ResultService;

@Controller
public class DefaultController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ResultService resultService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		List<User> users= (List<User>) userRepository.findAll();
		if(users.size()==0) {
			return "redirect:/signup";
		}else {
		int idx = (int) (Math.random() * 1000000);
		Long quizKey = new Long(idx);
		request.getSession().setAttribute("quizKey", quizKey);
		return "index";
		}
	}
	
	@Secured("ADMIN")
	@RequestMapping("/adminpage")
	public String adminPage(Model model) {
		Boolean no = new Boolean(false);
		model.addAttribute("reportAmount", reportRepository.countBySeen(no));
		List<Report> reports = reportRepository.findBySeen(no);
		for(Report report:reports) {
			Question que = report.getQuestion();
			List<Answer> answers = answerRepository.findByQuestionId(que.getQuestionid());
			que.setAnswers(answers);
			report.setQuestion(que);
		}
		model.addAttribute("reports", reports);
		return "adminpage";
	}
	
	@Secured("ADMIN")
	@Transactional
	@RequestMapping("/deletequestion")
	public String deleteQuestion(@RequestParam Long questionid) {
		Question que = questionRepository.findByQuestionid(questionid);
		reportRepository.deleteByQuestion(que);
		answerRepository.deleteByQuestionId(questionid);
		questionRepository.delete(que);
		return "redirect:/adminpage";
	}
	
	@Secured("ADMIN")
	@Transactional
	@RequestMapping("/dismissreport")
	public String dismissReport(@RequestParam Long reportId) {
		Report report = reportRepository.findByReportId(reportId);
		report.setSeen(new Boolean(true));
		reportRepository.save(report);
		return "redirect:/adminpage";
	}

	@RequestMapping("/new/")
	public String addQuestion(Model model, QuestionForm qform) {
		model.addAttribute("qform", qform);
		model.addAttribute("categories", categoryRepository.findAll());
		return "addquestion";
	}
	
	@RequestMapping("/login")
	public String adminLogin(Model model) {
		return "login";
	}
	
	@RequestMapping("/leaderboard")
	public String leaderboard(Model model) {
		List<Result> results = (List<Result>) resultRepository.findAll();
		List<Result> top10 = resultService.pickTen(results);
		model.addAttribute("results", top10);
		return "leaderboard";
	}

	@RequestMapping("/savequestion")
	public String saveQuestion(QuestionForm qform) {
		Question q = new Question(qform.getTitle(), categoryRepository.findByCategoryId(qform.getCategoryId()));
		questionRepository.save(q);
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
		model.addAttribute("reported", 0);
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
				model.addAttribute("reported", 0);
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
	
	@RequestMapping("/report")
	public String report(Model model, @RequestParam("winCount") Long winCount, @RequestParam("quizKey")Long quizKey,@RequestParam("reason") Long reason,@RequestParam("questionId") Long questionId,
			HttpServletRequest request) {
				questionService.report(reason, questionId);
				Question q = questionService.getRandomQuestion();
				model.addAttribute("question", q);
				model.addAttribute("answers", answerService.fetchAnswers(q.getQuestionid()));
				model.addAttribute("winCount", winCount);
				model.addAttribute("quizKey", quizKey);
				model.addAttribute("reported", 1);
				return "game";
	}
	
	@RequestMapping("/submit")
	public String submit(@RequestParam("name") String name, @RequestParam("winCount") Long winCount) {
		Date cur = new Date();
		Result res = new Result(name, winCount, cur);
		resultRepository.save(res);
		return "redirect:/leaderboard";
	}
	
	

}
