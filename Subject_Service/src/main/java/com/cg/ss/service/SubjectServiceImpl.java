package com.cg.ss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ss.entity.Subject;
import com.cg.ss.exception.NoProperDataException;
import com.cg.ss.exception.SubjectNotFoundException;
import com.cg.ss.repository.SubjectRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectrepository;

	@Override
	public List<Subject> getAllSubjects() throws SubjectNotFoundException {
		// TODO Auto-generated method stub
		List<Subject> subjects;
		subjects = subjectrepository.findAll();
		log.debug("the subjects are {}", subjects);// log messages should be here only
		try {
			if (subjects.size() == 0) {
				throw new SubjectNotFoundException("Subjects not found....!!");
			}
		} catch (SubjectNotFoundException e) {
			log.error(e.getMessage());
	}
		return subjects;
	}

	@Override
	public Subject addSubject(Subject subject) throws NoProperDataException {
		// TODO Auto-generated method stub
		Subject bean = subjectrepository.save(subject);
		log.debug("Added Subject is  {}", bean);// log messages should be here only after businnes is method
		try {
			if (bean.getSub_name().isEmpty()) {
				throw new NoProperDataException(" Please fill all fields ");
			}
		} catch (NoProperDataException np) {
			log.error(np.getMessage());
		}
		return bean;
	}

	@Override
	public Subject updateSubject(Subject subjects, Integer id) throws SubjectNotFoundException {
		// TODO Auto-generated method stub
		Subject subject = subjectrepository.findById(id)
				.orElseThrow(() -> new SubjectNotFoundException("No Subject Availble wth this id"));
		log.debug("updated subject is {}", subject);

		subject.setSub_name(subjects.getSub_name());
		

		return subjectrepository.save(subject);
	}

	@Override
	public String deleteSubject(Integer id) throws SubjectNotFoundException {
		// TODO Auto-generated method stub
		Optional<Subject> subject = subjectrepository.findById(id);
		log.debug("the exam is {}", subject);
		if (subject.isPresent()) {
			subjectrepository.deleteById(id);
			log.debug("deleted succesfully {}", subject.get());
		} else {
			throw new SubjectNotFoundException("Subject not available to delete on given id");
		}
		log.info("end");
		return "deleted";
	}

	@Override
	public Subject getSubjectById(Integer id) throws SubjectNotFoundException {
		// TODO Auto-generated method stub
		Subject subject = subjectrepository.findById(id)
				.orElseThrow(() -> new SubjectNotFoundException("Subject Not Found with id " + id));
		log.debug("the subject deleted  is {}", subject);
		return subject;
		// getById id takes only id has input (it will not take object Product product)
	}

}
