package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mikkonirkko.kysmaster.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}