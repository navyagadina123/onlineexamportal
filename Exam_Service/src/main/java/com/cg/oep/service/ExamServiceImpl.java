package com.cg.oep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oep.entity.Exam;
import com.cg.oep.exception.ExamNotFoundException;
import com.cg.oep.exception.NoProperDataException;
import com.cg.oep.repository.ExamRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExamServiceImpl implements ExamService {

	
	//changed in line 30
		@Autowired
		private ExamRepository examrepository;

	@Override
	public List<Exam> getAllExams() throws ExamNotFoundException {
		// TODO Auto-generated method stub
		List<Exam> exams;
		exams=examrepository.findAll();
		log.debug("the exams are {}",exams);//log messages should be here only
		try {
			if(exams.size()==0)
			{
				throw new ExamNotFoundException("Exams not found....!!");
			}
		}catch(ExamNotFoundException e)
		{
			log.error(e.getMessage());
		}
		return exams;
	}

	@Override
	public Exam addExam(Exam exam) throws NoProperDataException {
		
		Exam bean=examrepository.save(exam);
		log.debug("Added Exam is  {}",bean);//log messages should be here only after businnes is method
		try {
		if(bean.getExam_name().isEmpty()||bean.getDesc().isEmpty()||bean.getDate().isEmpty()||bean.getLevel().isEmpty()||bean.getMarks().isEmpty()||bean.getPassMarks().isEmpty()||bean.getTotalQuestion().isEmpty())
		{
			throw new NoProperDataException(" Please fill all fields ");
		}
		}catch(NoProperDataException np)
		{
			log.error(np.getMessage());
		}
		return bean;
	}

	@Override
	public Exam updateExam(Exam exam, Integer id) throws ExamNotFoundException {
		// TODO Auto-generated method stub
		Exam exam1=examrepository.findById(id).orElseThrow(()-> new  ExamNotFoundException("No Exam Availble wth this id"));
		log.debug("updated  exam is {}",exam1);
		
		exam1.setExam_name(exam.getExam_name());
		exam1.setDesc(exam.getDesc());
		exam1.setDate(exam.getDate());
		exam1.setLevel(exam.getLevel());
		exam1.setMarks(exam.getMarks());
		exam1.setPassMarks(exam.getPassMarks());
		exam1.setTotalQuestion(exam.getTotalQuestion());
		
		return  examrepository.save(exam1);
	}

	@Override
	public String deleteExam(Integer id) throws ExamNotFoundException {
		// TODO Auto-generated method stub
		Optional<Exam> exam = examrepository.findById(id);
		log.debug("the exam is {}",exam);
		if(exam.isPresent())
		{
			examrepository.deleteById(id);
			log.debug("deleted succesfully {}",exam.get());
		}
		else
		{
			throw new ExamNotFoundException("Exam not available to delete on given id");
		}
		log.info("end");
		return "deleted";
	}

	@Override
	public Exam getExamById(Integer id) throws ExamNotFoundException {
		// TODO Auto-generated method stub
		Exam exam=examrepository.findById(id).orElseThrow(()-> new ExamNotFoundException("Exam Not Found with id "+id));
		log.debug("the exam deleted  is {}",exam);
		return exam;
		//getById id takes only id has input (it will not take object Product product)
	}


}
