package com.nepCafe.asmnttracker.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {

	public static String convert2Json(Object o) {
		if(o == null) return null;
		if(o instanceof String) return (String) o;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return null;
	}
}
