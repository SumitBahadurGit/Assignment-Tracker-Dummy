package com.nepCafe.asmnttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nepCafe.asmnttracker.dao.LoginDao;
import com.nepCafe.asmnttracker.dao.UserDao;
import com.nepCafe.asmnttracker.dto.LogInDto;
import com.nepCafe.asmnttracker.dto.UserDto;
import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.exceptions.KExceptionCodes;
import com.nepCafe.asmnttracker.models.Email;
import com.nepCafe.asmnttracker.models.Login;
import com.nepCafe.asmnttracker.models.User;
import com.nepCafe.asmnttracker.util.MailUtil;
import com.nepCafe.asmnttracker.util.MapperUtil;
import com.sun.org.apache.bcel.internal.generic.StackProducer;

import emailTemplates.EmailTemplates;

@Service
public class LoginService {


	private LoginDao loginDao;
	private UserDao userDao;
	private MailService mailService;

	@Autowired
	public LoginService(LoginDao loginDao, UserDao userDao, MailService mailService) {
		this.loginDao = loginDao;
		this.userDao = userDao;
		this.mailService = mailService;
	}

	public User save(Login login) {
		User response = null;
		if (login.getUser() != null && login.getUser().getId() != 0) {
			UserDto userDto = userDao.find(login.getUser().getId());
			if (userDto != null) {
				LogInDto dto = MapperUtil.login2Dto.apply(login);
				dto.setUser(userDto);
				dto = loginDao.save(dto);
				response = MapperUtil.dto2User.apply(dto.getUser());
			}

		}
		if(response == null) {
			response = new User();
			response.setErrorMessage("Error saving user");
		}
		return response;
	}

	public User tryLogIn(Login login) {
		User response = null;
		LogInDto dto = loginDao.find(login.getUserName(), login.getPw());
		if (dto != null) {
			UserDto user = dto.getUser();
			response = MapperUtil.dto2User.apply(user);
		}
		if(response == null) {
			response = new User();
			response.setErrorMessage("Invalid credentials");
		}
		return response;
	}

	@Transactional(rollbackFor = Exception.class)
	public User forgotPw(Login login) throws KException {
		
		User response = new User();
		if(login.getUserName() == null ) {
			throw new KException(KExceptionCodes.VALIDATION_ERR, "Username is required to retreive password");
		}
		LogInDto dto = loginDao.find(login.getUserName());		

		if (dto != null) {
			String tempPw = String.valueOf(System.currentTimeMillis());
			dto.setPw(tempPw);
			dto = loginDao.save(dto);
			Email email = MailUtil.handleForgotPassword(dto.getUser().getEmail(), tempPw);
			if(email != null) {
				boolean isSuccess = mailService.sendSimpleMessage(email);
				if(!isSuccess) {
					response.setErrorMessage("Error sending email. Please contact admin.");
				}
			}
		} else {
			response.setErrorMessage("The username is not in the system. Please provide a valid username.");
		}

		return response;
	}
	
	public User changePw(Login login) throws KException {
		User response = null;
		if(login.getNewPw() == null || login.getNewPw().length() < 6) {
			response = new User();
			response.setErrorMessage("Password needs to be at least 8 characters.");
			return response;
		}
		LogInDto dto = loginDao.find(login.getUserName(), login.getPw());
		if (dto != null) {
			dto.setPw(login.getNewPw());
			dto = loginDao.save(dto);
			UserDto user = dto.getUser();
			response = MapperUtil.dto2User.apply(user);
		}
		if(response == null) {
			response = new User();
			response.setErrorMessage("Either username or password or both do not match.");
		}
		return response;
	}

}
