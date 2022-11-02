package com.cg.oep.entity;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exam {

	 @Transient
	 public static final String SEQUENCE_NAME = "exam_sequence";
	 
	@Id
	private int id;

	 @NotBlank(message="examname cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 20)
	private String exam_name;

	 @NotBlank(message="descname cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 100)
	private String desc;

	@NotBlank(message="date cannot be null") // to work all this values we should include @Valid at every starting point of methods
	@Size(max = 20)
	private String date;

	@NotBlank(message="marks cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 4)
	private String marks;

	@NotBlank(message="totalQuestion cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 5)
	private String totalQuestion;

	@NotBlank(message="passMarks cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 4)
	private String passMarks;

	@NotBlank(message="level cannot be null") // to work all this values we should include @Valid at every starting point of methods
	 @Size(max = 100)
	private String level;

	
}
