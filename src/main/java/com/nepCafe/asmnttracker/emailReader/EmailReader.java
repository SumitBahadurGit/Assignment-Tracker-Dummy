package com.nepCafe.asmnttracker.emailReader;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import org.springframework.stereotype.Service;

import com.nepCafe.asmnttracker.models.ATBody;
import com.nepCafe.asmnttracker.models.User;

@Service
public class EmailReader {

	public static void main(String[] args) {
		EmailReader er = new EmailReader();
		er.refreshAndFetch();
	}

	public ATBody refreshAndFetch() {
		ATBody response = null;
		try {
			String host = "imap.gmail.com";
			String mailStoreType = "imap";
			String username = "greenconsultingsolutions.us@gmail.com";
			String password = "Y3grerabbit!";

			EmailContentServiceImpl ext = new EmailContentServiceImpl();
			// create properties
			Properties properties = new Properties();

			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", "993");
			properties.put("mail.imap.starttls.enable", "true");
			properties.put("mail.imap.ssl.trust", host);

			Session emailSession = Session.getDefaultInstance(properties);

			// create the imap store object and connect to the imap server
			Store store = emailSession.getStore("imaps");

			store.connect(host, username, password);

			// create the inbox object and open it
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.RECENT), false));


			for (int i = messages.length - 1; i > messages.length - 2; i--) {

				Message message = messages[i];
				message.setFlag(Flag.SEEN, true);
				response = ext.extractReceivedEmail(message.getInputStream());
				response.setTitle(message.getSubject());
				response.setStatus("OPEN");				
				User user = new User();
				user.setEmail(InternetAddress.toString(message.getFrom()));
				response.setPostedBy(user);
				response.setUpdatedBy(user);
				response.setPostedOn(message.getReceivedDate());		
				

			}

			inbox.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(response.toString());
		return response;
	}

}
