package com.nepCafe.asmnttracker.emailReader;

import org.apache.commons.mail.util.MimeMessageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.User;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailContentServiceImpl {

	public MimeMessage convertToMimeMessage(InputStream data) throws MessagingException {
		Session session = Session.getDefaultInstance(new Properties());
		try {
			return new MimeMessage(session, data);
		} catch (MessagingException e) {
			throw new MessagingException();
		}
	}

	public ATBody extractReceivedEmail(InputStream data) throws Exception {

		MimeMessage message;
		try {
			message = this.convertToMimeMessage(data);

			// Use here Apache library for parsingMimeMessageParser
			MimeMessageParser messageParser = new MimeMessageParser(message);
			messageParser.parse(); // very important to parse before getting data
			ATBody atBody = new ATBody();

			if (messageParser.getPlainContent() != null) {
				atBody.setDesc(messageParser.getPlainContent());
				//System.out.println(messageParser.getPlainContent());
				String textOfHtmlString = Jsoup.parse(messageParser.getPlainContent()).html();
				Document.OutputSettings outputSettings = new Document.OutputSettings();
				outputSettings.prettyPrint(false);				
				outputSettings.escapeMode(EscapeMode.extended);
				outputSettings.charset("ASCII");

				Document document = Jsoup.parse(textOfHtmlString);
				document.outputSettings(outputSettings);// makes html() preserve
																							// linebreaks and spacing
				document.select("br").append("\\n");
				document.select("p").prepend("\\n\\n");
				
				String s = document.html().replaceAll("\\\\n", "\n");
				
				String finalString = Jsoup.clean(s, "", Whitelist.none(),
						outputSettings);

				System.out.println(messageParser.getPlainContent());
			} else {
				atBody.setDesc(messageParser.getHtmlContent());
			}

			System.out.println("\n\n\n\n");

			// System.out.println(messageParser.getPlainContent());
			System.out.println("\n\n\n\n");
			// System.out.println(messageParser.getHtmlContent());
			return atBody;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}
