package com.cg.questionservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.questionservice.model.Question;

@Repository
public interface QuestionRepository  extends MongoRepository<Question,Integer>{

}
