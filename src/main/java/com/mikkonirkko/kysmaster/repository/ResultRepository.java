package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.model.Report;
import com.mikkonirkko.kysmaster.model.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long>{
	Result findByResultId(Long resultId);
}
