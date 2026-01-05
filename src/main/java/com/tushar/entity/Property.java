package com.tushar.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "property")
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
	
	@JsonProperty("maxGuests")
	private Integer maxGuest;
	private String amenities;
	
	@ManyToOne
	@JoinColumn(name = "host_id", nullable = false)
	private Users host;

}
