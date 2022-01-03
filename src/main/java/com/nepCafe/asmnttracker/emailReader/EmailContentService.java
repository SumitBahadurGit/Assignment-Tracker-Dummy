package com.nepCafe.asmnttracker.emailReader;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;


public interface EmailContentService {

    MimeMessage convertToMimeMessage(InputStream data) throws MessagingException;
    

}
