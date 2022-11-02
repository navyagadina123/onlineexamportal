package com.cg.oep.service;

import java.util.List;

import com.cg.oep.entity.Exam;
import com.cg.oep.exception.ExamNotFoundException;
import com.cg.oep.exception.NoProperDataException;

public interface ExamService {

	public List<Exam> getAllExams() throws  ExamNotFoundException;
	public Exam addExam(Exam exam)  throws NoProperDataException;
	public Exam updateExam(Exam exam ,Integer id)  throws  ExamNotFoundException;
	public String deleteExam(Integer id) throws  ExamNotFoundException;
	public Exam getExamById(Integer id) throws ExamNotFoundException;
}
