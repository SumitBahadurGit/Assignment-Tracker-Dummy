package com.nepCafe.asmnttracker.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.Comment;
import com.nepCafe.asmnttracker.models.Email;
import com.nepCafe.asmnttracker.models.User;

import emailTemplates.EmailTemplates;

public class MailUtil {
	
	public static Email handleForgotPassword( String userName, String tempPw) {
		
		Map<String, String> commonProps = new HashMap<>();
		commonProps.put(EmailTemplates.SITELINK, EmailTemplates.SITELINK_VALUE);
		commonProps.put(EmailTemplates.PASSWORD, tempPw);
		
		Email email = new Email();
		email.setTo( new String[] { userName} );
		email.setSubject(EmailTemplates.SENSITIVE_DATA);
		email.setBody(EmailTemplates.forgotPasswordTemplate.apply(commonProps));
		return email;
	}

	public static Email handleNewComment(ATBody body, Comment comment) {
		if (body != null && body.getAssignee() != null && comment != null) {
			Map<String, String> commonProps = new HashMap<>();
			commonProps.put(EmailTemplates.SITELINK, EmailTemplates.SITELINK_VALUE);
			commonProps.put(EmailTemplates.TITLE, body.getTitle());
			commonProps.put(EmailTemplates.ID, String.valueOf(body.getId()));
			commonProps.put(EmailTemplates.USER_NAME,
					body.getAssignee().getFirstName().concat(" ").concat(body.getAssignee().getLastName()));
			commonProps.put(EmailTemplates.COMMENT, comment.getComments());
			commonProps.put(EmailTemplates.AUTHOR,
					comment.getAuthor().getFirstName().concat(" ").concat(comment.getAuthor().getLastName()));
			Email email = new Email();
			email.setSubject(EmailTemplates.NEW_COMMENT);
			email.setTo(new String[] { body.getAssignee().getEmail() });
			email.setBody(EmailTemplates.newCommentTempalte.apply(commonProps));
			return email;
		}
		return null;
	}

	public static Email handleNewUser(User user, String userName, String pw) {
		if (user != null) {
			Map<String, String> commonProps = new HashMap<>();
			commonProps.put(EmailTemplates.SITELINK, EmailTemplates.SITELINK_VALUE);
			commonProps.put(EmailTemplates.USER_NAME, userName);
			commonProps.put(EmailTemplates.NAME, user.getFirstName() + " " + user.getLastName());
			if (user.getAddedBy() != null) {
				commonProps.put(EmailTemplates.ADDED_BY, user.getAddedBy().getFirstName());
			}

			commonProps.put(EmailTemplates.ROLE, user.getRole());
			commonProps.put(EmailTemplates.EMAIL, user.getEmail());
			commonProps.put(EmailTemplates.PASSWORD, pw);
			Email email = new Email();
			email.setSubject(EmailTemplates.NEW_USER_ADDED);
			email.setTo(new String[] { user.getEmail() });
			email.setBody(EmailTemplates.newUserAddedTemplate.apply(commonProps));
			return email;
		}
		return null;
	}

	public static Email handleAssignmentUpdate(ATBody updated) {

		if (updated != null && updated.getAssignee() != null) {
			Map<String, String> commonProps = new HashMap<>();
			commonProps.put(EmailTemplates.SITELINK, EmailTemplates.SITELINK_VALUE);
			commonProps.put(EmailTemplates.TITLE, updated.getTitle());
			commonProps.put(EmailTemplates.ID, String.valueOf(updated.getId()));
			if(updated.getExpiresOn() != null) {
				commonProps.put(EmailTemplates.DEADLINE, updated.getExpiresOn().toString());				
			}


			commonProps.put(EmailTemplates.ASSIGNED_TO,
					updated.getAssignee().getFirstName().concat(" ").concat(updated.getAssignee().getLastName()));
			commonProps.put(EmailTemplates.USER_NAME,
					updated.getAssignee().getFirstName().concat(" ").concat(updated.getAssignee().getLastName()));

			if (updated.getPostedBy() != null) {
				commonProps.put(EmailTemplates.POSTED_BY,
						updated.getPostedBy().getFirstName().concat(" ").concat(updated.getPostedBy().getLastName()));
			}
			if (updated.getUpdatedBy() != null) {
				commonProps.put(EmailTemplates.UPDATED_BY,
						updated.getUpdatedBy().getFirstName().concat(" ").concat(updated.getUpdatedBy().getLastName()));
			}
			
			Email email = new Email();
			email.setSubject(EmailTemplates.TASK_MODIFIED);
			email.setTo(new String[] { updated.getAssignee().getEmail() });
			String htmlBody = EmailTemplates.assignUpdatedTemplate.apply(commonProps);
			email.setBody(htmlBody);
			return email;
		}
		return null;

	};

	public static List<Email> handleAssigneeUpdate(ATBody updated, ATBody previous) {

		List<Email> emails = new ArrayList<>();

		Map<String, String> commonProps = null;

		if (updated != null) {
			commonProps = new HashMap<>();
			commonProps.put(EmailTemplates.SITELINK, EmailTemplates.SITELINK_VALUE);
			commonProps.put(EmailTemplates.TITLE, updated.getTitle());
			commonProps.put(EmailTemplates.ID, String.valueOf(updated.getId()));
			if (updated.getExpiresOn() != null) {
				commonProps.put(EmailTemplates.DEADLINE, updated.getExpiresOn().toString());
			} 

			commonProps.put(EmailTemplates.ASSIGNED_TO, "");

			if (updated.getPostedBy() != null) {
				commonProps.put(EmailTemplates.POSTED_BY,
						updated.getPostedBy().getFirstName().concat(" ").concat(updated.getPostedBy().getLastName()));
			}
			if (updated.getUpdatedBy() != null) {
				commonProps.put(EmailTemplates.UPDATED_BY,
						updated.getUpdatedBy().getFirstName().concat(" ").concat(updated.getUpdatedBy().getLastName()));
			}
		}

		if (updated != null && updated.getAssignee() != null) {

			Email email = new Email();
			commonProps.put(EmailTemplates.USER_NAME,
					updated.getAssignee().getFirstName().concat(" ").concat(updated.getAssignee().getLastName()));
			commonProps.put(EmailTemplates.ASSIGNED_TO,
					updated.getAssignee().getFirstName().concat(" ").concat(updated.getAssignee().getLastName()));

			email.setSubject(EmailTemplates.NEW_TASK);
			email.setTo(new String[] { updated.getAssignee().getEmail() });
			String htmlBody = EmailTemplates.newAssignedTemplate.apply(commonProps);
			email.setBody(htmlBody);
			emails.add(email);
		}

		if (previous != null && previous.getAssignee() != null) {
			Email email = new Email();
			commonProps.put(EmailTemplates.USER_NAME,
					previous.getAssignee().getFirstName().concat(" ").concat(previous.getAssignee().getLastName()));
			email.setSubject(EmailTemplates.TASK_MODIFIED);
			email.setTo(new String[] { previous.getAssignee().getEmail() });
			String htmlBody = EmailTemplates.assigneeChangeTemplate.apply(commonProps);
			email.setBody(htmlBody);
			emails.add(email);
		}

		return emails;
	}
}
