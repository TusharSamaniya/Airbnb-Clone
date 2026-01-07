package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.dto.ContactRequest;
import com.tushar.service.EmailService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:8080")
public class ContactController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestBody ContactRequest request){
		try {
			emailService.sendContactEmail(request.getName(), request.getEmail(), request.getSubject(), request.getMessage());
			return ResponseEntity.ok("Message sent successfully");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("Failed to send message: " + e.getMessage());
		}
	}

}
