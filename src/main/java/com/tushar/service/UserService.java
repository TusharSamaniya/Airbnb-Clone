package com.tushar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tushar.entity.Users;
import com.tushar.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Users regrestation(Users user) {
		if(repo.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public Users loginUser(String email, String password) {
		Optional<Users> userOptional = repo.findByEmail(email);
		if(userOptional.isPresent()) {
			Users user = userOptional.get();
			if(passwordEncoder.matches(password, user.getPassword())) {
				user.setPassword(null);
				return user;
			}
	}
		throw new RuntimeException("Invalid email or password");
}
}