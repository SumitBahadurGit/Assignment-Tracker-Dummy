package com.nepCafe.asmnttracker.models;

import java.util.HashMap;
import java.util.Map;

public class Email {

	
	private String[] to;
	private String[] cc;
	private String subject;	
	private String body;
	private Map<String, String> props;
	
	public Email() {
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	
	
	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}
	
	public void addProp(String key, String value) {
		if(this.props == null) this.props = new HashMap<>();
		this.props.put(key, value);
	}
		
}
