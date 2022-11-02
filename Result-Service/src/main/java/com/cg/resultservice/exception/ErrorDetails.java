package com.cg.resultservice.exception;

import java.util.Date;

public class ErrorDetails {
   
	private Date date;
	private String details;
	private String massage;
	public ErrorDetails() {
		super();

	}
	public ErrorDetails(Date date, String details, String massage) {
		super();
		this.date = date;
		this.details = details;
		this.massage = massage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	
	
}
