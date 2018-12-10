package com.mikkonirkko.kysmaster.repository;

import org.springframework.data.repository.CrudRepository;

import com.mikkonirkko.kysmaster.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}