package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.dto.LoginRequest;
import com.tushar.entity.Users;
import com.tushar.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("register")
	public ResponseEntity<Users> register(@RequestBody Users user){
		try {
			Users saveUser = service.regrestation(user);
			user.setPassword(null);
			return ResponseEntity.ok(saveUser);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session){
		try {
			Users user = service.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
			session.setAttribute("loggedInUser", user);
			return ResponseEntity.ok(user);
		}catch(RuntimeException e) {
			return ResponseEntity.status(401).body("Invalid email and password");
		}
	}

}
