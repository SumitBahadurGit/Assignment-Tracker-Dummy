package com.nepCafe.asmnttracker.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class HttpService {

	@Autowired
	private RestTemplate restTemplate;
	
	public HttpService() {

	}
	
	
	public Object post(Object payload,  String url, Class clazz) {

		ResponseEntity<Object> response =  restTemplate.postForEntity(url, payload, clazz);
		
		if(response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;

	}
	
	public Object get( String url, Class clazz) {
		ResponseEntity<Object> response = restTemplate.getForEntity(url , clazz);
		if(response != null && response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}
	

}
