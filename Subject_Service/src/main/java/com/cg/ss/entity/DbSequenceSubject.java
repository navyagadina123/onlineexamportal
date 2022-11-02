package com.cg.ss.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "db_sequence_subject")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DbSequenceSubject {
  
	@Id
    private String  id;
    private int seq;
}
