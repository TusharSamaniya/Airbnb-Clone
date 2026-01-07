package com.tushar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendContactEmail(String fromName, String fromemail, String subject, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("tusharsamaniya02@gmail.com");
		mail.setFrom(fromemail);
		mail.setSubject("[Airbnb Clone Contact]" + subject);
		mail.setText("\"New message from your Airbnb Clone project!\\n\\n\" +\r\n"
				+ "            \"Name: \" + fromName + \"\\n\" +\r\n"
				+ "            \"Email: \" + fromEmail + \"\\n\\n\" +\r\n"
				+ "            \"Message:\\n\" + message");
		mailSender.send(mail);
	}

}
