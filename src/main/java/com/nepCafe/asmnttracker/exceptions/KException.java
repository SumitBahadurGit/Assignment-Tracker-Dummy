package com.nepCafe.asmnttracker.exceptions;

public class KException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4972002453242741884L;
	public int code;
	
	public KException() {
		super();
	}
	
	public KException(int code, String message) {
		super(message);
	}
	
	
}
