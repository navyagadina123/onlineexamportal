package com.cg.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.questionservice.exception.NoProperDataException;
import com.cg.questionservice.exception.QuestionNotFoundException;
import com.cg.questionservice.model.Question;
import com.cg.questionservice.repository.QuestionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@Override
	public List<Question> getAllQuestions() throws QuestionNotFoundException {
		List<Question> questions =new ArrayList<>();
		questions =questionRepository.findAll();
		try {
		if(questions.size()==0){
			throw new QuestionNotFoundException("Question is empty");
		}
		}catch(QuestionNotFoundException e)
		{
			log.error(e.getMessage());
		}
	return questions;
	}
	
	@Override
	public Question addQuestion(@RequestBody Question question) throws NoProperDataException {
		log.info("start");
		if(question!=null) 
		{
			questionRepository.save(question);
			log.debug("question added");
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return question;	
		}
	
	
//	
	@Override
	public Question getQuestionById(int id) throws QuestionNotFoundException {
		return questionRepository.findById(id)
				.orElseThrow(()-> new  QuestionNotFoundException("question Not Found"+id));

		
	}
	


	
	@Override
	public Question updateQuestion(Question question,Integer id) throws QuestionNotFoundException {
		Question questions=questionRepository.findById(id).orElseThrow(()-> new QuestionNotFoundException("question not "+id));
    	questions.setId(questions.getId());
    	questions.setOptionOne(question.getOptionOne());
    	questions.setOptionTwo(question.getOptionTwo());
	    questions.setOptionThree(question.getOptionThree());
	    questions.setOptionFour(question.getOptionFour());
		
		final Question  updatedQuestion = questionRepository.save(questions);
		return updatedQuestion;
	}

	
	@Override
	public String deleteQuestion(Integer id) throws QuestionNotFoundException {
        log.info("Start delete");
        var isRemoved =questionRepository.findById(id);
        if(isRemoved.isPresent())
        {
            questionRepository.deleteById(id);
            log.debug("deleted successfully {}",isRemoved.get());
        }
        else
        {
            throw new QuestionNotFoundException("Id Not Available");
        }
        log.info(" deleted End");
        return " deleted successfully";
    }
}
