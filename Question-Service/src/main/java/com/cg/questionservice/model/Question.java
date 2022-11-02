package com.cg.questionservice.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="question_details")
public class Question {
	
	  public static final String SEQUENCE_NAME = null;

	@Id
	  private int id;
	  
	  @NotEmpty
	  @Size(min=5,message = "qname is mandatory")
	  private String qname;
	 @NotEmpty(message = "optionone is mandatory")
	  private String optionOne;
	 @NotEmpty(message = "optionTwo is mandatory")
	  private String optionTwo;
	 @NotEmpty(message = "optionThree is mandatory")
	  private String optionThree;
	 @NotEmpty(message = "optionFour is mandatory")
	  private String optionFour;

}
