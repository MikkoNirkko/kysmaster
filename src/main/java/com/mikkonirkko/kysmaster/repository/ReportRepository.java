package com.mikkonirkko.kysmaster.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mikkonirkko.kysmaster.model.Category;
import com.mikkonirkko.kysmaster.model.Question;
import com.mikkonirkko.kysmaster.model.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>{
	Report findByReportId(Long reportId);
	Long countBySeen(Boolean seen);
	List<Report> findBySeen(Boolean seen);
	Long countByQuestion(Question question);
	void deleteByQuestion(Question question);
}
