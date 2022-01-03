package com.nepCafe.asmnttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepCafe.asmnttracker.emailReader.EmailReader;
import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.Email;
import com.nepCafe.asmnttracker.service.AtService;
import com.nepCafe.asmnttracker.service.MailService;

@RestController
@RequestMapping("/v1/email")
public class EmailController {

	private MailService mailService;
	private EmailReader emailReader;
	private AtService atService;

	@Autowired
	public EmailController(MailService mailService, EmailReader emailReader, AtService atService) {
		this.mailService = mailService;
		this.emailReader = emailReader;
		this.atService = atService;
	}

	@GetMapping("/refresh")
	public ATBody refresh() throws KException {

		ATBody atBody = emailReader.refreshAndFetch();
		if (atBody != null) {
			atBody = atService.saveAssignment(atBody);
		}
		return atBody;
	}

	@PostMapping("/send")
	public boolean sendEmail(@RequestBody Email email) {
		try {
			return mailService.sendSimpleMessage(email);
		} catch (KException e) {
			e.printStackTrace();
			return false;
		}
	}
}
