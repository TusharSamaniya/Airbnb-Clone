package com.tushar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String description;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private Double pricePerNight;
	private Integer bedrooms;
	private Integer bathrooms;
	private Integer maxGuest;
	private String amenities;
	
	@ManyToOne
	@JoinColumn(name = "host_id", nullable = false)
	private Users user;

}
