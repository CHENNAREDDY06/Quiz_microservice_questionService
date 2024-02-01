package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
		
	}
	
	@GetMapping("category/{cat}")
	public ResponseEntity<List<Question> > getQuestionsByCategory(@PathVariable String cat){
		 
		return questionService.getQuestionsByCategory(cat);
		
	}
	
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question q) {
		return questionService.addQuestion(q);
		
	}
	
	@DeleteMapping("deleteQuestion/{Id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id){
		return questionService.deleteQuestion(id);
	}
	
	@PostMapping("create/{cat}/{nQ}")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@PathVariable String cat, @PathVariable int nQ){
		return  questionService.getQuestionsForQuiz(cat,nQ);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds ){
		return questionService.getQuestionsFromId(questionIds);
		
	}
	 @PostMapping("getScore")
	 public ResponseEntity<Integer> getScore(@RequestBody  List<Response> response){
		return questionService.getScore(response);
		 
	 }


}
