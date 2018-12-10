package com.mikkonirkko.kysmaster.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
	Result findByResultId(Long resultId);
}
