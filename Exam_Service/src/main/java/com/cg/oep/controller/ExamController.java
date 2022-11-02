package com.cg.oep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oep.entity.Exam;
import com.cg.oep.exception.ExamNotFoundException;
import com.cg.oep.exception.NoProperDataException;
import com.cg.oep.service.ExamServiceImpl;
import com.cg.oep.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ExamController {

	
	@Autowired
	private ExamServiceImpl examserviceimpl;

	@Autowired
	private SequenceGeneratorService service;


	@GetMapping("/allexams")  //users/admin
	//include preAuthorize include here also  call preauthorize method here
	public ResponseEntity<List<Exam>> getAllExams() throws ExamNotFoundException {
		List<Exam> exam =examserviceimpl.getAllExams();
		if(exam.size()>0)
		{
			log.debug("Exams are {}"+ exam);
		 return new  ResponseEntity<>(exam,HttpStatus.OK);
		}
		return new  ResponseEntity<>(exam,HttpStatus.NO_CONTENT);
	}

	//admin/users
	  @GetMapping("/getexam/{id}")
	  public ResponseEntity<Exam> getExamById(@Valid @PathVariable Integer id) throws ExamNotFoundException {
		 Exam exam= examserviceimpl.getExamById(id);
		 if(exam!=null)
		 {
			 return ResponseEntity.ok().body(exam);
		 }
		 else {
				return new   ResponseEntity<Exam>(exam,HttpStatus.NOT_FOUND);
			  }
	  }

	@PostMapping("/addexam")  //only admin
	public ResponseEntity<Exam> addExam(@RequestBody @Validated Exam exam) throws NoProperDataException {
		exam.setId(service.getSequenceNumberForExam(Exam.SEQUENCE_NAME));
		 return new ResponseEntity<>(examserviceimpl.addExam(exam),HttpStatus.CREATED);
	}

	@PutMapping("/updateexam/{id}")  //admin only @PutMapping("/updateproduct/{id}")
	public ResponseEntity<Exam> updateExam(@Validated @RequestBody Exam exam,@PathVariable Integer id) throws ExamNotFoundException {
		 return ResponseEntity.ok(examserviceimpl.updateExam(exam,id));
	}

	@DeleteMapping("/deleteexam/{id}")  //only delete
	public ResponseEntity<String> deleteExam(@Validated @PathVariable Integer id) throws ExamNotFoundException {
		 examserviceimpl.deleteExam(id);
		 return ResponseEntity.ok("The Exam "+id+" deleted successfully");
	}
}
