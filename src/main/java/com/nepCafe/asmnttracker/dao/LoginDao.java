package com.nepCafe.asmnttracker.dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nepCafe.asmnttracker.dto.LogInDto;
import com.nepCafe.asmnttracker.repo.LoginRepo;

@Component
public class LoginDao {

	private LoginRepo repo;
	
	@Autowired
	public LoginDao(LoginRepo repo) {
		this.repo = repo;
	}
	
	public LogInDto save(LogInDto entity) {
		return repo.save(entity);
	}
		
	public LogInDto find(String userName) {
		Optional<LogInDto> optional =  repo.findByUserName(userName);
		LogInDto logInDto = null;
		
		if(optional.isPresent()) {
			
			logInDto = optional.get();				
			logInDto.setLastLogin(Timestamp.from(Instant.now()));
			logInDto = save(logInDto);
		}

		return logInDto;
	}
	
	public LogInDto find(String userName, String pw) {
		
		Optional<LogInDto> optional =  repo.findByUserNameAndPw(userName, pw);
		LogInDto logInDto = null;
		
		if(optional.isPresent()) {
			logInDto = optional.get();
			logInDto.setLastLogin(Timestamp.from(Instant.now()));
			logInDto = save(logInDto);
		}

		return logInDto;
	}
}
