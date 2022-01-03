package com.nepCafe.asmnttracker.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtCount {

	private Map<String, Long> statusCount;
	private String message;
	private String errorMessage;

	public Map<String, Long> getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(Map<String, Long> statusCount) {
		this.statusCount = statusCount;
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
