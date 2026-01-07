package com.tushar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendContactEmail(String fromName, String fromEmail, String subject, String message) {
	    SimpleMailMessage mail = new SimpleMailMessage();
	    mail.setTo("tusharsamaniya29@gmail.com");
	    mail.setFrom(fromEmail);  // Sender's email (visible in Gmail)
	    mail.setSubject("[Airbnb Clone Contact] " + subject);

	    // Fixed: Proper string formatting with actual values
	    String emailBody = "New message from your Airbnb Clone project!\n\n" +
	                       "Name: " + fromName + "\n" +
	                       "Email: " + fromEmail + "\n\n" +
	                       "Message:\n" + message;

	    mail.setText(emailBody);

	    mailSender.send(mail);
	}

}
