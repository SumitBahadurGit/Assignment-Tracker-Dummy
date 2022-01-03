package com.nepCafe.asmnttracker.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nepCafe.asmnttracker.exceptions.KException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	
	@ExceptionHandler(value = {KException.class})
	public ResponseEntity<Object> handleExctption(KException ex, WebRequest request) {
		
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

		
		
	}
}
