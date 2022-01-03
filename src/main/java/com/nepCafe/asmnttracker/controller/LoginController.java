package com.nepCafe.asmnttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.models.Login;
import com.nepCafe.asmnttracker.models.User;
import com.nepCafe.asmnttracker.service.LoginService;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/save")
	public User save(@RequestBody Login request) {
		return loginService.save(request);
	}
	
	@PostMapping
	public User login(@RequestBody Login request) {
		return loginService.tryLogIn(request);
	}
	
	@PostMapping("/update")
	public User changePw(@RequestBody Login request) throws KException {
		return loginService.changePw(request);
	}
	
	@PostMapping("/forgotPassword")
	public User forgotPassword(@RequestBody Login request) throws KException {
		return loginService.forgotPw(request);
	}
}
