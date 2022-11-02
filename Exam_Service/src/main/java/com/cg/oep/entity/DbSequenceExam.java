package com.cg.oep.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "db_sequence_exam")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DbSequenceExam {
  
	@Id
    private String  id;
    private int seq;
}
