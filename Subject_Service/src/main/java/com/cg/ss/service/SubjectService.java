package com.cg.ss.service;

import java.util.List;

import com.cg.ss.entity.Subject;
import com.cg.ss.exception.NoProperDataException;
import com.cg.ss.exception.SubjectNotFoundException;

public interface SubjectService {

	public List<Subject> getAllSubjects() throws SubjectNotFoundException;
	public Subject addSubject(Subject subject)  throws NoProperDataException;
	public Subject updateSubject(Subject subject ,Integer id)  throws  SubjectNotFoundException;
	public String deleteSubject(Integer id) throws  SubjectNotFoundException;
	public Subject getSubjectById(Integer id) throws SubjectNotFoundException;
}
