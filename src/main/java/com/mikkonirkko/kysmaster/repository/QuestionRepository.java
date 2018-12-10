package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	List<Question> findByCategory(Category category);

	List<Question> findAll();

	Question findByQuestionid(Long questionid);
}
