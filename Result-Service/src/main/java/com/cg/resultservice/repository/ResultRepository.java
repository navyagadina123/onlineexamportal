package com.cg.resultservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.resultservice.model.Result;

@Repository
public interface ResultRepository extends MongoRepository<Result,Integer> {

}
