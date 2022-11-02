package com.cg.oep.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.oep.entity.MyErrorResponse;
import com.cg.oep.exception.ExamNotFoundException;
import com.cg.oep.exception.NoProperDataException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({RoleNotFoundException.class,ExamNotFoundException.class})
	public ResponseEntity<MyErrorResponse> handleExamNotFound(RoleNotFoundException ex){
				MyErrorResponse error=new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("id doesn't exist....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler({NoProperDataException.class})
	public ResponseEntity<MyErrorResponse> ExamDataNotFound(NoProperDataException ex){
				MyErrorResponse error=new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_ACCEPTABLE);
		error.setMessage(ex.getMessage());
		error.setReason("please fill all fields....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<MyErrorResponse> handleBadRequest(MethodArgumentTypeMismatchException ex){
				MyErrorResponse error=new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setReason("Wrong url/method typed ....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<MyErrorResponse> handleMethodNotSupportException(HttpRequestMethodNotSupportedException ex){
				MyErrorResponse error=new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		error.setMessage(ex.getMessage());
		error.setReason("Wrong method selected....");
		return new ResponseEntity<MyErrorResponse>(error,HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleException(Exception ex){
			Map<String,Object> body=new LinkedHashMap<String, Object>();
		body.put("timestamp",LocalDateTime.now());
		body.put("Status",HttpStatus.NOT_ACCEPTABLE);
		body.put("Message",ex.getMessage());
		body.put("Reason","Wrong url typed....");
		return new ResponseEntity<Object>(body,HttpStatus.NOT_ACCEPTABLE);
	}
}
