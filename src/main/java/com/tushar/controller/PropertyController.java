package com.tushar.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/upload-image")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, HttpSession session) {
	    Users currentUser = (Users) session.getAttribute("loggedInUser");
	    if (currentUser == null || !"HOST".equals(currentUser.getRole())) {
	        return ResponseEntity.status(401).body("Unauthorized");
	    }

	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body("Please select an image");
	    }

	    try {
	        // Save file to uploads folder
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        Path path = Paths.get("uploads/" + fileName);
	        Files.createDirectories(path.getParent());
	        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	        // Return the URL for frontend
	        String imageUrl = "/uploads/" + fileName;
	        return ResponseEntity.ok(imageUrl);

	    } catch (IOException e) {
	        return ResponseEntity.status(500).body("Upload failed");
	    }
	}
	
	public ResponseEntity<List<Property>> searchProperties(@RequestParam(required = false) String location, @RequestParam(required = false) Integer guests){
		List<Property> result = propertyService.searchProperty(location, guests);
		return ResponseEntity.ok(result);
	}
	
	
	}


