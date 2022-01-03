package com.nepCafe.asmnttracker.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ATResponse {

	private int page;
	private int size;
	private long totalItems;
	private int totalPage;
	private List<ATBody> assignments;
	private String message;
	private String errorMessage;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long l) {
		this.totalItems = l;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<ATBody> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<ATBody> assignments) {
		this.assignments = assignments;
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
