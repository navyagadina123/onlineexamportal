package com.cg.questionservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "db_sequence_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequenceQuestion {
	   @Id
	    private String  id;
	    private int seq;

}
