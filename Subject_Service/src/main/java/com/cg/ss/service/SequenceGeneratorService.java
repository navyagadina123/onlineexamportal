package com.cg.ss.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cg.ss.entity.DbSequenceSubject;


@Service
public class SequenceGeneratorService {

	 @Autowired
	    private MongoOperations mongoOperations;
	  
	    public int getSequenceNumberForSubject(String sequenceName) {
	        //get sequence no
	        Query query = new Query(Criteria.where("id").is(sequenceName));
	        //update the sequence no
	        Update update = new Update().inc("seq",100);
	        //modify in document
	        //login id will start from 100
	        DbSequenceSubject pro = mongoOperations
	                .findAndModify(query,
	                        update, options().returnNew(true).upsert(true),
	                        DbSequenceSubject.class);
	        return !Objects.isNull(pro) ? pro.getSeq() :1;
	    }

}
