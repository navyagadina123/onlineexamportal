package com.cg.ss.entity;

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

@Document(collection = "subject_service")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
     
	@Transient
	public static final String SEQUENCE_NAME = null;

	@Id
	private int id;
	
	@NotBlank(message="subname cannot be null") // to work all this values we should include @Valid at every starting point of methods
	@Size(max = 20)
	private String sub_name;
	
}
