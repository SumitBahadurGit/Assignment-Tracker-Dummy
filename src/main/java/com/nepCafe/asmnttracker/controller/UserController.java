package com.nepCafe.asmnttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.models.User;
import com.nepCafe.asmnttracker.models.Users;
import com.nepCafe.asmnttracker.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}/find")
	public User findById(@PathVariable Long id,
			@RequestParam(name = "includeDetails", required = false) boolean includeEmployment,
			@RequestParam(name = "includeAttachments", required = false) boolean includeAttachments) {
		return userService.findUser(id, includeEmployment, includeAttachments);
	}

	@GetMapping("/find")
	public Users find(@RequestParam(required = false) String role, @RequestParam(required = false) String empStatus,
			@RequestParam(required = true, defaultValue = "0") int page,
			@RequestParam(required = true, defaultValue = "20") int size) {
		return userService.findUsersByRole(role, empStatus, page, size);
	}

	@GetMapping("/search")
	public Users find(@RequestParam(required = true) String searchTerm) {
		return userService.searchUsers(searchTerm);
	}

	@PostMapping("/save")
	public User save(@RequestBody User request) throws KException {
		return userService.save(request);
	}

	@PostMapping("/update")
	public User update(@RequestBody User request) throws KException {
		return userService.update(request);
	}

}
