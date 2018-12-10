package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findByQuestionId(Long questionId);

	void deleteByQuestionId(Long questionId);
}
