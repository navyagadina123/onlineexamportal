package com.cg.questionservice.service;

import java.util.List;

import com.cg.questionservice.exception.NoProperDataException;
import com.cg.questionservice.exception.QuestionNotFoundException;
import com.cg.questionservice.model.Question;

public interface QuestionService {
	public  List<Question> getAllQuestions() throws  QuestionNotFoundException;
	public Question getQuestionById( int id) throws QuestionNotFoundException;
	public Question addQuestion(Question question)  throws  NoProperDataException;
	public Question updateQuestion(Question question,Integer id)  throws QuestionNotFoundException;
	public String deleteQuestion( Integer id) throws QuestionNotFoundException;

}
