package com.tushar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.entity.Property;
import com.tushar.entity.Users;
import com.tushar.repository.PropertyRepository;
import com.tushar.repository.UserRepository;

@Service
public class PropertyService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PropertyRepository propertyRepo;
	
	public Property addProperty(Property property, Long hostId) {
		Users host = userRepo.findById(hostId)
				.orElseThrow(() -> new RuntimeException("Host not found"));
		
		if(!"HOST".equals(host.getRole())) {
			throw new RuntimeException("Only user with host role can add Properties");
		}
		
		property.setHost(host);
		return propertyRepo.save(property);
	}
	
	public List<Property> getAllProperties(){
		return propertyRepo.findAll();
	}
	
	public Property getPropertyById(Long id) {
	    return propertyRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
	}
	
	public List<Property> searchProperty(String location, Integer guests){
		if(location == null || location.isEmpty()) {
			if(guests == null) {
				return propertyRepo.findAll();
			}
			return propertyRepo.findAll().stream()
					.filter(p -> p.getMaxGuest() >= guests)
					.collect(Collectors.toList());
		}
		String lowerLocation = location.toLowerCase();
		Stream<Property> stream = propertyRepo.findAll().stream()
				.filter(p -> p.getLocation().toLowerCase().contains(lowerLocation));
		
		if(guests != null) {
			stream = stream.filter(p -> p.getMaxGuest() >= guests);
		}
		return stream.collect(Collectors.toList());
		
	}
	

}
