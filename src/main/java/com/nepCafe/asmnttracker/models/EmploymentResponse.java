package com.nepCafe.asmnttracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmploymentResponse {

	private List<Employment> employments;
	private String message;
	private String errorMessage;
	

	public List<Employment> getEmployments() {
		return employments;
	}
	public void setEmployments(List<Employment> employments) {
		this.employments = employments;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
