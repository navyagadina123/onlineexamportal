package com.cg.resultservice.service;

import java.util.List;

import com.cg.resultservice.exception.NoProperDataException;
import com.cg.resultservice.exception.ResultNotFoundException;
import com.cg.resultservice.model.Result;

public interface ResultService {

	public  List<Result> getAllResults() throws  ResultNotFoundException;
	public Result getResultById( int id) throws ResultNotFoundException;
	public Result addNewResult(Result result)  throws  NoProperDataException;

}
