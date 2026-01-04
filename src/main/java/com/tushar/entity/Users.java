package com.tushar.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@JsonProperty("fullName")
	@Column(nullable = false)
	private String fullname;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String role = "GUEST";
	
	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Property> properties = new ArrayList<>();

}
