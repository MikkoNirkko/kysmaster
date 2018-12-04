package com.mikkonirkko.kysmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.repository.CategoryRepository;
import com.mikkonirkko.kysmaster.repository.QuestionRepository;

@SpringBootApplication
public class KysmasterApplication {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(KysmasterApplication.class, args);
	}
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
	    public void run(String...args) throws Exception {
	       Category nat = new Category("Nature");
	       categoryRepository.save(nat);
	       Category pol = new Category("Politics");
	       categoryRepository.save(pol);
	       Category ppl = new Category("People");
	       categoryRepository.save(ppl);
	       Category tch = new Category("Technology");
	       categoryRepository.save(tch);
	       Category spr = new Category("Sports");
	       categoryRepository.save(spr);
	       Category tvs = new Category("Film And Television");
	       categoryRepository.save(tvs);
	       Category oth = new Category("Other");
	       categoryRepository.save(oth);
	    }

	}
}
