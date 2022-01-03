package com.nepCafe.asmnttracker.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.models.Employment;
import com.nepCafe.asmnttracker.models.EmploymentResponse;


@Component
public class RemoteEmployment extends HttpService {

		
	@Value("${service.remote.url.employment-tracker}")
	private String url;
	private String slash = "/";
	private String get = "/find";
	private String save = "/save";
	
	@Autowired
	public RemoteEmployment() {
		super();

		// TODO Auto-generated constructor stub
	}
	
	public EmploymentResponse saveEmployment(Employment employment) {
		EmploymentResponse response = (EmploymentResponse) post(employment, url + save, EmploymentResponse.class);
		return response;
	}
	public EmploymentResponse getEmployment(Long id) {
		EmploymentResponse response = (EmploymentResponse) get(url + slash + id + get , EmploymentResponse.class);
		return response;
	}
	
}
