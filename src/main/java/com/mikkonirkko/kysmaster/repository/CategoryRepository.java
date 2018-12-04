package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.model.Question;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	Category findByCategoryId(Long categoryId);
}
