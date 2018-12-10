package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mikkonirkko.kysmaster.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findByUsername(String username);
}