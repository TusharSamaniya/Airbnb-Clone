package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.entity.Users;
import com.tushar.service.UserService;

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

}
