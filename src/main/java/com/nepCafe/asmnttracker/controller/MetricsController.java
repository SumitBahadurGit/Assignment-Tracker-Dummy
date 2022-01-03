package com.nepCafe.asmnttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepCafe.asmnttracker.service.UserService;

@RestController
@RequestMapping("/v1/metrics/users")
public class MetricsController {

	@Autowired
	private UserService userService;

	@GetMapping("/empImmigStats")
	public List<List<Object>> getActiveEmpStatsByStatus() {
		return userService.getActiveEmpStatsByStatus();
	}
	
}
