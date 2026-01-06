package com.tushar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.entity.Property;
import com.tushar.entity.Users;
import com.tushar.service.PropertyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProperty(@RequestBody Property property, HttpSession session){
		
			System.out.println("Session ID: " + session.getId());
			System.out.println("User: " + session.getAttribute("loggedInUser"));
		
			Users currentUser = (Users) session.getAttribute("loggedInUser"); 
			if(currentUser == null)
				return ResponseEntity.status(401).body("Please login first");
			
			try {
	            Property savedProperty = propertyService.addProperty(property, currentUser.getId());
	            return ResponseEntity.ok(savedProperty);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
			
		}
	
	@GetMapping("/all")
	public ResponseEntity<List<Property>> getAllProperties(){
		List<Property> properties = propertyService.getAllProperties();
		return ResponseEntity.ok(properties);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
		Property property = propertyService.getPropertyById(id);
		return ResponseEntity.ok(property);
	}
	
	
	}


