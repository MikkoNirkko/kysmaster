package com.mikkonirkko.kysmaster.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findByCategoryId(Long categoryId);
}
