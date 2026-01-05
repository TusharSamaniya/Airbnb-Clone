package com.tushar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	List<Property> findByHostId(Long hostId);

}
