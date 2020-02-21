package com.example.userManagement.exceptions;

import java.util.Date;

//creating simple error details bean
public class CustomErrorDetails {
	
	private Date timestamp;
	private String message;
	private String errorDetails;
	
	//Constructors
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}

	//Getters

	public Date getTimestamp() {
		return timestamp;
	}


	public String getMessage() {
		return message;
	}


	public String getErrorDetails() {
		return errorDetails;
	}
	
	
	
	

}
