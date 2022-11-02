package com.cg.resultservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.resultservice.exception.NoProperDataException;
import com.cg.resultservice.exception.ResultNotFoundException;
import com.cg.resultservice.model.Result;
import com.cg.resultservice.service.ResultServiceImpl;
import com.cg.resultservice.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins ="*")
@RequestMapping("/result")
public class ResultController {
	
	@Autowired
	private ResultServiceImpl resultServiceimpl;

	@Autowired
	private SequenceGeneratorService service;
	
	@GetMapping("/allResults") 
	public ResponseEntity<List<Result>> getAllResults() throws ResultNotFoundException
	{
		
		List<Result> results=resultServiceimpl.getAllResults();
		log.info("starting  of get mapping");
	
		if(results.size()>0) {
			log.debug("results are {}"
					+ results);
		 return new  ResponseEntity<>(results,HttpStatus.OK); 
		}
		else {
			log.debug(" no results found ");
			 return new  ResponseEntity<>(results,HttpStatus.NO_CONTENT); 
		}
		
		
	}
	
	@GetMapping("/results/{id}")
	public ResponseEntity<Result> getResultById(@PathVariable  Integer id)
	throws ResultNotFoundException{
		
		Result results= resultServiceimpl.getResultById(id);
		if(results!=null){
		  return ResponseEntity.ok().body(results);
		}
		  else {
			return new   ResponseEntity(results,HttpStatus.NOT_FOUND);
		  }

	}
		
	
	@PostMapping("/addresults") 
	public ResponseEntity<Result> addResult(@RequestBody Result results)  throws NoProperDataException
		
		{
			
			if(results!=null) 
			{
				
				results.setId(service.getSequenceNumberForResult(Result.SEQUENCE_NAME));
				resultServiceimpl.addNewResult(results);
				log.error("added results");
				return new ResponseEntity<>(results,HttpStatus.CREATED);
				
			}
			else
			{
				throw new NoProperDataException("Please fill fields");
				
			}
			
		}

}
