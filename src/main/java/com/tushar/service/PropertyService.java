package com.tushar.service;

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

}
