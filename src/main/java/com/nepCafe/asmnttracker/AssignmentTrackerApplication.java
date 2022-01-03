package com.nepCafe.asmnttracker;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.nepCafe", "org.nepCafe.kafka"})
@EnableEurekaClient
@EnableAspectJAutoProxy
public class AssignmentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentTrackerApplication.class, args);
	}
		
	@Bean
	public JavaMailSender getJavaMailSender() {

	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
	    mailSender.setPort(587);	    
	    mailSender.setUsername("AKIATVTKJN2KO77ZVDMG");
	    mailSender.setPassword("BDjr46KRUGoK+tWuLc48y2tDwszoG/Caxuzp5+x7p5hC");	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
