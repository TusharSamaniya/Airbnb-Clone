package com.tushar.entity;

import java.time.LocalDate;

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
@Table(name = "bookings")
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "property_id", nullable = false)
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "guest_id", nullable = false)
	private Users guest;
	
	@Column(nullable = false)
	private LocalDate checkIn;
	
	@Column(nullable = false)
	private LocalDate checkOut;
	private int numberOfGuests;
	private double totalPrice;
	private LocalDate bookedAt = LocalDate.now();
	private String status = "CONFIRMED";
}
