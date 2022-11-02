package com.cg.resultservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.resultservice.exception.NoProperDataException;
import com.cg.resultservice.exception.ResultNotFoundException;
import com.cg.resultservice.model.Result;
import com.cg.resultservice.repository.ResultRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResultServiceImpl implements ResultService {
	@Autowired
	private ResultRepository resultRepository;
	
	
	@Override
	public List<Result> getAllResults() throws ResultNotFoundException {
		List<Result> results =new ArrayList<>();
		results =resultRepository.findAll();
		try {
		if(results.size()==0){
			throw new ResultNotFoundException("Result is empty");
		}
		}catch(ResultNotFoundException e)
		{
			log.error(e.getMessage());
		}
	return results;
	}
	
	@Override
	public Result addNewResult(@RequestBody Result result) throws NoProperDataException {
		log.info("start");
		if(result!=null) 
		{
			resultRepository.save(result);
			log.debug("result added");
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return result;	
		}
	
	

	@Override
	public Result getResultById(int id) throws ResultNotFoundException {
		return resultRepository.findById(id)
				.orElseThrow(()-> new  ResultNotFoundException("result Not Found"+id));

		
	}

	
}
