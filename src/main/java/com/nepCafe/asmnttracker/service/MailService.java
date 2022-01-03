package com.nepCafe.asmnttracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.exceptions.KExceptionCodes;
import com.nepCafe.asmnttracker.models.Email;

import emailTemplates.EmailTemplates;

@Service
public class MailService {

	private JavaMailSender mailSender;
	public static final String DEFAULT_EMAIL = "greenconsultingsolutions.us@gmail.com";

	@Autowired
	public MailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmails(List<Email> emails) throws KException {
		for (Email email : emails) {
			sendSimpleMessage(email);
		}
	}

	public boolean sendSimpleMessage(Email email) throws KException {

		if (email == null)
			return false;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(DEFAULT_EMAIL);			
			List<String> emails = new ArrayList<String>();
			emails.addAll(Arrays.asList(email.getTo()));
			emails.add(DEFAULT_EMAIL);
			String[] finalEmails = new String[emails.size()];
			emails.toArray(finalEmails);
 			helper.setTo(finalEmails);
 			
 			if(email.getCc() != null) {
 	 			helper.setCc(email.getCc()); 				
 			}

 			helper.setSubject(email.getSubject());
			helper.setText(email.getBody(), true);
			mailSender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new KException(KExceptionCodes.EMAIL_ERR, "Unable to send mail to " + email.getTo());
			
		}
		return true;
	}
}
