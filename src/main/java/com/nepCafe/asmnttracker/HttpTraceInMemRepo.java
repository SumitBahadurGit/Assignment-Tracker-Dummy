package com.nepCafe.asmnttracker;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;


@Repository
public class HttpTraceInMemRepo extends InMemoryHttpTraceRepository{
	
	public HttpTraceInMemRepo() {
		super();
	}
	@Override
	public void add(HttpTrace trace) {
		super.add(trace);		
	}

}
