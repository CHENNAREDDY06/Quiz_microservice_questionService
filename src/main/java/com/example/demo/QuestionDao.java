package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>  {

	List<Question> findByCategory(String cat);

	@Query(value = "SELECT q.id FROM question q WHERE q.category = :cat ORDER BY RAND() LIMIT :nQ", nativeQuery = true)
	List<Integer> findRandomQuestionsIdByCategory(String cat,  int nQ);
}
	
  


