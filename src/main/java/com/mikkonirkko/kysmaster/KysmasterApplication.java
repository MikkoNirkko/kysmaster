package com.mikkonirkko.kysmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	
}



