package com.cg.ss.controller;

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

import com.cg.ss.entity.Subject;
import com.cg.ss.exception.NoProperDataException;
import com.cg.ss.exception.SubjectNotFoundException;
import com.cg.ss.service.SequenceGeneratorService;
import com.cg.ss.service.SubjectServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class SubjectController {

	@Autowired
	private SubjectServiceImpl subjectserviceimpl;

	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/allsubjects") // users/admin
	// include preAuthorize include here also call preauthorize method here
	public ResponseEntity<List<Subject>> getAllSubjects() throws SubjectNotFoundException {
		List<Subject> subject = subjectserviceimpl.getAllSubjects();
		if (subject.size() > 0) {
			log.debug("Subjects are {}" + subject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		}
		return new ResponseEntity<>(subject, HttpStatus.NO_CONTENT);
	}

	// admin/users
	@GetMapping("/getsubject/{id}")
	public ResponseEntity<Subject> getSubjectById(@Valid @PathVariable Integer id) throws SubjectNotFoundException {
		Subject subject = subjectserviceimpl.getSubjectById(id);
		if (subject != null) {
			return ResponseEntity.ok().body(subject);
		} else {
			return new ResponseEntity<Subject>(subject, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addsubject") // only admin
	public ResponseEntity<Subject> addSubject(@RequestBody @Validated Subject subject) throws NoProperDataException {
		subject.setId(service.getSequenceNumberForSubject(Subject.SEQUENCE_NAME));
		return new ResponseEntity<>(subjectserviceimpl.addSubject(subject), HttpStatus.CREATED);
	}

	@PutMapping("/updatesubject/{id}") // admin only @PutMapping("/updateproduct/{id}")
	public ResponseEntity<Subject> updateSubject(@Validated @RequestBody Subject subject, @PathVariable Integer id)
			throws SubjectNotFoundException {
		return ResponseEntity.ok(subjectserviceimpl.updateSubject(subject, id));
	}

	@DeleteMapping("/deletesubject/{id}") // only delete
	public ResponseEntity<String> deleteSubject(@Validated @PathVariable Integer id) throws SubjectNotFoundException {
		subjectserviceimpl.deleteSubject(id);
		return ResponseEntity.ok("The Subject " + id + " deleted successfully");
	}
}
