package com.nepCafe.asmnttracker.util;

import java.util.StringJoiner;

import org.springframework.util.ObjectUtils;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.exceptions.KExceptionCodes;
import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.User;


public class Validator {
	
	public static void validateAssignemnt(ATBody request) throws KException {
		
		if(request == null) throw new KException(KExceptionCodes.NULL_OBJ, "Assignemnt is empty");
		StringJoiner sb = new StringJoiner(" , ");
		
		if(ObjectUtils.isEmpty(request.getTitle())) {
			sb.add("Title is empty");			
		}
		
		if(ObjectUtils.isEmpty(request.getStatus())) {
			sb.add("Status is empty");			
		}

		if(ObjectUtils.isEmpty(request.getAssignmentType())) {
			sb.add("Assignemnt Type is empty");			
		}
		
		if(ObjectUtils.isEmpty(request.getPriority())) {
			sb.add("Priority role is empty");			
		}

		if(sb.length() > 3) {
			throw new KException(KExceptionCodes.VALIDATION_ERR, sb.toString());
		}
		

	}

	public static void validateUser(User user) throws KException {
		
		if(user == null) throw new KException(KExceptionCodes.NULL_OBJ, "User is empty");
		StringJoiner sb = new StringJoiner(" , ");
		
		if(ObjectUtils.isEmpty(user.getFirstName())) {
			sb.add("Firstname is empty");			
		}
		
		if(ObjectUtils.isEmpty(user.getLastName())) {
			sb.add("Lastname is empty");			
		}
		if(ObjectUtils.isEmpty(user.getAccess())) {
			sb.add("User access is empty");			
		}
		if(ObjectUtils.isEmpty(user.getDob())) {
			sb.add("User dob is empty");			
		}
		if(ObjectUtils.isEmpty(user.getGender())) {
			sb.add("User gender is empty");			
		}
		
		if(ObjectUtils.isEmpty(user.getRole())) {
			sb.add("User role is empty");			
		}

		if(ObjectUtils.isEmpty(user.getCountry())) {
			sb.add("User country is empty");			
		}
		if(ObjectUtils.isEmpty(user.getEmail())) {
			sb.add("User email is empty");			
		}
		if(ObjectUtils.isEmpty(user.getPhone())) {
			sb.add("User phone is empty");			
		}
		if(ObjectUtils.isEmpty(user.getEmpStatus())) {
			sb.add("User employment status is empty");			
		}
		if(ObjectUtils.isEmpty(user.getImmigrationstatus())) {
			sb.add("User immigration status is empty");			
		}
		
		if(sb.length() > 3) {
			throw new KException(KExceptionCodes.VALIDATION_ERR, sb.toString());
		}
		
	}

}
