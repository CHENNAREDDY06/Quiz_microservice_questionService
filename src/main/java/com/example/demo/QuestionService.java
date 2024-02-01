package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST );
		
	}
	
	
	public ResponseEntity<List<Question> >getQuestionsByCategory(String cat) {
		try {
			
		
		return new ResponseEntity<>(questionDao.findByCategory(cat), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<String> addQuestion(Question q) {
		try {
			
		  questionDao.save(q);
		  return new ResponseEntity<>(" Sucess", HttpStatus.CREATED);
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(" Unable to upload", HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<String> deleteQuestion(int id) {
		
		try {
			questionDao.deleteById(id);
			return new ResponseEntity<>("Deleted sucessfully", HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<>("Unable to delete", HttpStatus.BAD_REQUEST);
	}


	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String cat, int nQ) {
		// TODO Auto-generated method stub
	      List<Integer> questions=questionDao.findRandomQuestionsIdByCategory(cat, nQ);
		 
		  return new ResponseEntity<>(questions, HttpStatus.OK);
		 
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> qw=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
	
		for(Integer id: questionIds) {
		
		questions.add(questionDao.findById(id).get());
			
		}
		for (Question q: questions) {
			
			QuestionWrapper qws=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			
			qw.add(qws);
		}
		
		return new ResponseEntity<>(qw, HttpStatus.OK);
	}


	public ResponseEntity<Integer> getScore(List<Response> response) {
	
	     
	       Integer right =0;
	       
	       for( Response responses: response) {
	    	    Optional<Question> q=questionDao.findById(responses.getId());
	   
	    	 if (responses.getResponse().equals(q.get().getRightAnswer())) {
	    		 right++;
	    		
	    	 }
	    		
	}
	
   return new ResponseEntity<>(right, HttpStatus.OK);
}
}
