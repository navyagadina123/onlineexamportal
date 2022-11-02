package com.cg.questionservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.questionservice.exception.NoProperDataException;
import com.cg.questionservice.exception.QuestionNotFoundException;
import com.cg.questionservice.model.Question;
import com.cg.questionservice.service.QuestionServiceImpl;
import com.cg.questionservice.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins ="*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionServiceImpl questionServiceimpl;

	@Autowired
	private SequenceGeneratorService service;
	
	@GetMapping("/allquestions") 
	public ResponseEntity <List<Question>> getAllQuestions() throws QuestionNotFoundException
	{
		
		List<Question> questions=questionServiceimpl.getAllQuestions();
		log.info("starting  of get mapping");
	
		if(questions.size()>0) {
			log.debug("questions are {}"
					+ questions);
		 return new  ResponseEntity<>(questions,HttpStatus.OK); 
		}
		else {
			log.debug(" no questions found ");
			 return new  ResponseEntity<>(questions,HttpStatus.NO_CONTENT); 
		}
		
		
	}
	
	@GetMapping("/questions/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable  Integer id)
	throws QuestionNotFoundException{
		
		Question questions= questionServiceimpl.getQuestionById(id);
		if(questions!=null){
		  return ResponseEntity.ok().body(questions);
		}
		  else {
			return new   ResponseEntity(questions,HttpStatus.NOT_FOUND);
		  }

	}
		
	
	@PostMapping("/addquestions") 
	public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question questions)  throws NoProperDataException
		
		{
			
			if(questions!=null) 
			{
				
				questions.setId(service.getSequenceNumberForQuestion(Question.SEQUENCE_NAME));
				questionServiceimpl.addQuestion(questions);
				log.error("added question");
				return new ResponseEntity<>(questions,HttpStatus.CREATED);
				
			}
			else
			{
//				throw new NoProperDataException("Please fill fields");
				return new ResponseEntity<>(questions,HttpStatus.NO_CONTENT);
				
			}
			
		}

	@DeleteMapping(path="/questions/{id}")
	public ResponseEntity<String> deletePlant( @PathVariable int id) throws QuestionNotFoundException {

	        Question questions=questionServiceimpl.getQuestionById(id);
	        if(questions.getId()==id)
	        
	        {
	        try {
	            questionServiceimpl.deleteQuestion(id);
	            return ResponseEntity.ok(" deleted operation done ");
	        } catch (QuestionNotFoundException e) {
	            log.error(e.getMessage());
	        }
	        
	        }

	        log.info("id not found");
            return ResponseEntity.ok(" Id not found");

}

@PutMapping("/updateQuestion/{id}")
public Question updateQuestion(@RequestBody Question questions,@PathVariable Integer id) throws QuestionNotFoundException {
	Question question = questionServiceimpl.updateQuestion(questions, id);
	return question;
}
	
}