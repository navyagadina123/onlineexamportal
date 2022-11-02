package com.cg.resultservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="result_details")
public class Result {
	
	 public static final String SEQUENCE_NAME = "result_sequence";

	@Id
	  
	   private int id;

	   private String status;
	   
	   private String score;
	   
	   private String edate;
	   
	   private String totalMarks;
	   
	   private String totalQuestion;
	   

}
