package com.tushar.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.entity.Booking;
import com.tushar.entity.Property;
import com.tushar.entity.Users;
import com.tushar.repository.BookingRepository;
import com.tushar.repository.PropertyRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	public Booking createBooking(Property property, Users guest, LocalDate checkIn, LocalDate checkOut, int guests) {
		var overlapping = bookingRepository.findOverlappingBookings(property.getId(), checkIn, checkOut);
		if(!overlapping.isEmpty()) {
			throw new RuntimeException("These dated are not available, Property is already booked.");
		}
		if(guests > property.getMaxGuest()) {
			throw new RuntimeException("Too many guests for this property.");
		}
		long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
		double totalPrice = nights * property.getPricePerNight();
		
		Booking booking = new Booking();
		booking.setProperty(property);
		booking.setGuest(guest);
		booking.setCheckIn(checkIn);
		booking.setCheckOut(checkOut);
		booking.setNumberOfGuests(guests);
		booking.setTotalPrice(totalPrice);
		
		return bookingRepository.save(booking);
	}

}
