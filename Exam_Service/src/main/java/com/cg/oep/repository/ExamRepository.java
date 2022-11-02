package com.cg.oep.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.oep.entity.Exam;

@Repository
public interface ExamRepository extends MongoRepository<Exam, Integer> {

}
