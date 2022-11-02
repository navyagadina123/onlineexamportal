package com.cg.ss.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.ss.entity.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, Integer> {

}
