package com.tushar.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookingRequest {
	
	private Long propertyId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int guests;

}
