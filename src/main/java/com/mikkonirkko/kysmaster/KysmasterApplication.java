package com.mikkonirkko.kysmaster;

//KysMaster, Mikko Nirkko 2018

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.repository.CategoryRepository;

@SpringBootApplication
public class KysmasterApplication {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(KysmasterApplication.class, args);
	}

	@Component
	public class DataLoader implements CommandLineRunner {

		// If Categories are not found in the database, they will be created

		@Override
		public void run(String... strings) throws Exception {
			Long cat = categoryRepository.count();
			if (cat < 1) {
				Category cat1 = new Category("Science");
				categoryRepository.save(cat1);
				Category cat2 = new Category("Nature");
				categoryRepository.save(cat2);
				Category cat3 = new Category("Sports");
				categoryRepository.save(cat3);
				Category cat4 = new Category("Food");
				categoryRepository.save(cat4);
				Category cat5 = new Category("Other");
				categoryRepository.save(cat5);
			}

		}
	}
}
